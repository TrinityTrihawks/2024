// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.aouton;

import java.util.NoSuchElementException;

import edu.wpi.first.math.filter.Debouncer;
import edu.wpi.first.math.filter.Debouncer.DebounceType;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.LimelightHelpers;
import frc.robot.Constants.Robot2024Constants.ShooterConstants;
import frc.robot.LimelightHelpers.LimelightTarget_Fiducial;
import frc.robot.subsystems.Drive;

class AprilLimelight extends Command {

    private Drive drive;
    private boolean hasTarget;
    private LimelightTarget_Fiducial curTarget;

    Debouncer debouncer = new Debouncer(0.1, DebounceType.kBoth);

    /** Creates a new Limelight. */
    public AprilLimelight(Drive drive) {
        this.drive = drive;
        addRequirements(drive);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        // NetworkTableInstance.getDefault().getTable(ShooterConstants.kLimelightNostname).getEntry("AprilTags")
        // .setNumber(0);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {

        double x, area;
        x = area = Double.NaN;
        int id = -1;
        boolean rawHasTarget = false;

        updateTargeting();

        if (curTarget != null) {
            x = curTarget.tx;
            area = curTarget.ta;
            id = (int) curTarget.fiducialID;
            rawHasTarget = true;
        }
        hasTarget = debouncer.calculate(rawHasTarget);

        SmartDashboard.putNumber("LimelightX", x);
        SmartDashboard.putNumber("LimelightArea", area);
        SmartDashboard.putNumber("id", id);
        SmartDashboard.putBoolean("has target", hasTarget);
        SmartDashboard.putString("ls json", LimelightHelpers.getJSONDump("limelight-shooter"));

        // double fwdSpeed = (10 - area) / 21;
        double rotSpeed = x / 50;

        SmartDashboard.putNumber("rotSpeed", rotSpeed);
        // SmartDashboard.putNumber("fwdSpeed", fwdSpeed);

        if (targetIsCorrect()) {

            if ((rotSpeed < -0.1) || (rotSpeed > 0.1)) {
                // lower limit
                rotSpeed = Math.signum(rotSpeed) * Math.max(0.3, Math.abs(rotSpeed));
                drive.drive(0, rotSpeed);
            } else {
                drive.drive(0.3, 0);
            }
        } else {
            drive.drive(0.3, 0);
        }
    }

    private boolean targetIsCorrect() {

        if (curTarget == null) {
            return false;
        }

        Alliance alliance;
        try {
            alliance = DriverStation.getAlliance().get();
        } catch (NoSuchElementException nsee) {
            DriverStation.reportError(nsee.toString(), true);
            return false;
        }
        switch (alliance) {
            case Blue:
                return curTarget.fiducialID == ShooterConstants.kBlueTargetID;
            case Red:
                return curTarget.fiducialID == ShooterConstants.kRedTargetID;

            default:
                DriverStation.reportError("bad alliance color " + alliance, true);
                return false;
        }
    }

    private void updateTargeting() {

        LimelightTarget_Fiducial[] targets = LimelightHelpers
                .getLatestResults(ShooterConstants.kLimelightNostname).targetingResults.targets_Fiducials;

        for (LimelightTarget_Fiducial target : targets) {

            curTarget = target;
            if (targetIsCorrect()) {
                return;
            }
        }

        curTarget = null;

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
