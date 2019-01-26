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

	Ultrasonic ultrasonic = new Ultrasonic(0,1);

	public UltrasonicSubsystem() {
		super("Ultrasonic", Constants.ultrasonicKp, Constants.ultrasonicKi, Constants.ultrasonicKp);
		setAbsoluteTolerance(Constants.ultrasonicTolerance);
		setInputRange(0, 700);
		setOutputRange(-1, 1);
		getPIDController().setContinuous(true);
		ultrasonic.setAutomaticMode(true);
	}

	public boolean isEnabled() {
		return getPIDController().isEnabled();
	}

	public double getDistanceCM() {
		return (ultrasonic.getRangeMM() / 10);
	}

	protected double returnPIDInput() {
		return getDistanceCM();
	}

	protected void usePIDOutput(double output) {
		Robot.driveExecutor.setX(output * 0.5);
	}

	public void initDefaultCommand() {
		//setDefaultCommand(new DriveCommand());
	}
}