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

    public static Command c2(Shooter shooter, Intake intake, Drive drive) {
        return Commands.sequence(
                c1(shooter, intake, drive),
                driveXMeters(drive, -AutonConstants.kLEAVEDistance),
                Commands.deadline(
                        shootCL(shooter, intake),
                        Commands.runEnd(
                                () -> drive.drive(-.2, 0),
                                () -> drive.stop(),
                                drive)));
    }

    public static Command c1(Shooter shooter, Intake intake, Drive drive) {
        return Commands.sequence(
                Commands.deadline(
                        new LiveDelay(Constants.AutonConstants.kAutonStartDelayKey),
                        Commands.run(() -> drive.drive(0, 0), drive)),
                shootCL(shooter, intake),
                Commands.deadline(
                        driveXMeters(drive, AutonConstants.kLEAVEDistance),
                        intake(intake)));
    }

    public static Command leave(Drive drive) {
        return Commands.sequence(
                Commands.deadline(
                        new LiveDelay(Constants.AutonConstants.kAutonStartDelayKey),
                        Commands.run(() -> drive.drive(0, 0), drive)),
                driveXMeters(drive, AutonConstants.kLEAVEDistance));
    }

    public static Command r1(Shooter shooter, Intake intake, Drive drive) {
        return Commands.sequence(
                Commands.deadline(
                        new LiveDelay(Constants.AutonConstants.kAutonStartDelayKey),
                        Commands.run(() -> drive.drive(0, 0), drive)),
                shootCL(shooter, intake),
                Commands.deadline(
                        driveOutRightTimed(drive),
                        intake(intake)),
                grabNote(drive, intake));
    }

    public static Command r2(Shooter shooter, Intake intake, Drive drive) {
        return Commands.sequence(
                r1(shooter, intake, drive),
                Commands.runEnd(
                        () -> drive.drive(-.6, -.2),
                        () -> drive.stop(),
                        drive).withTimeout(2),
                alignToApriltag(drive),
                Commands.run(() -> drive.drive(-.4, 0), drive),
                Commands.deadline(
                        shootCL(shooter, intake),
                        Commands.runEnd(
                                () -> drive.drive(-.3, 0),
                                () -> drive.stop(),
                                drive)));
    }

    public static Command l1(Shooter shooter, Intake intake, Drive drive) {
        return Commands.sequence(
                Commands.deadline(
                        new LiveDelay(Constants.AutonConstants.kAutonStartDelayKey),
                        Commands.run(() -> drive.drive(0, 0), drive)),
                shootCL(shooter, intake),
                Commands.deadline(
                        driveOutLeftTimed(drive),
                        intake(intake)),
                grabNote(drive, intake));
    }

    public static Command l2(Shooter shooter, Intake intake, Drive drive) {
        return Commands.sequence(
                l1(shooter, intake, drive),
                Commands.runEnd(
                        () -> drive.drive(-.6, .2),
                        () -> drive.stop(),
                        drive).withTimeout(2),
                alignToApriltag(drive),
                Commands.run(() -> drive.drive(-.4, 0), drive).withTimeout(.7),
                Commands.deadline(
                        shootCL(shooter, intake),
                        Commands.runEnd(
                                () -> drive.drive(-.3, 0),
                                () -> drive.stop(),
                                drive)));
    }

    public static Command intake(Intake intake) {
        return Commands.startEnd(() -> intake.run(), () -> intake.stop(), intake);
    }

    public static Command intakeOne(Intake intake) {
        return Commands.deadline(
                Commands.waitUntil(intake::hasNote),
                intake(intake));
    }

    public static Command grabNote(Drive drive, Intake intake) {
        return Commands.deadline(
                intakeOne(intake),
                followNote(drive));
    }

    public static Command alignToApriltag(Drive drive) {
        return new AprilLimelight(drive);
    }

    public static Command followNote(Drive drive) {
        return new NoteLimelight(drive);
    }

    private static Command driveOutRightTimed(Drive drive) {
        return Commands.sequence(
                Commands.runEnd(
                        () -> drive.drive(.5, 0),
                        () -> drive.stop(),
                        drive).withTimeout(.7),
                Commands.runEnd(
                        () -> drive.drive(0, .5),
                        () -> drive.stop(),
                        drive).withTimeout(1));
    }

    private static Command driveOutLeftTimed(Drive drive) {
        return Commands.sequence(
                Commands.runEnd(
                        () -> drive.drive(.5, 0),
                        () -> drive.stop(),
                        drive).withTimeout(.7),
                Commands.runEnd(
                        () -> drive.drive(0, -.5),
                        () -> drive.stop(),
                        drive).withTimeout(1));
    }

    private Autos() {
        throw new UnsupportedOperationException("This is a utility class!");
    }
}
