// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.aouton;

import edu.wpi.first.math.filter.Debouncer;
import edu.wpi.first.math.filter.Debouncer.DebounceType;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.Robot2024Constants.ShooterConstants;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Intake;

public class AprilLimelight extends Command {

    private Drive drive;
    private double area;
    private boolean hasTarget;
    private final Intake intake;

    DigitalInput input = new DigitalInput(0);

    Debouncer debouncer = new Debouncer(0.1, DebounceType.kBoth);

    /** Creates a new Limelight. */
    public AprilLimelight(Drive drive, Intake intake) {
        this.drive = drive;
        this.intake = intake;
        addRequirements(drive);
        addRequirements(intake);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        NetworkTableInstance.getDefault().getTable(ShooterConstants.kLimelightNostname).getEntry("AprilTags")
                .setNumber(0);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        NetworkTable table = NetworkTableInstance.getDefault().getTable(ShooterConstants.kLimelightNostname);
        NetworkTableEntry tx = table.getEntry("tx");
        NetworkTableEntry ty = table.getEntry("ty");
        NetworkTableEntry ta = table.getEntry("ta");
        NetworkTableEntry tv = table.getEntry("tv");
        NetworkTableEntry tid = table.getEntry("tid");

        double x = tx.getDouble(0.0);
        double y = ty.getDouble(0.0);
        area = ta.getDouble(0.0);
        double id = tid.getDouble(0.0);

        if (area != 0) {
            hasTarget = true;
        } else {
            hasTarget = false;
        }
        debouncer.calculate(hasTarget);

        SmartDashboard.putNumber("LimelightX", x);
        SmartDashboard.putNumber("LimelightY", y);
        SmartDashboard.putNumber("LimelightArea", area);
        SmartDashboard.putNumber("id", id);
        SmartDashboard.putBoolean("has target", hasTarget);

        // double fwdSpeed = (10 - area) / 21;
        double rotSpeed = x / 50;

        SmartDashboard.putNumber("rotSpeed", rotSpeed);
        // SmartDashboard.putNumber("fwdSpeed", fwdSpeed);

        if (hasTarget) {
            if ((rotSpeed < -0.1) || (rotSpeed > 0.1)) {
                // lower limit
                rotSpeed = Math.signum(rotSpeed) * Math.max(0.3, Math.abs(rotSpeed));
                drive.drive(0, rotSpeed);
            } else {
                drive.drive(0.5, 0);
                intake.run();
            }
        } else {
            drive.drive(0, 0);
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}