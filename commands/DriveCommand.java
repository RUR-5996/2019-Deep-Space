/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class DriveCommand extends Command {

	public DriveCommand() {
		requires(Robot.drive);
	}

	@Override
	protected void initialize() {

	}

	@Override
	protected void execute() {
		Robot.drive.TeleopDrive(Robot.m_oi.getDriveForward(), 
								Robot.m_oi.getDriveSideways(),
								Robot.m_oi.getDriveRotation()); // Used for driving with joystick
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {

	}

	@Override
	protected void interrupted() {

	}
}