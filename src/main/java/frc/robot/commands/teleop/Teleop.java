package frc.robot.commands.teleop;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import frc.robot.commands.aouton.Shoot;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class Teleop {

    public static Command arcadeDrive(Drive drive, DoubleSupplier forward, DoubleSupplier twist) {
        return new ArcadeDrive(drive, forward, twist);
    }

    /**
     * porcelain command to run the basic shoot sequence
     */
    public static Command pushToShoot(Shooter shooter) {
        return new Shoot(shooter);
    }

    /**
     * use this in conjunction with Teleop.warmShooter().
     * it spins up the feeder wheel on start and stops
     * all wheels on end.
     */
    public static Command shoot(Shooter shooter) {
        return new StartEndCommand(() -> shooter.feed(), () -> shooter.stop(), shooter);
    }

    /**
     * one-shot starts the shooter wheel starting
     * not the *most* motor-safe thing ever... but it's
     * ok
     */
    public static Command warmShooter(Shooter shooter) {
        return Commands.runOnce(() -> shooter.run(), shooter);
    }

    public static Command runIntake(Intake intake) {
        return new StartEndCommand(() -> intake.run(), () -> intake.stop(), intake);
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
                intake);
    }

    private Teleop() {
        throw new UnsupportedOperationException("utility class");
    }
}
