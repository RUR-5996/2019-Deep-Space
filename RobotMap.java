/*----------------------------------------------------------------------------*/

/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
/**
 * Class for mapping ports of Talons, victors, sensors etc.
 */
public class RobotMap {
	
	//binds talons, victors, sensors etc. to ports - used throughout the robot
	public static Talon leftFront = new Talon(0);
	public static Talon leftBack = new Talon(1);
	public static Talon rightFront = new Talon(2);
	public static Talon rightBack= new Talon(3);
	public static Victor grabberWheels= new Victor(4);
	public static Victor arm = new Victor(6);
	public static Servo lowerServo = new Servo(7);
	public static Servo upperServo = new Servo(8);
	public static Victor lifter = new Victor(12);
	public static Victor dartLeft = new Victor(10);
	public static Victor dartRight = new Victor(11);
	public static Victor jaws = new Victor(13);
}
