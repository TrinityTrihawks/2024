// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.aouton;

import frc.robot.commands.test.PrintEnc;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;

public final class Autos {
  /** Example static factory for an autonomous command. */
  public static Command exampleAuto(ExampleSubsystem subsystem) {
    return Commands.sequence(subsystem.exampleMethodCommand());
  }

  public static Command driveAMeter(Drive drive) {
    return new DriveAMeter(drive);
  }

  public static Command printEnc(Drive drive) {
    return new PrintEnc(drive);
  }

  private Autos() {
    throw new UnsupportedOperationException("This is a utility class!");
  }
}
