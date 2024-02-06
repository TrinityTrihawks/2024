// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.robot2024;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Robot2024Constants.ShooterConstants;

public class Shooter extends SubsystemBase implements frc.robot.subsystems.Shooter {

    private static Shooter instance;

    private final CANSparkMax feederMotor = new CANSparkMax(
            ShooterConstants.kFeederId,
            MotorType.kBrushless);
    private final CANSparkMax lowerShooterMotor = new CANSparkMax(
            ShooterConstants.kLowerShooterId,
            MotorType.kBrushless);
    private final CANSparkMax upperShooterMotor = new CANSparkMax(
            ShooterConstants.kUpperShooterId,
            MotorType.kBrushless);

    public static Shooter getInstance() {
        return instance == null ? instance = new Shooter() : instance;
    }

    private Shooter() {
        upperShooterMotor.setInverted(true);
        lowerShooterMotor.setInverted(false); // maybe switch
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    public void run() {
        upperShooterMotor.set(ShooterConstants.kUpperShooterSpeed);
        lowerShooterMotor.set(ShooterConstants.kLowerShooterSpeed);
    }

    public void stop() {
        upperShooterMotor.set(0);
        lowerShooterMotor.set(0);
        feederMotor.set(0);
    }

    public void feed() {
        feederMotor.set(ShooterConstants.kFeederSpeed);
    }
}
