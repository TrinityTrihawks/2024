// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.aouton;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.Robot2024Constants.NotePathConstants;
import frc.robot.subsystems.robot2024.NotePath;

public class Shoot extends Command {

    private final NotePath subsys;
    private final Timer feedTimer = new Timer();

    /** Creates a new Shoot. */
    public Shoot(NotePath s) {
        addRequirements(subsys = s);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        feedTimer.start();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if (feedTimer.hasElapsed(NotePathConstants.kShooterWarmupTime)) {
            subsys.shoot();
        } 
        subsys.runShooter();
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
