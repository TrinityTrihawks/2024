// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.robot2024;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
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

    private final SparkPIDController upperPID = upperShooterMotor.getPIDController();
    private final SparkPIDController lowerPID = lowerShooterMotor.getPIDController();

    public static Shooter getInstance() {
        return instance == null ? instance = new Shooter() : instance;
    }

    private Shooter() {

        upperShooterMotor.setInverted(false);
        lowerShooterMotor.setInverted(false);

        feederMotor.setInverted(true);
        upperPID.setP(ShooterConstants.kUP);
        upperPID.setI(ShooterConstants.kUI);
        upperPID.setFF(ShooterConstants.kUFF);
        lowerPID.setP(ShooterConstants.kLP);
        lowerPID.setI(ShooterConstants.kLI);
        lowerPID.setFF(ShooterConstants.kLFF);
        upperPID.setOutputRange(-ShooterConstants.kShooterWheelMaxRPM, ShooterConstants.kShooterWheelMaxRPM);
        lowerPID.setOutputRange(-ShooterConstants.kShooterWheelMaxRPM, ShooterConstants.kShooterWheelMaxRPM);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run

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
        feederMotor.set(ShooterConstants.kFeederSpeed);
    }

    @Override
    public void reverse() {
        feederMotor.set(-ShooterConstants.kFeederSpeed);
        upperShooterMotor.set(-ShooterConstants.kUpperShooterReverseSpeed);
        lowerShooterMotor.set(-ShooterConstants.kLowerShooterReverseSpeed);
    }

    @Override
    public void runCL() {
        upperPID.setReference(ShooterParameters.upperShooterSpeed * ShooterConstants.kShooterWheelMaxRPM,
                ControlType.kVelocity);
        lowerPID.setReference(ShooterParameters.lowerShooterSpeed * ShooterConstants.kShooterWheelMaxRPM,
                ControlType.kVelocity);
    }

    @Override
    public void runCLAmp() {
        upperShooterMotor.set(ShooterParameters.upperAmpSpeed);
        lowerShooterMotor.set(ShooterParameters.lowerAmpSpeed);
    }

}
