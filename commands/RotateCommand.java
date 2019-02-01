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

public class RotateCommand extends Command {

	private double angle;

	/**
	 * Declares dependencies and local variables
	 * @param angle - angle to which we want to turn
	 */
	public RotateCommand(double angle) {
		requires(Robot.rotate);
		this.angle = angle;
	}

	// Called just before this Command runs the first time, sets the setpoint for
 	// the PID controller and enables it
	protected void initialize() {
		Robot.rotate.setSetpoint(angle);
		Robot.rotate.enable();
	}

	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return Math.abs(Robot.rotate.getPosition() - Robot.rotate.getSetpoint()) < Constants.gyroTolerance;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		// disables the PID controller after it has reached its setpoint
		Robot.rotate.disable();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		Robot.rotate.disable();
	}
}