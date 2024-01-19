package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Subsystem;

public interface RobotSubsystem extends Subsystem {
    
    public void intake();

    public void stopIntake();

    public void runShooter();

    public void stopShooter();

    public void shoot();
}
