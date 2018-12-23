package frc.robot;

import edu.wpi.first.wpilibj.drive.MecanumDrive;

public class DriveExecutor {
	private double x;
	private double y;
	private double z;
	public void setX(double x) {
		this.x = x;
	}
	public void setY(double y) {
		this.y = y;
	}
	public void setZ(double z) {
		this.z = z;
	}
	
	private MecanumDrive robotDrive = new MecanumDrive(RobotMap.leftFront, RobotMap.leftBack, RobotMap.rightFront, RobotMap.rightBack);
	
	public void execute() {
		robotDrive.driveCartesian(x, -y, z);
	}
}
