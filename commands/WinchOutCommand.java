/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * Command for pulling the winch out.
 */
public class WinchOutCommand extends Command {

  /**
   * Command for pulling the winch out.
   * Dependent on winch subsystem.
   */
  public WinchOutCommand() {
    requires(Robot.winch);
  }

  /**
   * Method which is run once when the command is initialized.
   */
  @Override
  protected void initialize() {
  }

  /**
   * Called repeatedly until isFinished returns true.
   * Moves winch in while encoder ticks are bellow 1020.
   * Else if statement should be redundant, but placed in for safety.
   */
  @Override
  protected void execute() {
    if (Robot.winch.getEncoder() < 1020) {
      Robot.winch.moveOut();
    }
    else if (Robot.winch.getEncoder() > 1020){
      Robot.winch.moveStop();
    }

  }

  /**
   * Checks whether end condition is satisfied, terminates command.
   * Checks whether encoder value is above 1020.
   */
  @Override
  protected boolean isFinished() {
    return Robot.winch.getEncoder() > 1020;
  }

  /**
   * Method which is called when isFinished returns true.
   * Stops the movement of the winch.
   */
  @Override
  protected void end() {
    Robot.winch.moveStop();
  }

  /**
   * Method which is called if command is interrupted by another command.
   * Stops the movement of the winch.
   */
  @Override
  protected void interrupted() {
    Robot.winch.moveStop();
  }
}