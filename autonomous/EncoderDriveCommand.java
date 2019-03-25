/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class EncoderDriveCommand extends Command {

  private double distance; 

  /**
   * Method for driving with encoders
   * @param distance that the robot should drive in cm
   */
  public EncoderDriveCommand(double distance) {
    requires(Robot.drive);
    this.distance = distance;
  }

  /**
   * Method which turns on the PID controller and sets the setpoint.
   */
  @Override
  protected void initialize() {
    Robot.drive.setSetpoint(Robot.drive.getEncoderPosition(distance));
    Robot.drive.enable();
  }

  /**
   * Method which runs periodically.
   * Not used, PID takes care of everything.
   */
  @Override
  protected void execute() {
  }

  /**
   * Method for checking whether the command is finished.
   * @return is robot beyond the set target
   */
  @Override
  protected boolean isFinished() {
    return Robot.drive.isEncoderTargetReached();
  }

  /**
   * Called after the end condition is reached.
   * Disables the PID controller and resets encoders.
   */
  @Override
  protected void end() {
    Robot.drive.disable();
    Robot.robotMap.resetEncoders();
  }

  /**
   * Called when interupted by another command that used the same
   * subsystem.
   */
  @Override
  protected void interrupted() {
    Robot.drive.disable();
    Robot.robotMap.resetEncoders();
  }
}
