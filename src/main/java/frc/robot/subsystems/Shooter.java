package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Subsystem;

public interface Shooter extends Subsystem {

    public void run();

    public void runClosedLoop();

    public void feed();

    public void stop();

    public void reverse();

}
