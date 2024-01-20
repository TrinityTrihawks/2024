package frc.robot.commands.test;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.commands.teleop.Teleop;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.RobotSubsystem;

public class Testing {

    public static Command preFlight(TestArgs args) {
        return Commands.sequence(
            testDriverControls(args),
            testAutos(args),
            Commands.print("preflight checklist complete!")
        );
    }
    
    private static Command testAutos(TestArgs args) {

        return Commands.sequence(
            // put auton routines to test here
        );
    }

    private static Command testDriverControls(TestArgs args) {

        final TestState testCompletions = new TestState();

        return Commands.sequence(
            Commands.runOnce(() -> SmartDashboard.putData("Advance Test", Commands.run(() -> testCompletions.testedArcadeDrive = true))),
            Commands.deadline(Commands.waitUntil(() -> testCompletions.testedArcadeDrive), Teleop.arcadeDrive(args.drive, args.forward, args.twist)),

            Commands.runOnce(() -> SmartDashboard.putData("Advance Test", Commands.run(() -> testCompletions.testedShooter = true))),
            Commands.deadline(Commands.waitUntil(() -> testCompletions.testedShooter), Teleop.pushToShoot(args.subsys))
        );
    }

    private static class TestState {
        
        private boolean testedArcadeDrive = false;
        private boolean testedShooter = false;
        
    }

    public static class TestArgs {
        private Drive drive;
        private RobotSubsystem subsys;
        private DoubleSupplier forward;
        private DoubleSupplier twist;

        public TestArgs(Drive drive, RobotSubsystem subsys, DoubleSupplier forward, DoubleSupplier twist) {
            this.drive = drive;
            this.subsys = subsys;
            this.twist = twist;
            this.forward = forward;
        }
    }

    private Testing() {
        throw new UnsupportedOperationException("utility class");
    }
}
