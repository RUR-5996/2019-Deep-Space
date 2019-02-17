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
   * Constructor. Sets command timeout and dependency.
   */
  public MoveBackCommand() {
    super(Constants.moveBackTime);
    requires(Robot.drive);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.drivingType = DrivingType.NORMAL;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.driveExecutor.setY(Constants.moveBackSpeed);
  }

  // Called once after timeout
  @Override
  protected void end() {
    Robot.driveExecutor.setY(0);
    Robot.drivingType = DrivingType.FIELD_ORIENTED;
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.driveExecutor.setY(0);
    Robot.drivingType = DrivingType.FIELD_ORIENTED;
  }
}
