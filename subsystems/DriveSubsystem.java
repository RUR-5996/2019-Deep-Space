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
import frc.robot.utils.ReportingInterface;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem for driving - NEEDS MAINTENANCE
 */
public class DriveSubsystem extends /*PID*/Subsystem implements ReportingInterface {

	//private double encoderPulses;

	public DriveSubsystem() {
		/*
		super("Drive", 0.003, 0, 0.03);
		setAbsoluteTolerance(250);
		setInputRange(-32000, 32000);
		setOutputRange(-0.4, 0.4);
		getPIDController().setContinuous(true);*/
	}
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
	public void TeleopDrive(double xAxis, double yAxis/*, double zAxis*/) {
		//Checks whether vision PID is running
		if(!Robot.vision.isEnabled()){
			Robot.driveExecutor.setX(xAxis);
		}

		//Checks whether ultrasonic PID is running
		if (!Robot.ultrasonic.isEnabled()) {
			Robot.driveExecutor.setY(yAxis);
		}
		/*   CURRENTLY NOT USED MANUALLY, leave it here tho
		//Checks whether gyro PID is running
		if (!Robot.rotate.isEnabled()) {
			Robot.driveExecutor.setZ(zAxis);
		}*/
	}

	/*
	public double getEncoderPosition(double distance) {
		Robot.robotMap.resetEncoders();
		this.encoderPulses = Constants.pulsesPerCm * distance;
		return encoderPulses;
	}

	public double getEncoderValue() {
		return RobotMap.rightBack.getSelectedSensorPosition();
	}

	public double getEndocerPulses() {
		return encoderPulses;
	}

	public boolean isEncoderTargetReached() {
		if(encoderPulses > 0) {
			return getEncoderValue() >= encoderPulses;
		} else if(encoderPulses < 0) {
			return getEncoderValue() <= -encoderPulses;
		}
		return false;
	}

	protected double returnPIDInput() {
		return getEncoderValue();
	}

	protected void usePIDOutput(double output) {
		Robot.driveExecutor.setY(-output);
	}
*/
	public void report() {
		//SmartDashboard.putNumber("Encoder ticks", getEncoderValue());
	}

}
