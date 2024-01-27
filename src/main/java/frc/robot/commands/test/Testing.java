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
                Commands.print("preflight checklist complete!"),
                new IdleDrive(args.drive));
    }

    private static Command testAutos(TestArgs args) {

        return Commands.sequence(
                alert("No auton routines")
        // put auton routines to test here via testAll
        );
    }

    private static Command testDriverControls(TestArgs args) {

        return testAll(
                Teleop.arcadeDrive(args.drive, args.forward, args.twist),
                Teleop.pushToShoot(args.subsys));
    }

    private static Command alert(String msg) {
        return Commands.runOnce(() -> SmartDashboard.putString("Test alert", msg));
    }

    private static Command showTestSequence(Command... commands) {

        String[] output = new String[commands.length];

        for (int i = 0; i < commands.length; ++i) {
            output[i] = commands[i].getClass().getName();
        }

        return Commands.runOnce(() -> {
            SmartDashboard.putStringArray("Queued Tests", output);
        });
    }

    private static Command test(Command command) {
        final BooleanWrapper done = new BooleanWrapper();

        return Commands.sequence(
                Commands.runOnce(() -> {
                    SmartDashboard.putData("Advance Test",
                            Commands.runOnce(() -> {
                                done.bool = true;
                                
                            }));
                }),
                Commands.deadline(Commands.waitUntil(() -> done.bool), command));
    }

    private static Command testAll(Command... commands) {

        Command[] output = new Command[commands.length];

        for (int i = 0; i < commands.length; ++i) {
            output[i] = test(commands[i]);
        }

        return Commands.sequence(showTestSequence(commands), Commands.sequence(output));
    }

    private static class BooleanWrapper {

        private boolean bool = false;

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
