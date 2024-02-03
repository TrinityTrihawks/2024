// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.aouton;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drive;

public class Limelight extends Command {
  
  private final Drive drive;

  /** Creates a new Limelight. */
  public Limelight(Drive drive) {
    this.drive = drive;
    addRequirements(drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(1);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        NetworkTableEntry tx = table.getEntry("tx");
        NetworkTableEntry ty = table.getEntry("ty");
        NetworkTableEntry ta = table.getEntry("ta");
        NetworkTableEntry tv = table.getEntry("tv");
        NetworkTableEntry tid = table.getEntry("tid");

         boolean hasTarget = tv.getBoolean(false);
        double x = tx.getDouble(0.0);
        double y = ty.getDouble(0.0);
        double area = ta.getDouble(0.0);
        double id = tid.getDouble(0.0);

        SmartDashboard.putNumber("LimelightX", x);
        SmartDashboard.putNumber("LimelightY", y);
        SmartDashboard.putNumber("LimelightArea", area);
        SmartDashboard.putNumber("id", id);
        SmartDashboard.putBoolean("has target", hasTarget);

        //double fwdSpeed = (10 - area) / 21;
        double rotSpeed = x / 0;
        SmartDashboard.putNumber("rotSpeed", rotSpeed);
        //SmartDashboard.putNumber("fwdSpeed", fwdSpeed);

        //if(hasTarget){
          drive.drive(0 , rotSpeed);
        //}
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
