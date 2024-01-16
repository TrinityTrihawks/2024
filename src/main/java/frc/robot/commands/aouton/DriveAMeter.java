// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.aouton;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drive;

class DriveAMeter extends Command {

    private double startDistance;
    private Drive drive;

    /** Creates a new DriveAMeter. */
    public DriveAMeter(Drive drive) {
        this.drive = drive;
        addRequirements(drive);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        startDistance = drive.getEncoderDistanceLeft();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        drive.drive(0.5, 0);
        System.out.println(this + ": driving forward @ " + drive.getEncoderDistanceLeft());
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return Math.abs(drive.getEncoderDistanceLeft() - startDistance) > 1.0;
    }
}
