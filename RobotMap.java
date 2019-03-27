/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.VictorSP;

/**
 * Instantiation and binding of motors. 
 */
public class RobotMap {
  	public static WPI_TalonSRX leftFront = new WPI_TalonSRX(Constants.frontLeftMotor);
	public static WPI_TalonSRX leftBack = new WPI_TalonSRX(Constants.backLeftMotor);
	public static WPI_TalonSRX rightFront = new WPI_TalonSRX(Constants.frontRightMotor);
	public static WPI_TalonSRX rightBack= new WPI_TalonSRX(Constants.backRightMotor);

	public static WPI_VictorSPX shooter1 = new WPI_VictorSPX(Constants.shooter1);
	public static WPI_VictorSPX shooter2 = new WPI_VictorSPX(Constants.shooter2);
	public static WPI_VictorSPX shooter3 = new WPI_VictorSPX(Constants.shooter3);
	public static WPI_VictorSPX shooter4 = new WPI_VictorSPX(Constants.shooter4);
	public static WPI_VictorSPX shooterBoschMotor = new WPI_VictorSPX(Constants.shooterBoschMotor);
	public static WPI_VictorSPX winchMotor = new WPI_VictorSPX(Constants.winchMotor);
	public static WPI_VictorSPX rampWinchMotor = new WPI_VictorSPX(Constants.rampWinchMotor);

	public static VictorSP hatchMotor = new VictorSP(Constants.boschMotor);

	/*
	public void resetEncoders() {
		leftFront.setSelectedSensorPosition(0);
		leftBack.setSelectedSensorPosition(0);
		rightFront.setSelectedSensorPosition(0);
		rightBack.setSelectedSensorPosition(0);
	}*/
}
