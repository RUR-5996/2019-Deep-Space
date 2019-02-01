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

//declares class - Drive_Subsystem - extends Subsystem REQUIRED!!! Cannot run otherwise. Since this is a subsystem call methods using commands.
public class DriveSubsystem extends Subsystem {

	// sets default command for the subsystem - required for this subsystem.
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DriveCommand()); // sets Drive_Command as default command
	}

	// method used during teleop drive - xAxis for x coordinate input, yAxis for y
	// coordinate input, zAxis for turning, ahrs for gyro
	public void TeleopDrive(double xAxis, double yAxis, double zAxis) {
		// drive method, uses cartesian drive for mecanumDrive type robotDrive object -
		// driveCartesian automatically processes the values fed
		if (!Robot.ultrasonic.isEnabled()) {
			Robot.driveExecutor.setX(xAxis);
		}

		Robot.driveExecutor.setY(yAxis);

		if (!Robot.rotate.isEnabled()) {
			Robot.driveExecutor.setZ(zAxis);
		}
	}
}
