// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.aouton;

import java.util.NoSuchElementException;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Shooter;

public class AimForSpkr extends Command {

    private final Drive drive;
    private final Shooter.Limelight eye;
    private final double forwardSpeedCap = .5;
    private final double turnSpeedCap = .5;
    private final double targetArea = 20;
    private final int redTag = 4;
    private final int blueTag = 7;

    /** Creates a new AimForSpkr. */
    public AimForSpkr(Drive d, Shooter.Limelight l) {
        addRequirements(drive = d);
        eye = l;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {

        if (hasCorrectTag()) {
            driveAtTarget();

        } else {
            drive.drive(forwardSpeedCap, 0);
        }

    }

    private boolean hasCorrectTag() {
        return eye.hasTag() && tagMatchesDS();
    }

    private boolean tagMatchesDS() {
        Alliance alliance;
        try {
            alliance = DriverStation.getAlliance().get();
        } catch (NoSuchElementException nsee) {
            DriverStation.reportError(nsee.toString(), true);
            return false;
        }
        switch (alliance) {
            case Blue:
                return eye.getTagID() == blueTag;
            case Red:
                return eye.getTagID() == redTag;

            default:
                DriverStation.reportError("bad alliance color", true);
                return false;
        }

    }

    private void driveAtTarget() {

        double turnSpeed = eye.getXOffset();
        turnSpeed *= turnSpeedCap;

        double forwardSpeed = Math.max(targetArea - eye.getArea(), .2);
        forwardSpeed *= -1 * forwardSpeedCap;

        drive.drive(forwardSpeed, turnSpeed);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        drive.stop();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
