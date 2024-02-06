// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

/*
 * DO NOT TOUCH THIS FILE!
 */

package frc.robot.subsystems.romi;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.Drive;

//TODO: make this work
public class Drivetrain extends SubsystemBase implements Drive {

    private static final double kCountsPerRevolution = 1440.0;
    // DO NOT KILL THIS LINE (below) if we need inches we might want it... ;)
    private static final double kWheelDiameterInch = 2.75591; // 70 mm
    private static final double kWheelDiameterMeters = .070; // 70 mm

    // The Romi has the left and right motors set to
    // PWM channels 0 and 1 respectively
    private final Spark leftMotor = new Spark(0);
    private final Spark rightMotor = new Spark(1);

    // The Romi has onboard encoders that are hardcoded
    // to use DIO pins 4/5 and 6/7 for the left and right
    private final Encoder leftEncoder = new Encoder(4, 5);
    private final Encoder rightEncoder = new Encoder(6, 7);

    // Set up the differential drive controller
    private final DifferentialDrive diffDrive = new DifferentialDrive(leftMotor::set, rightMotor::set);

    private static Drivetrain instance;

    public static Drivetrain getInstance() {
        return instance == null ? instance = new Drivetrain() : instance;
    }

    private Drivetrain() {
        // Use inches as unit for encoder distances
        leftEncoder.setDistancePerPulse((Math.PI * kWheelDiameterInch) / kCountsPerRevolution);
        rightEncoder.setDistancePerPulse((Math.PI * kWheelDiameterInch) / kCountsPerRevolution);
        resetEncoders();

        // Invert right side since motor is flipped
        rightMotor.setInverted(true);
    }

    public void arcadeDrive(double xaxisSpeed, double zaxisRotate) {
        diffDrive.arcadeDrive(xaxisSpeed, zaxisRotate);
    }

    public void resetEncoders() {
        leftEncoder.reset();
        rightEncoder.reset();
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run during simulation
    }

    @Override
    public void drive(double x, double z) {
        arcadeDrive(x, z);
    }

    @Override
    public void stop() {
        arcadeDrive(0, 0);
    }

    @Override
    public double getGyroX() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getGyroY() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getGyroZ() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getEncoderDistanceLeft() {
        return leftEncoder.getDistance();
    }

    @Override
    public double getEncoderDistanceRight() {
        return rightEncoder.getDistance();
    }

    @Override
    public void brakeIdle() {
    }

    @Override
    public void noBrakeIdle() {
    }

    @Override
    public double getEncoderVelocityLeft() {
        return 0;
        // TODO Auto-generated method stub
    }

    @Override
    public double getEncoderVelocityRight() {
        // TODO Auto-generated method stub
        return 0;
    }
}
