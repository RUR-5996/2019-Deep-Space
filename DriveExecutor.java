/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

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
