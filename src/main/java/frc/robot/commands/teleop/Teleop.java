package frc.robot.commands.teleop;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import frc.robot.commands.aouton.Shoot;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.RobotSubsystem;

public class Teleop {

    public static Command arcadeDrive(Drive drive, DoubleSupplier forward, DoubleSupplier twist) {
        return new ArcadeDrive(drive, forward, twist);
    }

    /**
     * porcelain command to run the basic shoot sequence
     */
    public static Command pushToShoot(RobotSubsystem subsys) {
        return new Shoot(subsys);
    }

    /**
     * use this in conjunction with Teleop.warmShooter().
     * it spins up the feeder wheel on start and stops
     * all wheels on end.
     */
    public static Command shoot(RobotSubsystem subsys) {
        return new StartEndCommand(() -> subsys.shoot(), () -> subsys.stopShooter(), subsys);
    }

    public static Command runIntake(RobotSubsystem subsys) {
        return new StartEndCommand(() -> subsys.intake(), () -> subsys.stopIntake(), subsys);
    }

    private Teleop() {
        throw new UnsupportedOperationException("utility class");
    }
}
