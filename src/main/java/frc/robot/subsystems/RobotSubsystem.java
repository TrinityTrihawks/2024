package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Subsystem;

public interface RobotSubsystem extends Subsystem {
    
    public void intake();
    
    public void reverseIntake();
    
    public void stopIntake();

    public void runShooter();

    /**
     * stop feeder and shooter wheels
     */
    public void stopShooter();

    public void shoot();

}
