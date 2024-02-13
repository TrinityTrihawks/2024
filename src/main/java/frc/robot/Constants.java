// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    public static final double kDrivetrainStopDeadzone = 0.1;

    public static class OperatorConstants {
        public static final int kDriverControllerPort = 0;

        public static final boolean kSquareForwardInput = false;
        public static final boolean kSquareTwistInput = false;

        public static final int kSubsysControllerPort = 1;
    }

    public static class RomiConstants {

    }

    public static class BilbotConstants {
        public static class DriveConstants {

            public static final int kLeftLeaderId = 12;
            public static final int kLeftFollowerId = 14;
            public static final int kRightLeaderId = 11;
            public static final int kRightFollowerId = 13;
            public static final int kEncoderCPR = 42;
            public static final double kSlewValue = 2.5;

            private static final double kGearboxRatio = 1 / 12.75;
            private static final double kWheelDiameterInches = 6;
            private static final double kMetersPerInch = 2.54 / 100.0;
            private static final double kFudgeFactor = 1.8;
            public static final double kRotationsToMeters = kGearboxRatio
                    * (Math.PI * kWheelDiameterInches * kMetersPerInch) * kFudgeFactor;
            public static final double kRPMToMetersPerSecond = kRotationsToMeters / 60.0;

        }
    }

    public static class Robot2024Constants {

        public static class DriveConstants {

            public static final int kLeftLeaderId = 11;
            public static final int kLeftFollowerId = 13;
            public static final int kRightLeaderId = 12;
            public static final int kRightFollowerId = 14;
            public static final int kEncoderCPR = 42;
            public static final double kSlewValue = 2;
            public static final double kGearBoxRatio = 12.75; // TODO ?
            public static final double kWheelDiameterInches = 8; // TODO ish
            public static final double kMetersPerInch = 1.0 / 100.0 * 2.54;
            public static final double kMotorRotationsToMeters = kGearBoxRatio * Math.PI * kWheelDiameterInches
                    * kMetersPerInch;
            public static final double kMotorRPMToMetersPerSecond = kMotorRotationsToMeters / 60.0;

        }

        public static class ShooterConstants {

            public static final int kFeederId = 17;
            public static final int kLowerShooterId = 18;
            public static final int kUpperShooterId = 19;
            public static final double kFeederSpeed = .5;
            public static final double kUpperShooterSpeed = .8;
            public static final double kLowerShooterSpeed = .8;
            public static final double kShooterWarmupTime = .5; // seconds
            public static final double kUpperShooterReverseSpeed = .4;
            public static final double kLowerShooterReverseSpeed = .4;

        }

        public static class IntakeConstants {

            public static final int kUpperIntakeId = 15;
            public static final int kLowerIntakeId = 16;
            public static final double kUpperIntakeSpeed = 1;
            public static final double kLowerIntakeSpeed = 1;
            public static final int kUpperIntakeReverseSpeed = 1;
            public static final int kLowerIntakeReverseSpeed = 1;

        }

    }
}
