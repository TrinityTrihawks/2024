// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.robot2024;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Robot2024Constants.IntakeConstants;

public class Intake extends SubsystemBase implements frc.robot.subsystems.Intake {

    private final CANSparkMax upperIntakeMotor = new CANSparkMax(
            IntakeConstants.kUpperIntakeId,
            MotorType.kBrushless);
    private final CANSparkMax lowerIntakeMotor = new CANSparkMax(
            IntakeConstants.kLowerIntakeId,
            MotorType.kBrushless);

    private static Intake instance;

    public static Intake getInstance() {
        return instance == null ? instance = new Intake() : instance;
    }

    private Intake() {
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    public void run() {
        upperIntakeMotor.set(IntakeConstants.kUpperIntakeSpeed);
        lowerIntakeMotor.set(IntakeConstants.kLowerIntakeSpeed);
    }

    public void stop() {
        upperIntakeMotor.set(0);
        lowerIntakeMotor.set(0);
    }

    @Override
    public void reverse() {
        upperIntakeMotor.set(-IntakeConstants.kUpperIntakeReverseSpeed);
        lowerIntakeMotor.set(-IntakeConstants.kLowerIntakeReverseSpeed);
    }

}
