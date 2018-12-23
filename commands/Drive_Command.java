package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.OI;

import edu.wpi.first.wpilibj.command.Command;

public class Drive_Command extends Command {
	
	public Drive_Command(){
		requires(Robot.driveSubsystem);
	}
	
	@Override
	protected void initialize() {
		
	}
	
	@Override
	protected void execute() {
		Robot.driveSubsystem.TeleopDrive(Robot.m_oi.getDriveForward() , Robot.m_oi.getDriveSideways());		// Used for driving with joystick
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