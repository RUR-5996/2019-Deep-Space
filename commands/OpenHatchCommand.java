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
 * Command for opening the hatch manipulator
 */
public class OpenHatchCommand extends Command {

  /**
   * Constructor, declares dependecy on subsystem
   */
  public OpenHatchCommand() {
    requires(Robot.hatch);
  }

  /**
   * Method, which runs when the command is initialized
   */
  @Override
  protected void initialize() {
  }

  /**
   * Method which is called repeatedly when its instantiated.
   * Opens the manipulator until the limit switch is triggered. 
   */
  @Override
  protected void execute() {
    Robot.hatch.openHatch();
  }

  /**
   * Method which checks whether to command is finished, then terminates the command.
   * @return boolean, if returns true, command is terminated
   * Checks whether the limit switch is pressed.
   */
  @Override
  protected boolean isFinished() {
    return !Robot.hatch.getOpenedSwitchValue();
  }

  /**
   * Method which is called when isFinished is true.
   * Shuts down the motor.
   */
  @Override
  protected void end() {
    Robot.hatch.stop();
  }

  /**
   * Called when the command would be interrupted by another command,
   * which would use the same subsystem.
   */
  @Override
  protected void interrupted() {
    Robot.hatch.stop();
  }
}
