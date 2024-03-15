package frc.robot.commands.teleop;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.aouton.Autos;
import frc.robot.commands.aouton.Shoot;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class Teleop {

    public static Command arcadeDrive(Drive drive, DoubleSupplier forward, DoubleSupplier twist) {
        return new ArcadeDrive(drive, forward, twist);
    }

    public static Command pushToShoot(Shooter shooter, Intake intake) {
        return Commands.deferredProxy(() -> new Shoot(shooter, intake));
    }

    /**
     * porcelain command to run the shoot sequence
     */
    public static Command pushToShootCL(Shooter shooter, Intake intake) {
        return Commands.deferredProxy(() -> Autos.shootCL(shooter, intake));
    }

    public static Command pushToShootCLAmp(Shooter shooter, Intake intake) {
        return Commands.deferredProxy(() -> Autos.shootCLAmp(shooter, intake));
    }

    /**
     * use this in conjunction with Teleop.warmShooter().
     * it spins up the feeder wheel on start and stops
     * all wheels on end.
     */
    public static Command shoot(Shooter shooter, Intake intake) {
        return new StartEndCommand(
                () -> {
                    shooter.feed();
                    intake.run();
                }, () -> {
                    shooter.stop();
                    intake.stop();
                }, shooter, intake);
    }

    /**
     * one-shot starts the shooter wheel starting
     * not the *most* motor-safe thing ever... but it's
     * ok
     */
    public static Command warmShooter(Shooter shooter) {
        return Commands.runOnce(() -> shooter.run(), shooter);
    }

    public static Command runIntake(Intake intake, CommandXboxController ctlr) {
        return new FunctionalCommand(
                () -> intake.run(),
                () -> {
                    if (intake.hasNote()) {
                        ctlr.getHID().setRumble(RumbleType.kBothRumble, 1);
                    } else {
                        ctlr.getHID().setRumble(RumbleType.kBothRumble, 0);
                    }
                },
                (b) -> {
                    intake.stop();
                    ctlr.getHID().setRumble(RumbleType.kBothRumble, 0);
                },
                () -> false,
                intake);
    }

    public static Command runReverseIntakeAndShooter(Intake intake, Shooter shooter) {
        return new StartEndCommand(
                () -> {
                    shooter.reverse();
                    intake.reverse();
                },
                () -> {
                    intake.stop();
                    shooter.stop();
                },
                intake, shooter);
    }

    private Teleop() {
        throw new UnsupportedOperationException("utility class");
    }
}
