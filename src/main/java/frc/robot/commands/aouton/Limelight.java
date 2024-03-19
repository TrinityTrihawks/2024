// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.aouton;

import edu.wpi.first.math.filter.Debouncer;
import edu.wpi.first.math.filter.Debouncer.DebounceType;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.LimelightHelpers;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Intake;

public class Limelight extends Command {

    private final Drive drive;
    private double area;
    private boolean hasTarget;
    private Intake intake;

    private Debouncer debouncer = new Debouncer(0.1, DebounceType.kBoth);

    /** Creates a new Limelight. */
    public Limelight(Drive drive, Intake intake) {
        this.drive = drive;
        addRequirements(this.intake = intake);
        addRequirements(drive);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        NetworkTableInstance.getDefault().getTable("limelight-intake").getEntry("pipeline").setNumber(1);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight-intake");
        NetworkTableEntry tx = table.getEntry("tx");
        NetworkTableEntry ta = table.getEntry("ta");

        double x = tx.getDouble(0.0);
        area = ta.getDouble(0.0);

        var targetarr = LimelightHelpers.getLatestResults("limelight-intake").targetingResults.targets_Detector;
        if (targetarr.length > 0) {
            x = targetarr[0].tx;
        }

        boolean rawHasTarget;
        if (area != 0) {
            rawHasTarget = true;
        } else {
            rawHasTarget = false;
        }
        hasTarget = debouncer.calculate(rawHasTarget);

        SmartDashboard.putNumber("LimelightX", x);
        SmartDashboard.putNumber("LimelightArea", area);
        SmartDashboard.putBoolean("has target", hasTarget);
        SmartDashboard.putString("li json", LimelightHelpers.getJSONDump("limelight-intake"));

        // double fwdSpeed = (10 - area) / 21;
        double rotSpeed = Math.signum(x) * Math.max(Math.abs(x), .3);
        SmartDashboard.putNumber("rotSpeed", rotSpeed);
        // SmartDashboard.putNumber("fwdSpeed", fwdSpeed);

        // if(area > 1){}
        if ((rotSpeed < -0.1) || (rotSpeed > 0.1)) {
            drive.drive(0, rotSpeed);
        } else {
            drive.drive(0, 0);
            intake.run();
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        intake.stop();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
