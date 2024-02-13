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

    /**
     * Returns a shooter for the current robot. Returns a no-op dummy
     * if the current robot is not Tritone
     */
    public static frc.robot.subsystems.Shooter getShooter() {

        switch (Robot.getRuntimeType()) {
            case kRoboRIO2:
                return frc.robot.subsystems.robot2024.Shooter.getInstance();

            default:
                return new DummyShooter();
        }
    }

    /**
     * Returns an intake for the current robot. Returns a no-op dummy
     * if the current robot is not Tritone
     */
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
        System.out.println("dummy shooter running...");
    }

    @Override
    public void feed() {
        System.out.println("dummy shooter shooting...");
    }

    @Override
    public void stop() {
        System.out.println("dummy shooter stopping...");
    }

    @Override
    public void reverse() {
        System.out.println("dummy shooter reversing...");
    }

}

class DummyIntake implements frc.robot.subsystems.Intake {

    @Override
    public void run() {
        System.out.println("dummy intake running...");
    }

    @Override
    public void stop() {
        System.out.println("dummy intake stopping...");
    }

    @Override
    public void reverse() {
        System.out.println("dummy intake reversing...");
    }

}
