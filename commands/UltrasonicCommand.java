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
 * Command for driving to a set distance via PID controller.
 */
public class UltrasonicCommand extends Command {

  private double distance;

  /**
   * Declares dependencies and local variables
   * @param distance - Distance to which we want the PID controller to move the robot.
   */
  public UltrasonicCommand(double distance) {
    requires(Robot.ultrasonic);
    this.distance = distance;
  }

  /**
	 * Method which is called before execute - at start up.
	 * Sets the setpoint for the controller and enables it.
	 */
  @Override
  protected void initialize() {
    Robot.ultrasonic.setSetpoint(distance);
    Robot.ultrasonic.enable();
    Robot.drivingType = DrivingType.NORMAL;
  }

  /**
	 * Not used. PID controller is set up and runs in the meantime.
	 */
  @Override
  protected void execute() {
  }

  /**
   * Method which checks whether to command is finished, then terminates the command.
   * @return boolean, if returns true, command is terminated
   * Checks whether the robot is within tolerance of the given setpoint.
   */
  @Override
  protected boolean isFinished() {
    return (Math.abs(Robot.ultrasonic.getSetpoint() - Robot.ultrasonic.getPosition()) < Constants.ultrasonicTolerance);
  }

  /**
   * Method which is called when isFinished is true.
   * Disabled the PID controller after if has reached its setpoint.
   */
  @Override
  protected void end() {
    Robot.ultrasonic.disable();
    Robot.drivingType = DrivingType.FIELD_ORIENTED;
  }

  /**
   * Called when the command would be interrupted by another command,
   * which would use the same subsystem. Disables the PID controller.
   */
  @Override
  protected void interrupted() {
    Robot.ultrasonic.disable();
    Robot.drivingType = DrivingType.FIELD_ORIENTED;
  }
}