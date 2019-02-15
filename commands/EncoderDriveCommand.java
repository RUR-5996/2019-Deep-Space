/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class EncoderDriveCommand extends Command {

  private double distance; 

  public EncoderDriveCommand(double distance) {
    requires(Robot.drive);
    this.distance = distance;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.drive.setSetpoint(Robot.drive.getEncoderPosition(distance));
    Robot.drive.enable();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.drive.isEncoderTargetReached();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.drive.disable();
    Robot.robotMap.resetEncoders();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.drive.disable();
    Robot.robotMap.resetEncoders();
  }
}
