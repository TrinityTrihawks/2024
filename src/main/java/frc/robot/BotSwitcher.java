package frc.robot;

import frc.robot.subsystems.Drive;

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

    public static frc.robot.subsystems.Shooter getShooter() {

        switch (Robot.getRuntimeType()) {
            case kRoboRIO2:
                return frc.robot.subsystems.robot2024.Shooter.getInstance();

            default:
                return new DummyShooter();
        }
    }

    public static frc.robot.subsystems.Intake getIntake() {
        switch (Robot.getRuntimeType()) {
            case kRoboRIO2:
                return frc.robot.subsystems.robot2024.Intake.getInstance();

            default:
                return new DummyIntake();
        }
    }

}

class DummyShooter implements frc.robot.subsystems.Shooter {

    @Override
    public void run() {
    }

    @Override
    public void feed() {
    }

    @Override
    public void stop() {
    }

}

class DummyIntake implements frc.robot.subsystems.Intake {

    @Override
    public void run() {
    }

    @Override
    public void stop() {
    }

}
