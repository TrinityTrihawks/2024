// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.aouton;

import frc.robot.Constants;
import frc.robot.Constants.AutonConstants;
import frc.robot.commands.test.PrintEnc;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;

public final class Autos {

    public static Command shoot(Shooter shooter, Intake intake) {
        return new Shoot(shooter, intake);
    }

    public static Command shootCL(Shooter shooter, Intake intake) {
        return new ShootCL(shooter, intake);
    }

    public static Command shootCLAmp(Shooter shooter, Intake intake) {
        return new ShootCLAmp(shooter, intake);
    }

    public static Command driveXMeters(Drive drive, double meters) {
        return new DriveXMeters(drive, meters);
    }

    public static Command driveTime(Drive drive, double seconds) {
        return Commands.deadline(
                Commands.waitSeconds(Math.abs(seconds)),
                Commands.runEnd(
                        () -> drive.drive(.5 * Math.signum(seconds), 0),
                        () -> drive.stop(),
                        drive));
    }

    public static Command printEnc(Drive drive) {
        return new PrintEnc(drive);
    }

    public static Command spkr2Leave(Shooter shooter, Intake intake, Drive drive) {
        return Commands.sequence(
                spkr1Leave(shooter, intake, drive),
                Autos.driveXMeters(drive, -AutonConstants.kLEAVEDistance),
                Commands.deadline(
                        Autos.shootCL(shooter, intake),
                        Commands.runEnd(
                                () -> drive.drive(-.5, 0),
                                () -> drive.stop(),
                                drive)));
    }

    public static Command spkr1Leave(Shooter shooter, Intake intake, Drive drive) {
        return Commands.sequence(
                Commands.deadline(
                        new LiveDelay(Constants.AutonConstants.kAutonStartDelayKey),
                        Commands.run(() -> drive.drive(0, 0), drive)),
                Autos.shootCL(shooter, intake),
                Commands.deadline(
                        Autos.driveXMeters(drive, AutonConstants.kLEAVEDistance),
                        Autos.intake(intake)));
    }

    public static Command leave(Drive drive) {
        return Commands.sequence(
                Commands.deadline(
                        new LiveDelay(Constants.AutonConstants.kAutonStartDelayKey),
                        Commands.run(() -> drive.drive(0, 0), drive)),
                Autos.driveXMeters(drive, AutonConstants.kLEAVEDistance));
    }

    public static Command intake(Intake intake) {
        return Commands.startEnd(() -> intake.run(), () -> intake.stop(), intake);
    }

    private Autos() {
        throw new UnsupportedOperationException("This is a utility class!");
    }
}
