package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Subsystem;

public interface Intake extends Subsystem {

    public void run();

    public void stop();

    public void reverse();

    public boolean hasNote();

    public Limelight getLimelight();

    public interface Limelight {

        public boolean hasTarget();

        public double getXOffset();

    }

}
