/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class UltrasonicWallCommand extends Command {

  private double distance;

  public UltrasonicWallCommand(double distance) {
    requires(Robot.ultrasonic);
    this.distance = distance;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.ultrasonic.setSetpoint(distance);
    Robot.ultrasonic.enable();
  }

  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return (Math.abs(Robot.ultrasonic.getSetpoint() - Robot.ultrasonic.getPosition()) < 5);
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.ultrasonic.disable();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}