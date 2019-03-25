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
   * Command for moving to a wall for hatch pickup / dropoff.
   */
public class WallCommand extends Command {

  /**
   * Command for moving to a wall for hatch pickup / dropoff.
   * Uses ultrasonic sensor.
   * Dependent on ultrasonic subsystem.
   */
  public WallCommand() {
    requires(Robot.ultrasonic);
  }

  /**
   * Called when command is initialized.
   * Sets the driving type from Field Oriented to Normal.
   * IMPORTANT!
   * Enables the PID controller for gyroscope in order to correct for
   * movement to the sides.
   * Do not use Vision PID, goes fubar if it stops seeing targets.
   */
  @Override
  protected void initialize() {
    Robot.drivingType = DrivingType.NORMAL;
    Robot.rotate.enable();
  }

  /**
   * Called repeatedly when command is running.
   * Sets speed based upon the distance from wall.
   */
  @Override
  protected void execute() {
    if(Robot.ultrasonic.getDistanceCM() > 70) {
      Robot.driveExecutor.setY(-0.3);
    } else if(Robot.ultrasonic.getDistanceCM() < 70) {
      Robot.driveExecutor.setY(-0.15);
    } else if(Robot.ultrasonic.getDistanceCM() < 50) {
      Robot.driveExecutor.setY(-0.06);
    } else if(Robot.ultrasonic.getDistanceCM() < Constants.hatchToWallDistance) {
      Robot.driveExecutor.setY(0);
    }
  }

  /**
   * Checks whether end condition if met.
   * Checks whether distance from ultrasonic is lower than predefined distance
   * needed to place hatch panel. (in Constants)
   */
  @Override
  protected boolean isFinished() {
    return Robot.ultrasonic.getDistanceCM() < Constants.hatchToWallDistance;
  }

  /**
   * Called when command is ended.
   * Sets driving type back to Field Oriented and stops movement.
   * Disables PID controller.
   */
  @Override
  protected void end() {
    Robot.driveExecutor.setY(0);
    Robot.drivingType = DrivingType.FIELD_ORIENTED;
    Robot.rotate.disable();
  }

  /**
   * Called if command is interrupted.
   * Sets driving type back to Field Oriented and stops movement.
   * Disables PID controller.
   */
  @Override
  protected void interrupted() {
    Robot.driveExecutor.setY(0);
    Robot.rotate.disable();
    Robot.drivingType = DrivingType.FIELD_ORIENTED;
  }
}
