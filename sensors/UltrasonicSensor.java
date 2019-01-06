/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.sensors;
import frc.robot.Robot;

import edu.wpi.first.wpilibj.Ultrasonic;
public class UltrasonicSensor extends Robot {
	
	Ultrasonic ultrasonic = new Ultrasonic(9,8);

	public UltrasonicSensor() {
		ultrasonic.setAutomaticMode(true);
	}
	
	public double getDistanceCM() {
		return (ultrasonic.getRangeMM() / 10);
	}
}