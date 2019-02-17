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
import frc.robot.enumeration.DrivingType;

/**
 * Command for lining up with the vision targets.
 */
public class LineupCommand extends Command {
  /**
   * Constructor, declares dependencies.
   * No local variables are used, since we always want to be
   * lined up with the vision targets at the same position.
   */
  public LineupCommand() {
    requires(Robot.vision);
  }

  /**
	 * Method which is called before execute - at start up.
	 * Sets the setpoint for the controller and enables it.
   * Zero is used because we always want to be lined up with the vision targets.
	 */
  @Override
  protected void initialize() {
    Robot.vision.setSetpoint(0);
    Robot.vision.enable();
    Robot.rotate.enable();
    Robot.drivingType = DrivingType.NORMAL;
  }

  /**
	 * Not used. PID controller is set up and runs in the meantime.
	 */
  @Override
  protected void execute() {
    //Robot.vision.lineup();
  }

  /**
   * Method which checks whether to command is finished, then terminates the command.
   * @return boolean, if returns true, command is terminated
   * Checks whether the robot is within tolerance of the center of vision targets.
   */
  @Override
  protected boolean isFinished() {
    if(Robot.vision.getTargets() == 2) {
      return Math.abs(Robot.vision.getOffset()) < Constants.visionTolerance;
    } else {
      Robot.driveExecutor.setX(0);
      return true;
    } 
    
  }

  /**
   * Called once after isFinished returns true.
   * Disables the PID controller after the setpoint is reached.
   */
  @Override
  protected void end() {
    Robot.driveExecutor.setX(0);
    Robot.vision.disable();
    Robot.rotate.disable();
    Robot.drivingType = DrivingType.FIELD_ORIENTED;
  }

  /**
   * Called when another command which requires one or more of the same
   * subsystems is scheduled to run. Disables the turn controller.
   */
  @Override
  protected void interrupted() {
    Robot.driveExecutor.setX(0);
    Robot.vision.disable();
    Robot.rotate.enable();
    Robot.drivingType = DrivingType.FIELD_ORIENTED;
  }
}
