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
import frc.robot.subsystems.Drive;

class NoteLimelight extends Command {

    private final Drive drive;
    private double area;
    private boolean hasTarget;

    private Debouncer debouncer = new Debouncer(0.1, DebounceType.kBoth);

    /** Creates a new Limelight. */
    public NoteLimelight(Drive drive) {
        this.drive = drive;
        addRequirements(drive);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight-intake");
        NetworkTableEntry tx = table.getEntry("tx");
        NetworkTableEntry ta = table.getEntry("ta");

        double x = tx.getDouble(0.0);
        area = ta.getDouble(0.0);

        boolean rawHasTarget;
        if ((int) area != 0) {
            rawHasTarget = true;
        } else {
            rawHasTarget = false;
        }
        hasTarget = debouncer.calculate(rawHasTarget);

        SmartDashboard.putNumber("LiX", x);
        SmartDashboard.putNumber("LiArea", area);
        SmartDashboard.putBoolean("li has target", hasTarget);
        SmartDashboard.putString("li json",
                NetworkTableInstance.getDefault().getTable("limelight-intake").getEntry("json").getString("ERROR"));

        x /= 50;
        // double fwdSpeed = (10 - area) / 21;
        double rotSpeed = Math.signum(x) * Math.max(Math.abs(x), .3);
        rotSpeed *= hasTarget ? 1 : 0;
        SmartDashboard.putNumber("rotSpeed", rotSpeed);
        // SmartDashboard.putNumber("fwdSpeed", fwdSpeed);

        // if(area > 1){}
        if (hasTarget) {
            drive.drive(0.5, rotSpeed);
        } else {
            drive.drive(0.5, 0);
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
