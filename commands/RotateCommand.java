/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class RotateCommand extends Command{
	
	private double angle;
	
	public RotateCommand(double angle) {
		requires(Robot.rotate);
		this.angle = angle;
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	protected void execute() {
		Robot.rotate.turnToAngle(angle);
	}
	
	protected void end() {
//		Robot.gyro.rotateToAngle = false;
	}
}