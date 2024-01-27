// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.aouton;

import frc.robot.commands.test.PrintEnc;
import frc.robot.subsystems.Drive;

import edu.wpi.first.wpilibj2.command.Command;

public final class Autos {

    public static Command driveAMeter(Drive drive) {
        return new DriveAMeter(drive);
    }

    public static Command printEnc(Drive drive) {
        return new PrintEnc(drive);
    }

    public static Command[] allAutos() {
        return new Command[] {
                // put all viable autons here for selection by the sendable chooser and to be
                // run in preflight
        };
    }

    private Autos() {
        throw new UnsupportedOperationException("This is a utility class!");
    }
}
