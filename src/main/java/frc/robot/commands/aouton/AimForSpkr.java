// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.aouton;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Shooter;

class AimForSpkr extends Command {

    private final Drive drive;
    private final Shooter.Limelight eye;
    private final double forwardSpeedCap = .3;
    private final double turnSpeedCap = .4;
    private final double targetArea = 20;

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

        if (eye.hasTarget()) {
            driveAtTarget();

        } else {
            drive.drive(forwardSpeedCap, 0);
        }

    }

    private void driveAtTarget() {

        double turnSpeed = eye.getXOffset() / 15;
        turnSpeed *= turnSpeedCap;

        double forwardSpeed = Math.max(
                // normalize area difference
                (targetArea - eye.getArea()) / targetArea
                        * forwardSpeedCap,
                .2);
        forwardSpeed *= -1;

        drive.drive(forwardSpeedCap, turnSpeed);
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
