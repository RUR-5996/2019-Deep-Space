/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.Robot;
import frc.robot.enumeration.DrivingType;

/**
 * Add your docs here.
 */
public class MoveLeftCommand extends TimedCommand {
  /**
   * Command for moving left autonomously.
   * @param timeout of the command
   * Dependent on drive subsystem.
   */
  public MoveLeftCommand(double timeout) {
    super(timeout);
    requires(Robot.drive);
  }

  /**
   * Called when command is initialized.
   * Sets the driving type from Field Oriented to Normal.
   * IMPORTANT!
   */
  @Override
  protected void initialize() {
    Robot.drivingType = DrivingType.NORMAL;
  }

  /**
   * Called repeatedly when command is running.
   * Sets speed.
   */
  @Override
  protected void execute() {
    Robot.driveExecutor.setX(-0.5);
  }

  /**
   * Called when command is ended.
   * Sets driving type back to Field Oriented and stops movement.
   */
  @Override
  protected void end() {
    Robot.driveExecutor.setX(0);
    Robot.drivingType = DrivingType.FIELD_ORIENTED;
  }

  /**
   * Called when command is interrupted.
   * Sets driving type back to Field Oriented and stops movement.
   */
  @Override
  protected void interrupted() {
    Robot.driveExecutor.setX(0);
    Robot.drivingType = DrivingType.FIELD_ORIENTED;
  }
}
