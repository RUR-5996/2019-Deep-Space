/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 * Disregard literally everything here.
 */
public class DoNothingCommand extends TimedCommand {
  /**
   * Method for doing literally nothing, used as timer.
   * @param timeout in seconds.
   */
  public DoNothingCommand(double timeout) {
    super(timeout);
  }

  //Disregard everything bellow. Not used but required.
  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
