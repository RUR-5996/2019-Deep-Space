/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.RampSubsystem;

/**
 * Command for pulling the winch of the ramp in.
 */
public class RampWinchInCommand extends Command {

  /**
   * Command for pulling the winch of the ramp in.
   * Dependent on winch subsystem.
   */
  public RampWinchInCommand() {
    requires(Robot.rampWinch);
  }

  /**
   * Method which is run once when the command is initialized.
   */
  @Override
  protected void initialize() {
  }

  /**
   * Called repeatedly until isFinished returns true.
   * Moves winch in while encoder ticks are above 20.
   */
  @Override
  protected void execute() {
    if(Robot.rampWinch.getEncoder() > 20){
      Robot.rampWinch.moveIn();
    }
  }

  /**
   * Checks whether end condition is satisfied, terminates command.
   * Checks whether encoder value is under 20.
   */
  @Override
  protected boolean isFinished() {
    return (Robot.rampWinch.getEncoder() <= 20) || Robot.rampWinch.getRampLimitSwitchValue();  // If we want the winch to auto-correct to desired position, put this away
  }

  /**
   * Method which is called when isFinished returns true.
   * Stops the movement of the winch.
   */
  @Override
  protected void end() {
    Robot.rampWinch.moveStop();
    if (Robot.rampWinch.getRampLimitSwitchValue()) {
      Robot.rampWinch.resetEncoder();
    }
  }

  /**
   * Method which is called if command is interrupted by another command.
   * Stops the movement of the winch.
   */
  @Override
  protected void interrupted() {
    Robot.rampWinch.moveStop();
  }
}