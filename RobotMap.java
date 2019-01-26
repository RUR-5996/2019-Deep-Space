/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Talon;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  	public static Talon leftFront = new Talon(Constants.frontLeftMotor);
	public static WPI_TalonSRX leftBack = new WPI_TalonSRX(Constants.backLeftMotor);
	public static Talon rightFront = new Talon(Constants.frontRightMotor);
	public static WPI_TalonSRX rightBack= new WPI_TalonSRX(Constants.backRightMotor);
	public static Talon boschMotor = new Talon(Constants.boschMotor);
}
