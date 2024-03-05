// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.robot2024;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkRelativeEncoder;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.ADIS16470_IMU;
import edu.wpi.first.wpilibj.ADIS16470_IMU.IMUAxis;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.Drive;
import frc.robot.Constants.Robot2024Constants.DriveConstants;
import frc.robot.Parameters.Robot2024Parameters.DriveParameters;

public class Drivetrain extends SubsystemBase implements Drive {

    private final CANSparkMax leftLeader = new CANSparkMax(DriveConstants.kLeftLeaderId, MotorType.kBrushless);
    private final CANSparkMax leftFollower = new CANSparkMax(DriveConstants.kLeftFollowerId, MotorType.kBrushless);
    private final CANSparkMax rightLeader = new CANSparkMax(DriveConstants.kRightLeaderId, MotorType.kBrushless);
    private final CANSparkMax rightFollower = new CANSparkMax(DriveConstants.kRightFollowerId, MotorType.kBrushless);

    private final CANSparkMax[] sparks = new CANSparkMax[] {
            leftLeader, rightLeader,
            leftFollower, rightFollower
    };

    private final RelativeEncoder leftEncoder = leftLeader.getEncoder(SparkRelativeEncoder.Type.kHallSensor,
            DriveConstants.kEncoderCPR);
    private final RelativeEncoder rightEncoder = rightLeader.getEncoder(SparkRelativeEncoder.Type.kHallSensor,
            DriveConstants.kEncoderCPR);

    private final ADIS16470_IMU gyro = new ADIS16470_IMU();

    private SlewRateLimiter speedLimiter = new SlewRateLimiter(DriveParameters.aheadSlewValue);
    private SlewRateLimiter twistLimiter = new SlewRateLimiter(DriveParameters.rotateSlewValue);

    private final DifferentialDrive drive;

    private static Drivetrain instance;

    public static Drivetrain getInstance() {
        return instance == null ? instance = new Drivetrain() : instance;
    }

    private Drivetrain() {
        rightLeader.setInverted(true);
        leftLeader.setInverted(false);
        leftFollower.follow(leftLeader);
        rightFollower.follow(rightLeader);
        for (CANSparkMax spark : sparks) {
            spark.clearFaults();
        }

        leftEncoder.setPositionConversionFactor(DriveConstants.kMotorRotationsToMeters);
        rightEncoder.setPositionConversionFactor(DriveConstants.kMotorRotationsToMeters);
        leftEncoder.setVelocityConversionFactor(DriveConstants.kMotorRPMToMetersPerSecond);
        rightEncoder.setVelocityConversionFactor(DriveConstants.kMotorRPMToMetersPerSecond);

        drive = new DifferentialDrive(leftLeader::setVoltage, rightLeader::setVoltage);
        drive.setMaxOutput(DriveConstants.kMaxDriveVoltage);

        resetEncoders();
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    @Override
    public void drive(double x, double z) {
        drive.arcadeDrive(
                speedLimiter.calculate(x),
                -twistLimiter.calculate(z),
                false);
    }

    @Override
    public void stop() {
        drive.arcadeDrive(0, 0);
        speedLimiter.reset(0);
        twistLimiter.reset(0);
    }

    @Override
    public double getGyroX() {
        return gyro.getAngle(IMUAxis.kX);
    }

    @Override
    public double getGyroY() {
        return gyro.getAngle(IMUAxis.kY);
    }

    @Override
    public double getGyroZ() {
        return gyro.getAngle(IMUAxis.kZ);
    }

    @Override
    public double getEncoderDistanceLeft() {
        return leftEncoder.getPosition();
    }

    @Override
    public double getEncoderDistanceRight() {
        return rightEncoder.getPosition();
    }

    @Override
    public void brakeIdle() {
        for (CANSparkMax spark : sparks) {
            spark.setIdleMode(IdleMode.kBrake);
        }
    }

    @Override
    public void noBrakeIdle() {
        for (CANSparkMax spark : sparks) {
            spark.setIdleMode(IdleMode.kCoast);
        }
    }

    @Override
    public void resetEncoders() {
        leftEncoder.setPosition(0);
        rightEncoder.setPosition(0);
    }

    @Override
    public double getEncoderVelocityLeft() {
        return leftEncoder.getVelocity();
    }

    @Override
    public double getEncoderVelocityRight() {
        return rightEncoder.getVelocity();
    }
}
