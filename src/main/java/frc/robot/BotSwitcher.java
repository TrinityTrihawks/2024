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
}
