// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.robot2024;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.Drive;

public class Drivetrain extends SubsystemBase implements Drive {

    private static Drivetrain instance;
    
    public static Drivetrain getInstance() {
        return instance == null ? instance = new Drivetrain() : instance;
    }

    private Drivetrain() {
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    @Override
    public void drive(double x, double z) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'drive'");
    }

    @Override
    public void stop() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'stop'");
    }

    @Override
    public double getGyroX() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getGyroX'");
    }

    @Override
    public double getGyroY() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getGyroY'");
    }

    @Override
    public double getGyroZ() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getGyroZ'");
    }

    @Override
    public double getEncoderDistanceLeft() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEncoderDistanceLeft'");
    }

    @Override
    public double getEncoderDistanceRight() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEncoderDistanceRight'");
    }

    @Override
    public void brakeIdle() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'brakeIdle'");
    }

    @Override
    public void noBrakeIdle() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'noBrakeIdle'");
    }

    @Override
    public void resetEncoders() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'resetEncoders'");
    }
}
