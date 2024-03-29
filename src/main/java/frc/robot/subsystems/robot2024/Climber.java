// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.robot2024;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.filter.Debouncer;
import edu.wpi.first.math.filter.Debouncer.DebounceType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants.Robot2024Constants.ClimberConstants;

public class Climber extends SubsystemBase implements frc.robot.subsystems.Climber {

    private final DigitalInput leftLim;
    private final DigitalInput rightLim;
    private final CANSparkMax left;
    private final CANSparkMax right;

    private boolean currentAtStopping = false;
    private final Debouncer currentDebouncer;

    private boolean leftLimTriggered = false;
    private boolean rightLimTriggered = false;
    private final Debouncer leftDebouncer;
    private final Debouncer rightDebouncer;
    private boolean mustStop = false;
    private double speed;

    private static Climber instance;

    public static Climber getInstance() {
        return instance == null ? instance = new Climber() : instance;
    }

    private Climber() {
        left = new CANSparkMax(ClimberConstants.kLeftMotorID, MotorType.kBrushed);
        right = new CANSparkMax(ClimberConstants.kRightMotorID, MotorType.kBrushed);

        left.setIdleMode(IdleMode.kBrake);
        right.setIdleMode(IdleMode.kBrake);
        left.setInverted(true);
        right.setInverted(false);

        leftLim = new DigitalInput(ClimberConstants.kLeftLimPort);
        rightLim = new DigitalInput(ClimberConstants.kRightLimPort);

        currentDebouncer = new Debouncer(.1, DebounceType.kBoth);
        leftDebouncer = new Debouncer(.1, DebounceType.kBoth);
        rightDebouncer = new Debouncer(.1, DebounceType.kBoth);

    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        SmartDashboard.putNumber("speed", 0.2);
        speed = SmartDashboard.getNumber("speed", 0.2);
        leftLimTriggered = leftDebouncer.calculate(leftLim.get());
        rightLimTriggered = rightDebouncer.calculate(rightLim.get());
        updateCurrent();

        calcShouldStop();
    }

    private void updateCurrent() {
        double leftCurrent = left.getOutputCurrent();
        double rightCurrent = right.getOutputCurrent();
        SmartDashboard.putNumber("left current", leftCurrent);
        SmartDashboard.putNumber("right current", rightCurrent);

        boolean curTooHigh = (leftCurrent + rightCurrent) / 2.0 > ClimberConstants.kStoppingCurrent;
        // never need to come down
        currentAtStopping = currentDebouncer.calculate(curTooHigh);
    }

    private void calcShouldStop() {
        mustStop = mustStop || currentAtStopping;
        
        // logic for limsw
    }

    @Override
    public void extend() {
        left.set(speed);
        right.set(speed);
    }

    @Override
    public void retract() {
        double leftSpeed = -speed;
        double rightSpeed = -speed;
        if (mustStop) {
            stop();
        } else {
            if(leftLim.get()){
            leftSpeed = 0;}
            if(rightLim.get()){
            rightSpeed = 0;}
            right.set(rightSpeed);
            left.set(leftSpeed);
        }

    }

    @Override
    public void stop() {
        left.stopMotor();
        right.stopMotor();
    }
}
