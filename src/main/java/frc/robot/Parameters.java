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

            SmartDashboard.putBoolean("echo: squareForwardInput", squareForwardInput);
            SmartDashboard.putBoolean("echo: squareTwistInput", squareTwistInput);
            SmartDashboard.putNumber("echo: forwardThrottle", forwardThrottle);
            SmartDashboard.putNumber("echo: twistThrottle", twistThrottle);
        }

        public static boolean squareForwardInput = OperatorConstants.kSquareForwardInput;
        public static boolean squareTwistInput = OperatorConstants.kSquareTwistInput;

        public static double forwardThrottle = OperatorConstants.kForwardThrottle;
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

                SmartDashboard.putNumber("feederSpeed", feederSpeed);
                SmartDashboard.putNumber("upperShooterSpeed", upperShooterSpeed);
                SmartDashboard.putNumber("lowerShooterSpeed", lowerShooterSpeed);
                SmartDashboard.putNumber("upperShooterReverseSpeed", upperShooterReverseSpeed);
                SmartDashboard.putNumber("lowerShooterReverseSpeed", lowerShooterReverseSpeed);
                SmartDashboard.putNumber("upperAmpSpeed", upperAmpSpeed);
                SmartDashboard.putNumber("lowerAmpSpeed", lowerAmpSpeed);

                SmartDashboard.putNumber("uP", uP);
                SmartDashboard.putNumber("uI", uI);
                SmartDashboard.putNumber("uFF", uFF);

                SmartDashboard.putNumber("lP", lP);
                SmartDashboard.putNumber("lI", lI);
                SmartDashboard.putNumber("lFF", lFF);

            }

            private static void update() {

                feederSpeed = SmartDashboard.getNumber("feederSpeed", feederSpeed);
                upperShooterSpeed = SmartDashboard.getNumber("upperShooterSpeed", upperShooterSpeed);
                lowerShooterSpeed = SmartDashboard.getNumber("lowerShooterSpeed", lowerShooterSpeed);
                upperShooterReverseSpeed = SmartDashboard.getNumber("upperShooterReverseSpeed",
                        upperShooterReverseSpeed);
                lowerShooterReverseSpeed = SmartDashboard.getNumber("lowerShooterReverseSpeed",
                        lowerShooterReverseSpeed);
                upperAmpSpeed = SmartDashboard.getNumber("upperAmpSpeed", upperAmpSpeed);
                lowerAmpSpeed = SmartDashboard.getNumber("lowerAmpSpeed", lowerAmpSpeed);

                uP = SmartDashboard.getNumber("uP", uP);
                uI = SmartDashboard.getNumber("uI", uI);
                uFF = SmartDashboard.getNumber("uFF", uFF);

                lP = SmartDashboard.getNumber("lP", lP);
                lI = SmartDashboard.getNumber("lI", lI);
                lFF = SmartDashboard.getNumber("lFF", lFF);

                SmartDashboard.putNumber("echo: feederSpeed", feederSpeed);
                SmartDashboard.putNumber("echo: upperShooterSpeed", upperShooterSpeed);
                SmartDashboard.putNumber("echo: lowerShooterSpeed", lowerShooterSpeed);
                SmartDashboard.putNumber("echo: upperShooterReverseSpeed", upperShooterReverseSpeed);
                SmartDashboard.putNumber("echo: lowerShooterReverseSpeed", lowerShooterReverseSpeed);
                SmartDashboard.putNumber("echo: upperAmpSpeed", upperAmpSpeed);
                SmartDashboard.putNumber("echo: lowerAmpSpeed", lowerAmpSpeed);

                SmartDashboard.putNumber("echo: uP", uP);
                SmartDashboard.putNumber("echo: uI", uI);
                SmartDashboard.putNumber("echo: uFF", uFF);

                SmartDashboard.putNumber("echo: lP", lP);
                SmartDashboard.putNumber("echo: lI", lI);
                SmartDashboard.putNumber("echo: lFF", lFF);

            }

            public static double feederSpeed = Robot2024Constants.ShooterConstants.kFeederSpeed;
            public static double upperShooterSpeed = Robot2024Constants.ShooterConstants.kUpperShooterSpeed;
            public static double lowerShooterSpeed = Robot2024Constants.ShooterConstants.kLowerShooterSpeed;
            public static double upperShooterReverseSpeed = Robot2024Constants.ShooterConstants.kUpperShooterReverseSpeed;
            public static double lowerShooterReverseSpeed = Robot2024Constants.ShooterConstants.kLowerShooterReverseSpeed;
            public static double upperAmpSpeed = Robot2024Constants.ShooterConstants.kUpperAmpSpeed;
            public static double lowerAmpSpeed = Robot2024Constants.ShooterConstants.kLowerAmpSpeed;

            public static double uP = Robot2024Constants.ShooterConstants.kUP;
            public static double uI = Robot2024Constants.ShooterConstants.kUI;
            public static double uFF = Robot2024Constants.ShooterConstants.kUFF;

            public static double lP = Robot2024Constants.ShooterConstants.kLP;
            public static double lI = Robot2024Constants.ShooterConstants.kLI;
            public static double lFF = Robot2024Constants.ShooterConstants.kLFF;
        }
    }
}
