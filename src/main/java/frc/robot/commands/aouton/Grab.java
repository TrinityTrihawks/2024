// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.aouton;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.Robot2024Constants.IntakeConstants.LimelightConstants;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Intake;

/**
 * Command to grab a note via vision.
 * Searches for a note via limelight. If it cannot see one, it terminates;
 * if it sees one, it attempts to drive towards it until the intake reports
 * a note in it.
 */
class Grab extends Command {

    private final Drive drive;
    private final Intake intake;
    private final frc.robot.subsystems.Intake.Limelight eyeball;

    private double initialAngle;
    private double currentAngle;
    private boolean hasFoundTarget = false;
    private double turnSpeed;

    private final double forwardSpeed = .3; // TODO: tune
    private final double turnSpeedCap = .3; // TODO: tune

    /**
     * Command to grab a note via vision.
     * Searches for a note via limelight. If it cannot see one, it terminates;
     * if it sees one, it attempts to drive towards it until the intake reports
     * a note in it.
     */
    public Grab(Drive drive, Intake intake) {
        addRequirements(
                this.drive = drive,
                this.intake = intake);
        this.eyeball = intake.getLimelight();
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        initialAngle = drive.getGyroZ();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        hasFoundTarget = hasFoundTarget || eyeball.hasTarget();
        currentAngle = drive.getGyroZ();

        turnSpeed = eyeball.getOffAngle();
        turnSpeed /= LimelightConstants.kWidestDetectableNoteAngle;
        turnSpeed = Math.max(-1, Math.min(1, turnSpeed)) * turnSpeedCap;

        drive.drive(forwardSpeed, turnSpeed);
        intake.run();
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        intake.stop();
        drive.stop();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return (!eyeball.hasTarget() && !hasFoundTarget) || intake.hasNote();
    }
}
