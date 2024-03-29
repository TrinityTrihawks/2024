// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.aouton.Autos;
import frc.robot.commands.teleop.Teleop;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
    // The robot's subsystems and commands are defined here...
    private final Drive drive;
    private final Shooter shooter;
    private final Intake intake;
    private final Climber climber;

    // Replace with CommandPS4Controller or CommandJoystick if needed
    private final CommandXboxController driverController = new CommandXboxController(
            OperatorConstants.kDriverControllerPort);
    private final CommandXboxController subsysController = new CommandXboxController(
            OperatorConstants.kSubsysControllerPort);
    private final SendableChooser<Command> autonSwitch = new SendableChooser<>();

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        drive = BotSwitcher.getDrive();
        shooter = BotSwitcher.getShooter();
        intake = BotSwitcher.getIntake();
        climber = BotSwitcher.getClimber();
        configureAutonomoi();
        // Configure the trigger bindings
        configureBindings();
    }

    /**
     * Use this method to define your trigger->command mappings. Triggers can be
     * created via the
     * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with
     * an arbitrary
     * predicate, or via the named factories in {@link
     * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for
     * {@link
     * CommandXboxController
     * Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
     * PS4} controllers or
     * {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
     * joysticks}.
     */
    private void configureBindings() {
        // // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
        // new Trigger(m_exampleSubsystem::exampleCondition)
        // .onTrue(new ExampleCommand(m_exampleSubsystem));

        // // Schedule `exampleMethodCommand` when the Xbox controller's B button is
        // pressed,
        // // cancelling on release.
        // m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());

        // subsys.setDefaultCommand(new IntakeInBackground(subsys));
        drive.setDefaultCommand(
                Teleop.arcadeDrive(drive, driverController::getLeftY, driverController::getLeftX)
        );
        SmartDashboard.putBoolean("smart intake", true);
        subsysController.x().onTrue(Teleop.pushToShootCL(shooter, intake));
        subsysController.y().onTrue(Teleop.pushToShootCLAmp(shooter, intake));
        subsysController.a()
                .whileTrue(Teleop.runIntake(frc.robot.subsystems.robot2024.Intake.getInstance(), subsysController,
                        driverController));
        subsysController.b().whileTrue(Teleop.runReverseIntakeAndShooter(intake, shooter));
        subsysController.a()
                .whileTrue (Teleop.runIntake(frc.robot.subsystems.robot2024.Intake.getInstance(), subsysController,
                        driverController));
        subsysController.povUp()
                .or(subsysController.povUpLeft())
                .or(subsysController.povUpRight())
                .whileTrue(Teleop.extendClimber(climber));
        subsysController.povDown()
                .or(subsysController.povDownLeft())
                .or(subsysController.povDownRight())
                .whileTrue(Teleop.retractClimber(climber));
    }   

    private void configureAutonomoi() {
        autonSwitch.setDefaultOption(
                "(2 pts) basic leave command",
                Autos.leave(drive));

        autonSwitch.addOption(
                "(7 pts) leave center and score a note",
                Autos.c1(shooter, intake, drive));
        autonSwitch.addOption(
                "(12 pts) leave center and score 2 notes",
                Autos.c2(shooter, intake, drive));
        autonSwitch.addOption(
                "(7 pts) leave right and score a note",
                Autos.r1(shooter, intake, drive));
        autonSwitch.addOption(
                "(7 pts) leave left and score a note",
                Autos.l1(shooter, intake, drive));
        SmartDashboard.putData("Autonomoi", autonSwitch);
        SmartDashboard.putNumber(Constants.AutonConstants.kAutonStartDelayKey, 0.0);

    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {

        return autonSwitch.getSelected();
    }
}
