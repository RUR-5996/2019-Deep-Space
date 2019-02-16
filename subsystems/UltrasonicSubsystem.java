/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.utils.ReportingInterface;
import frc.robot.Constants;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 * Class for ultrasonic 
 */
public class UltrasonicSubsystem extends PIDSubsystem implements ReportingInterface {

	private Ultrasonic ultrasonic = new Ultrasonic(Constants.ultrasonicPort1, Constants.ultrasonicPort2);

	/**
   	 * Constructor, creates the PID controller, sets properties
   	 */
	public UltrasonicSubsystem() {
		super("Ultrasonic", Constants.ultrasonicKp, Constants.ultrasonicKi, Constants.ultrasonicKp);
		setAbsoluteTolerance(Constants.ultrasonicTolerance);
		setInputRange(0, Constants.ultrasonicRange);
		setOutputRange(-Constants.ultrasonicSpeed, Constants.ultrasonicSpeed);
		getPIDController().setContinuous(true);
		ultrasonic.setAutomaticMode(true);
	}

	/**
   	 * Method for checking whether the PID controller is enabled
   	 * @return boolean - true for enabled, false for disabled
   	 */
	public boolean isEnabled() {
		return getPIDController().isEnabled();
	}

	/**
	 * Method for getting ultrasonic distance in cm
	 * @return distance in cm as a double
	 */
	public double getDistanceCM() {
		return (ultrasonic.getRangeMM() / 10);
	}

	/**
	 * Method for passing info to the PID controller
	 * @return distance from ultrasonic in cm
	 */
	protected double returnPIDInput() {
		return getDistanceCM();
	}

	/**
	 * Method for using the output of the PID controller
	 * @param output method for driving forward/backwards
	 * In range of -1 to 1
	 */
	protected void usePIDOutput(double output) {
		Robot.driveExecutor.setY(output);
	}

	/**
	 * Default command needed by subsystem
	 */
	public void initDefaultCommand() {
		// setDefaultCommand(new DriveCommand());
	}

	public void report() {
		SmartDashboard.putBoolean("Ultrasonic PID enabled", isEnabled());
		SmartDashboard.putNumber("Distance", getDistanceCM());
		SmartDashboard.putNumber("Setpoint", getSetpoint());
	}
}