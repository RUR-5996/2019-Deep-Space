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
	/**
	 * Sets the speed from subsystems.
	 * @param x speed in the X direction
	 */
	public void setX(double x) {
		this.x = x;
	}
	/**
	 * Sets the speed from subsystems.
	 * @param y speed in the Y direction
	 */
	public void setY(double y) {
		this.y = y;
	}
	/**
	 * Sets the speed from subsystems.
	 * @param z speed in the Z direction
	 */
	public void setZ(double z) {
		this.z = z;
	}
	
	//declaration and instantiation of the drive train
	private MecanumDrive robotDrive = new MecanumDrive(RobotMap.leftFront, RobotMap.leftBack, RobotMap.rightFront, RobotMap.rightBack);
	
	/**
	 * Driving method called from Teleop Periodic
	 */
	public void execute() {
		robotDrive.driveCartesian(x, -y, z);
	}
}
