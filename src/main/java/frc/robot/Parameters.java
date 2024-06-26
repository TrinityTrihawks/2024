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
            SmartDashboard.putNumber("staticTwistThrottle", staticTwistThrottle);

        }

        private static void update() {

            squareForwardInput = SmartDashboard.getBoolean("squareForwardInput", squareForwardInput);
            squareTwistInput = SmartDashboard.getBoolean("squareTwistInput", squareTwistInput);
            forwardThrottle = SmartDashboard.getNumber("forwardThrottle", forwardThrottle);
            twistThrottle = SmartDashboard.getNumber("twistThrottle", twistThrottle);
            staticTwistThrottle = SmartDashboard.getNumber("staticTwistThrottle", staticTwistThrottle);

            SmartDashboard.putBoolean("echo: squareForwardInput", squareForwardInput);
            SmartDashboard.putBoolean("echo: squareTwistInput", squareTwistInput);
            SmartDashboard.putNumber("echo: forwardThrottle", forwardThrottle);
            SmartDashboard.putNumber("echo: twistThrottle", twistThrottle);
            SmartDashboard.putNumber("echo: staticTwistThrottle", staticTwistThrottle);
        }

        public static boolean squareForwardInput = OperatorConstants.kSquareForwardInput;
        public static boolean squareTwistInput = OperatorConstants.kSquareTwistInput;

        public static double forwardThrottle = OperatorConstants.kForwardThrottle;
        public static double staticTwistThrottle = OperatorConstants.kStaticTwistThrottle;
        public static double twistThrottle = OperatorConstants.kTwistThrottle;
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

                SmartDashboard.putNumber("echo: aheadSlewValue", aheadSlewValue);
                SmartDashboard.putNumber("echo: rotateSlewValue", rotateSlewValue);
            }

            public static double aheadSlewValue = Robot2024Constants.DriveConstants.kAheadSlewValue;
            public static double rotateSlewValue = Robot2024Constants.DriveConstants.kRotateSlewValue;
        }

        public static class ShooterParameters {

            private static void init() {

                SmartDashboard.putNumber("upperShooterSpeed", upperShooterSpeed);
                SmartDashboard.putNumber("lowerShooterSpeed", lowerShooterSpeed);
                SmartDashboard.putNumber("upperAmpSpeed", upperAmpSpeed);
                SmartDashboard.putNumber("lowerAmpSpeed", lowerAmpSpeed);

            }

            private static void update() {

                upperShooterSpeed = SmartDashboard.getNumber("upperShooterSpeed", upperShooterSpeed);
                lowerShooterSpeed = SmartDashboard.getNumber("lowerShooterSpeed", lowerShooterSpeed);
                upperAmpSpeed = SmartDashboard.getNumber("upperAmpSpeed", upperAmpSpeed);
                lowerAmpSpeed = SmartDashboard.getNumber("lowerAmpSpeed", lowerAmpSpeed);

                SmartDashboard.putNumber("echo: upperShooterSpeed", upperShooterSpeed);
                SmartDashboard.putNumber("echo: lowerShooterSpeed", lowerShooterSpeed);
                SmartDashboard.putNumber("echo: upperAmpSpeed", upperAmpSpeed);
                SmartDashboard.putNumber("echo: lowerAmpSpeed", lowerAmpSpeed);
                
            }
               

            public static double upperShooterSpeed = Robot2024Constants.ShooterConstants.kUpperShooterSpeed;
            public static double lowerShooterSpeed = Robot2024Constants.ShooterConstants.kLowerShooterSpeed;
            public static double upperAmpSpeed = Robot2024Constants.ShooterConstants.kUpperAmpSpeed;
            public static double lowerAmpSpeed = Robot2024Constants.ShooterConstants.kLowerAmpSpeed;
        }
    }
}
