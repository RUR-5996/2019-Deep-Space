/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

//imports
import frc.robot.Robot;
import frc.robot.commands.DriveCommand;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem for driving
 */
public class DriveSubsystem extends Subsystem {

	/**
	 * Sets default command for the subsystem - required.
	 * setDefaultCommand runs always when possible.
	 */
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DriveCommand());
	}

	/**
	 * Method for teleop drive, runs checks so it doesn't itervene with PIDs.
	 * @param xAxis - param for driving forwards/backwards
	 * @param yAxis - param for driving sideways
	 * @param zAxis - param for turning
	 */
	public void TeleopDrive(double xAxis, double yAxis, double zAxis) {
		//Checks whether ultrasonic PID is running
		if (!Robot.ultrasonic.isEnabled()) {
			Robot.driveExecutor.setX(xAxis);
		}

		Robot.driveExecutor.setY(yAxis);

		//Checks whether gyro PID is running
		if (!Robot.rotate.isEnabled()) {
			Robot.driveExecutor.setZ(zAxis);
		}
	}
}
