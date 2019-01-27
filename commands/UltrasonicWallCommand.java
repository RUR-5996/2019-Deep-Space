/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;

public class UltrasonicWallCommand extends Command {

  private double distance;

  /**
   * Declares dependencies and local variables
   * @param distance - Distance to which we want the PID controller to move the robot
   */
  public UltrasonicWallCommand(double distance) {
    requires(Robot.ultrasonic);
    this.distance = distance;
  }

  // Called just before this Command runs the first time, sets the setpoint for the PID controller and enables it
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
    //checks whether robot is close to the setpoint within tollerance, then terminates this command
    return (Math.abs(Robot.ultrasonic.getSetpoint() - Robot.ultrasonic.getPosition()) < Constants.ultrasonicTolerance);
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    //disables the PID controller after it has reached its setpoint
    Robot.ultrasonic.disable();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.ultrasonic.disable();
  }
}