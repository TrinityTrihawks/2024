package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Subsystem;

public interface Drive extends Subsystem {
    
    /**
     * 
     * @param x -- forward
     * @param z -- rotate
     */
    public void drive(double x, double z);

    public void stop();
    public void brakeIdle();
    public void noBrakeIdle();

    public double getGyroX();
    public double getGyroY();
    public double getGyroZ();

    public void resetEncoders();
    /**
     * 
     * @return meters
     */
    public double getEncoderDistanceLeft(); 
    /**
     * 
     * @return meters
     */
    public double getEncoderDistanceRight();
    /**
     * @return meters / sec
     */
    public double getEncoderVelocityLeft();
    /**
     * @return meters / sec
     */
    public double getEncoderVelocityRight();

}
