package frc.robot.commands;

import frc.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Turn_Command extends Command{
	
	private double angle;
	
	public Turn_Command(double angle) {
		requires(Robot.rotate);
		this.angle = angle;
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
	protected void execute() {
		Robot.rotate.turnToAngle(angle);
	}
	
	protected void end() {
//		Robot.gyro.rotateToAngle = false;
	}
}
