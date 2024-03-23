// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.teleop;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Parameters.OperatorParameters;
import frc.robot.subsystems.Drive;

class ArcadeDrive extends Command {

    private final Drive drive;
    private final DoubleSupplier x;
    private final DoubleSupplier z;

    private double twistRange;

    public ArcadeDrive(Drive d, DoubleSupplier forward, DoubleSupplier twist) {
        addRequirements(drive = d);
        x = OperatorParameters.squareForwardInput ? () -> {
            double x = forward.getAsDouble();
            return x * x * Math.signum(x);
        } : forward;
        z = OperatorParameters.squareForwardInput ? () -> {
            double z = twist.getAsDouble();
            return z * z * Math.signum(z);
        } : twist;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        twistRange = OperatorParameters.staticTwistThrottle - OperatorParameters.twistThrottle;
        double xspeed = x.getAsDouble() * OperatorParameters.forwardThrottle;
        double zspeed = z.getAsDouble()
                * (OperatorParameters.staticTwistThrottle - xspeed / OperatorParameters.forwardThrottle * twistRange);
        drive.drive(xspeed, zspeed);
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
