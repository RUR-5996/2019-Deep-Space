/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.Constants;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class UltrasonicSubsystem extends PIDSubsystem {

	private Ultrasonic ultrasonic = new Ultrasonic(0, 1);

	// Constructor, creates the PID controller, sets properties
	public UltrasonicSubsystem() {
		super("Ultrasonic", Constants.ultrasonicKp, Constants.ultrasonicKi, Constants.ultrasonicKp);
		setAbsoluteTolerance(Constants.ultrasonicTolerance);
		setInputRange(0, 700);
		setOutputRange(-1, 1);
		getPIDController().setContinuous(true);
		ultrasonic.setAutomaticMode(true);
	}

	// Boolean for checking whether the PID controller is enabled
	public boolean isEnabled() {
		return getPIDController().isEnabled();
	}

	// Getter method for the ultrasonic sensor
	public double getDistanceCM() {
		return (ultrasonic.getRangeMM() / 10);
	}

	// Method which is passed to the PID controller and used by it
	protected double returnPIDInput() {
		return getDistanceCM();
	}

	// Method for using the PID values
	protected void usePIDOutput(double output) {
		Robot.driveExecutor.setX(output * 0.5);
	}

	public void initDefaultCommand() {
		// setDefaultCommand(new DriveCommand());
	}
}