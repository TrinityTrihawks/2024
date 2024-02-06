// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.robot2024;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Robot2024Constants.NotePathConstants;
import frc.robot.subsystems.RobotSubsystem;

public class NotePath extends SubsystemBase implements RobotSubsystem {

    private final CANSparkMax upperIntakeMotor = new CANSparkMax(
            NotePathConstants.kUpperIntakeId,
            MotorType.kBrushless);
    private final CANSparkMax lowerIntakeMotor = new CANSparkMax(
            NotePathConstants.kLowerIntakeId,
            MotorType.kBrushless);
    private final CANSparkMax feederMotor = new CANSparkMax(
            NotePathConstants.kFeederId,
            MotorType.kBrushless);
    private final CANSparkMax lowerShooterMotor = new CANSparkMax(
            NotePathConstants.kLowerShooterId,
            MotorType.kBrushless);
    private final CANSparkMax upperShooterMotor = new CANSparkMax(
            NotePathConstants.kUpperShooterId,
            MotorType.kBrushless);

    private static NotePath instance;

    public static NotePath getInstance() {
        return instance == null ? instance = new NotePath() : instance;
    }

    private NotePath() {
        upperShooterMotor.setInverted(false);
        lowerShooterMotor.setInverted(true);
        feederMotor.setInverted(true);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    public void intake() {
        upperIntakeMotor.set(NotePathConstants.kUpperIntakeSpeed);
        lowerIntakeMotor.set(NotePathConstants.kLowerIntakeSpeed);
    }

    public void stopIntake() {
        upperIntakeMotor.set(0);
        lowerIntakeMotor.set(0);
    }

    public void runShooter() {
        upperShooterMotor.set(NotePathConstants.kUpperShooterSpeed);
        lowerShooterMotor.set(NotePathConstants.kLowerShooterSpeed);
    }

    public void stopShooter() {
        upperShooterMotor.set(0);
        lowerShooterMotor.set(0);
        feederMotor.set(0);
    }

    public void shoot() {
        feederMotor.set(NotePathConstants.kFeederSpeed);
    }

    @Override
    public void reverseIntake() {
        upperIntakeMotor.set(NotePathConstants.kUpperIntakeReverseSpeed);
        lowerIntakeMotor.set(NotePathConstants.kLowerIntakeReverseSpeed);
    }

}
