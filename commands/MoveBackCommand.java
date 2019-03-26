/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.enumeration.DrivingType;

/**
 * Command for moving back. Used when dropping off / loading hatch
 * for driver convenience.
 */
public class MoveBackCommand extends TimedCommand {
  /**
   * Command for moving back. Used when dropping off / loading hatch
   * for driver convenience.
   * Dependent on drive subsystem.
   * Move back time in seconds in Constants.
   */
  public MoveBackCommand() {
    super(Constants.moveBackTime);
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
    Robot.driveExecutor.setY(Constants.moveBackSpeed);
  }

  /**
   * Called when command is ended.
   * Sets driving type back to Field Oriented and stops movement.
   */
  @Override
  protected void end() {
    Robot.driveExecutor.setY(0);
    Robot.drivingType = DrivingType.FIELD_ORIENTED;
  }

  /**
   * Called when command is interrupted.
   * Sets driving type back to Field Oriented and stops movement.
   */
  @Override
  protected void interrupted() {
    Robot.driveExecutor.setY(0);
    Robot.drivingType = DrivingType.FIELD_ORIENTED;
  }
}
