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
        public static final int kSubsysControllerPort = 1;

        public static final boolean kSquareForwardInput = false;
        public static final boolean kSquareTwistInput = false;

        public static final double kForwardThrottle = .7;
        public static final double kTwistThrottle = .4;
        public static final double kStaticTwistThrottle = .6;
    }

    public static class AutonConstants {
        public static final double kLEAVEDistance = 1.7;
        public static final String kAutonStartDelayKey = "Auton Start Delay";
        public static final double kAngledLEAVETime = 1.5;
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

            public static final double kAheadSlewValue = 2.2;
            public static final double kRotateSlewValue = 2.7;

            public static final double kGearBoxRatio = 1.0 / 12.75;
            public static final double kWheelRotationsToMeters = .584;
            public static final double kFudgeFactor = 1.609;
            public static final double kMotorRotationsToMeters = kGearBoxRatio * kWheelRotationsToMeters * kFudgeFactor;
            public static final double kMotorRPMToMetersPerSecond = kMotorRotationsToMeters / 60.0;

        }

        public static class ShooterConstants {

            public static final int kFeederId = 17;
            public static final int kLowerShooterId = 18;
            public static final int kUpperShooterId = 19;
            public static final double kFeederSpeed = .5;
            public static final double kUpperShooterSpeed = .95;
            public static final double kLowerShooterSpeed = .75;
            public static final double kShooterWarmupTime = .55; // seconds
            public static final double kUpperShooterReverseSpeed = .4;
            public static final double kLowerShooterReverseSpeed = .4;
            public static final double kUpperAmpSpeed = .15;
            public static final double kLowerAmpSpeed = .2;

            public static final double kShooterWheelMaxRPM = 5500;

            public static final double kUP = 2e-5;
            public static final double kUI = 0;
            public static final double kUFF = 1.92e-4;

            public static final double kLP = 2e-5;
            public static final double kLI = 0;
            public static final double kLFF = 1.92e-4;

            public static final String kLimelightNostname = "limelight-shooter";
            public static final int kRedTargetID = 4;
            public static final int kBlueTargetID = 7;

        }

        public static class IntakeConstants {

            public static final int kUpperIntakeId = 15;
            public static final int kLowerIntakeId = 16;
            public static final double kUpperIntakeSpeed = 1;
            public static final double kLowerIntakeSpeed = 1;
            public static final int kUpperIntakeReverseSpeed = 1;
            public static final int kLowerIntakeReverseSpeed = 1;
            public static final int kNoteSwitchID = 1;

            public static class LimelightConstants {

                public static final double kWidestDetectableNoteAngle = 35;
            }
        }

        public static class ClimberConstants {

            // TODO: get empirical value for hard stop current: will need Phoenix Tuner...
            public static final double kStoppingCurrent = 50.0;
            public static final int kLeftMotorID = 21;
            public static final int kRightMotorID = 20;
            public static final int kLeftLimPort = 2;
            public static final int kRightLimPort = 3;
        }
    }
}
