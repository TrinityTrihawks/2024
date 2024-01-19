package frc.robot;

import frc.robot.subsystems.Drive;
import frc.robot.subsystems.RobotSubsystem;
import frc.robot.subsystems.robot2024.NotePath;

public final class BotSwitcher {

    public static Drive getDrive() {

        switch (Robot.getRuntimeType()) {

            case kSimulation:
                return frc.robot.subsystems.romi.Drivetrain.getInstance();

            case kRoboRIO:
                return frc.robot.subsystems.bilbot.Drivetrain.getInstance();

            case kRoboRIO2:
                return frc.robot.subsystems.robot2024.Drivetrain.getInstance();

            default:
                throw new IllegalStateException("STOP IT YOU CRACKER!!! -- The Code Genii");
        }
    }

    public static RobotSubsystem getSubsystem() {

        switch (Robot.getRuntimeType()) {
            case kRoboRIO2:
                return NotePath.getInstance();

            default:
                return new DummySubsystem();
        }
    }

    public static class DummySubsystem implements RobotSubsystem {

        @Override
        public void intake() {
        }

        @Override
        public void stopIntake() {
        }

        @Override
        public void runShooter() {
        }

        @Override
        public void stopShooter() {
        }

        @Override
        public void shoot() {
        }

    }
}
