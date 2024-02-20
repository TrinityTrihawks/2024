// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.robot2024;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkBase.ControlType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Robot2024Constants.ShooterConstants;
import frc.robot.Parameters.Robot2024Parameters.ShooterParameters;

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

    private final RelativeEncoder upperShooterEncoder = upperShooterMotor.getEncoder();
    private final RelativeEncoder lowerShooterEncoder = lowerShooterMotor.getEncoder();

    private final SparkPIDController upperPID = upperShooterMotor.getPIDController();
    private final SparkPIDController lowerPID = lowerShooterMotor.getPIDController();

    public static Shooter getInstance() {
        return instance == null ? instance = new Shooter() : instance;
    }

    private Shooter() {
        upperShooterMotor.setInverted(false);
        lowerShooterMotor.setInverted(true);
        feederMotor.setInverted(true);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run

        upperPID.setP(ShooterParameters.uP);
        upperPID.setI(ShooterParameters.uI);
        upperPID.setFF(ShooterParameters.uFF);
        upperPID.setOutputRange(-1, 1);

        lowerPID.setP(ShooterParameters.lP);
        lowerPID.setI(ShooterParameters.lI);
        lowerPID.setFF(ShooterParameters.lFF);
        lowerPID.setOutputRange(-1, 1);

    }

    public void run() {
        upperShooterMotor.set(ShooterParameters.upperShooterSpeed);
        lowerShooterMotor.set(ShooterParameters.lowerShooterSpeed);
    }

    public void stop() {
        upperShooterMotor.set(0);
        lowerShooterMotor.set(0);
        feederMotor.set(0);
    }

    public void feed() {
        feederMotor.set(ShooterParameters.feederSpeed);
    }

    @Override
    public void reverse() {
        feederMotor.set(-ShooterParameters.feederSpeed);
        upperShooterMotor.set(-ShooterParameters.upperShooterReverseSpeed);
        lowerShooterMotor.set(-ShooterParameters.lowerShooterReverseSpeed);
    }

    @Override
    public void runClosedLoop() {
        upperPID.setReference(ShooterParameters.upperShooterSpeed * ShooterConstants.kShooterWheelMaxRPM,
                ControlType.kVelocity);
        lowerPID.setReference(ShooterParameters.lowerShooterSpeed * ShooterConstants.kShooterWheelMaxRPM,
                ControlType.kVelocity);
    }

}
