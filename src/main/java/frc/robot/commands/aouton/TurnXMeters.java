// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.aouton;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drive;

public class TurnXMeters extends Command {
  /** Creates a new TurnXMeters. */
  private double startDegrees;
  private Drive drive;
  private double degrees;
  public TurnXMeters (Drive drive, double degrees) {
        this.degrees = degrees;
        this.drive = drive;
        addRequirements(drive);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    startDegrees = drive.getGyroZ();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (degrees > 0) {
      drive.drive(0.5, 0.5);
  } else if (degrees < 0) {
      drive.drive(0, -0.5);
  }
}
  

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (degrees > 0){
      return drive.getGyroZ() - startDegrees > degrees;
    }else if (degrees < 0){
      return drive.getGyroZ() - startDegrees < degrees;
    }else{
      return true;
    }
  }
}
