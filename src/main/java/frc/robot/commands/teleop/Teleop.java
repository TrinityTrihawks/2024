package frc.robot.commands.teleop;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.RobotSubsystem;

public class Teleop {

    public static Command arcadeDrive(Drive drive, DoubleSupplier forward, DoubleSupplier twist) {
        return new ArcadeDrive(drive, forward, twist);
    }
    
    public static Command runIntake(RobotSubsystem subsys) {
        return new StartEndCommand(() -> subsys.intake(), () -> subsys.stopIntake(), subsys);
    }

    private Teleop() {
        throw new UnsupportedOperationException("utility class");
    }
}
