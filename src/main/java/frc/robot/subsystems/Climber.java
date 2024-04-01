package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Subsystem;

public interface Climber extends Subsystem {

    /**
     * extend the climber [until eot?]
     */
    public void extend();

    /**
     * retract climber until eot, then no-op
     */
    public void retract();

    public void stop();
}
