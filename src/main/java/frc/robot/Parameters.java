package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.OperatorConstants;
import frc.robot.Constants.Robot2024Constants;

/**
 * this class contains robot parameters that need to, at least for now,
 * be tuned from the smart dashboard. defaults are taken from {@link Constants}.
 */
public class Parameters {

    public static void init() {
        OperatorParameters.init();
        Robot2024Parameters.init();
    }

    public static void update() {
        OperatorParameters.update();
        Robot2024Parameters.update();
    }

    public static class OperatorParameters {

        private static void init() {

            SmartDashboard.putBoolean("squareForwardInput", squareForwardInput);
            SmartDashboard.putBoolean("squareTwistInput", squareTwistInput);
            SmartDashboard.putNumber("forwardThrottle", forwardThrottle);
            SmartDashboard.putNumber("twistThrottle", twistThrottle);

        }

        private static void update() {

            squareForwardInput = SmartDashboard.getBoolean("squareForwardInput", squareForwardInput);
            squareTwistInput = SmartDashboard.getBoolean("squareTwistInput", squareTwistInput);
            forwardThrottle = SmartDashboard.getNumber("forwardThrottle", forwardThrottle);
            twistThrottle = SmartDashboard.getNumber("twistThrottle", twistThrottle);
        }

        public static boolean squareForwardInput = OperatorConstants.kSquareForwardInput;
        public static boolean squareTwistInput = OperatorConstants.kSquareTwistInput;

        public static double forwardThrottle = .45;
        public static double twistThrottle = .25;
    }

    public static class Robot2024Parameters {

        private static void init() {
            DriveParameters.init();
            ShooterParameters.init();
        }

        private static void update() {
            DriveParameters.update();
            ShooterParameters.update();
        }

        public static class DriveParameters {

            private static void init() {

                SmartDashboard.putNumber("aheadSlewValue", aheadSlewValue);
                SmartDashboard.putNumber("rotateSlewValue", rotateSlewValue);
            }

            private static void update() {

                aheadSlewValue = SmartDashboard.getNumber("aheadSlewValue", aheadSlewValue);
                rotateSlewValue = SmartDashboard.getNumber("rotateSlewValue", rotateSlewValue);
            }

            public static double aheadSlewValue = Robot2024Constants.DriveConstants.kAheadSlewValue;
            public static double rotateSlewValue = Robot2024Constants.DriveConstants.kRotateSlewValue;
        }

        public static class ShooterParameters {

            private static void init() {

                SmartDashboard.putNumber("feederSpeed", feederSpeed);
                SmartDashboard.putNumber("upperShooterSpeed", upperShooterSpeed);
                SmartDashboard.putNumber("lowerShooterSpeed", lowerShooterSpeed);
                SmartDashboard.putNumber("shooterWarmupTime", shooterWarmupTime);
                SmartDashboard.putNumber("upperShooterReverseSpeed", upperShooterReverseSpeed);
                SmartDashboard.putNumber("lowerShooterReverseSpeed", lowerShooterReverseSpeed);

            }

            private static void update() {

                feederSpeed = SmartDashboard.getNumber("feederSpeed", feederSpeed);
                upperShooterSpeed = SmartDashboard.getNumber("upperShooterSpeed", upperShooterSpeed);
                lowerShooterSpeed = SmartDashboard.getNumber("lowerShooterSpeed", lowerShooterSpeed);
                shooterWarmupTime = SmartDashboard.getNumber("shooterWarmupTime", shooterWarmupTime);
                upperShooterReverseSpeed = SmartDashboard.getNumber("upperShooterReverseSpeed",
                        upperShooterReverseSpeed);
                lowerShooterReverseSpeed = SmartDashboard.getNumber("lowerShooterReverseSpeed",
                        lowerShooterReverseSpeed);

            }

            public static double feederSpeed = Robot2024Constants.ShooterConstants.kFeederSpeed;
            public static double upperShooterSpeed = Robot2024Constants.ShooterConstants.kUpperShooterSpeed;
            public static double lowerShooterSpeed = Robot2024Constants.ShooterConstants.kLowerShooterSpeed;
            public static double shooterWarmupTime = Robot2024Constants.ShooterConstants.kShooterWarmupTime;
            public static double upperShooterReverseSpeed = Robot2024Constants.ShooterConstants.kUpperShooterReverseSpeed;
            public static double lowerShooterReverseSpeed = Robot2024Constants.ShooterConstants.kLowerShooterReverseSpeed;
        }
    }
}
