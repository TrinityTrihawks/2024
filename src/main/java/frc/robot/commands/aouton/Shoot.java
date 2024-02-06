// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.aouton;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.Robot2024Constants.ShooterConstants;
import frc.robot.subsystems.Shooter;

public class Shoot extends Command {

    private final Shooter shooter;
    private final Timer feedTimer = new Timer();

    public Shoot(Shooter s) {
        addRequirements(shooter = s);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        feedTimer.start();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if (feedTimer.hasElapsed(ShooterConstants.kShooterWarmupTime)) {
            shooter.feed();
        }
        shooter.run();
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        shooter.stop();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
