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
 * Command for picking up balls into the shooter.
 */
public class IntakeCommand extends Command {

  /**
   * Constructor, declares dependencies.
   */
  public IntakeCommand() {
    requires(Robot.shooter);
  }

  /**
   * Called just before this Command runs the first time.
   */
  @Override
  protected void initialize() {
  }

  /**
   * Called repeatedly when this Command is scheduled to run.
   * Calls the intake method.
   */
  @Override
  protected void execute() {
    Robot.shooter.intake();
  }

  /**
   * Determines whether to terminate the command.
   * Terminated when switch is triggered.
   * Unpressed = true.
   * Pressed = false.
   */
  @Override
  protected boolean isFinished() {
    return !Robot.shooter.getSwitch();
  }

  /**
   * Method, which is called at end.
   * Stops the manipulator.
   */
  @Override
  protected void end() {
    Robot.shooter.stop();
  }

  /**
   * Method, which is called when the command is interrupted.
   * Stops the manipulator.
   */
  @Override
  protected void interrupted() {
    Robot.shooter.stop();
  }
}
