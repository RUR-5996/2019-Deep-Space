/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Command for rotating via gyroscope.
 */
public class RotateCommand extends Command {

	private double angle;
	private int position;
	private int rotate;

	/**
	 * Declares dependencies and local variables
	 * @param angle - angle to which we want to turn
	 */
	public RotateCommand(/*double angle*/int rotate) {
		requires(Robot.rotate);
		this.rotate = rotate;
		//this.angle = angle;
	}

	/**
	 * Method which is called before execute - at start up.
	 * Sets the setpoint for the controller and enables it.
	 */
	@Override
	protected void initialize() {
		position = Robot.rotate.rotateRobot(rotate);
		switch(position) {
			case 0: Robot.rotate.setSetpoint(0f);
				break;
			case 1: Robot.rotate.setSetpoint(29f);
				break;
			case 2: Robot.rotate.setSetpoint(90f);
				break;
			case 3: Robot.rotate.setSetpoint(119f);
				break;
			case 4: Robot.rotate.setSetpoint(180f);
				break;
			case 5: Robot.rotate.setSetpoint(-119f);
				break;
			case 6: Robot.rotate.setSetpoint(-90f);
				break;
			case 7: Robot.rotate.setSetpoint(-29f);
				break;
		}
		Robot.rotate.enable();
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
   	 * Checks whether the robot is rotated within tolerance to the given setpoint.
   	 */
	@Override
	protected boolean isFinished() {
		return Math.abs(Robot.rotate.getPosition() - Robot.rotate.getSetpoint()) < Constants.gyroTolerance;
	}

	/**
   	 * Method which is called when isFinished is true.
   	 * Disabled the PID controller after if has reached its setpoint.
   	 */
	@Override
	protected void end() {
		Robot.rotate.disable();
	}

	/**
     * Called when the command would be interrupted by another command,
   	 * which would use the same subsystem. Disables the turn controller.
   	 */
	@Override
	protected void interrupted() {
		Robot.rotate.disable();
	}
}