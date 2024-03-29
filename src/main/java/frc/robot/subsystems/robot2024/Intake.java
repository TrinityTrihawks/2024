// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.robot2024;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.filter.Debouncer;
import edu.wpi.first.math.filter.Debouncer.DebounceType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Direction;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Robot2024Constants.IntakeConstants;

public class Intake extends SubsystemBase implements frc.robot.subsystems.Intake {

    private final CANSparkMax upperIntakeMotor = new CANSparkMax(
            IntakeConstants.kUpperIntakeId,
            MotorType.kBrushless);
    private final CANSparkMax lowerIntakeMotor = new CANSparkMax(
            IntakeConstants.kLowerIntakeId,
            MotorType.kBrushless);

    private final DigitalInput noteSwitch = new DigitalInput(IntakeConstants.kNoteSwitchID);
    private final Debouncer debouncer = new Debouncer(0.2, DebounceType.kBoth);
    private final Relay leds = new Relay(3, Direction.kForward);
    private boolean hasNote = false;

    private static Intake instance;

    public static Intake getInstance() {
        return instance == null ? instance = new Intake() : instance;
    }

    private Intake() {
        lowerIntakeMotor.setInverted(false);
        upperIntakeMotor.setInverted(true);
    }

    @Override
    public void periodic() {
        hasNote = !debouncer.calculate(noteSwitch.get());
        SmartDashboard.putBoolean("has note", hasNote);
        if (hasNote) {
            leds.set(Value.kForward);
        } else {
            leds.set(Value.kOff);
        }
    }

    @Override
    public void run() {
        upperIntakeMotor.set(IntakeConstants.kUpperIntakeSpeed);
        lowerIntakeMotor.set(IntakeConstants.kLowerIntakeSpeed);
    }

    @Override
    public void stop() {
        upperIntakeMotor.set(0);
        lowerIntakeMotor.set(0);
    }

    @Override
    public void reverse() {
        upperIntakeMotor.set(-IntakeConstants.kUpperIntakeReverseSpeed);
        lowerIntakeMotor.set(-IntakeConstants.kLowerIntakeReverseSpeed);
    }

    @Override
    public boolean hasNote() {
        return hasNote;
    }

}
