package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Subsystem;

public interface Intake extends Subsystem {

    public void run();

    public void stop();

    public void reverse();

}
