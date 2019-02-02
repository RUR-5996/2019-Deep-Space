/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Command for driving.
 */
public class DriveCommand extends Command {

	/**
	 * Constructor, declares dependencies on subsystems.
	 */
	public DriveCommand() {
		requires(Robot.drive);
	}

	/**
	 * Method, which runs when the command is initialized.
	 */
	@Override
	protected void initialize() {

	}

	/**
	 * Method which is called periodically while the command is active.
	 * Used for driving.
	 */
	@Override
	protected void execute() {
		Robot.drive.TeleopDrive(Robot.m_oi.getDriveForward(), 
								Robot.m_oi.getDriveSideways(),
								Robot.m_oi.getDriveRotation()); // Used for driving with joystick
	}

	/**
   * Method which checks whether to command is finished, then terminates the command.
   * @return boolean, if returns true, command is terminated.
   * The command never returns true, we want it to always run.
   */
	@Override
	protected boolean isFinished() {
		return false;
	}

	/**
	 * Method which is called when isFinished returns true.
	 * Nothing happens. We want this command to always run.
	 */
	@Override
	protected void end() {
	}

	/**
   	 * Called when the command would be interrupted by another command,
	 * which would use the same subsystem.
	 * Nothing happens. We want this command to always run.
   	 */
	@Override
	protected void interrupted() {

	}
}