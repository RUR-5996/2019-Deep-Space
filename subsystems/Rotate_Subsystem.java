package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.commands.ControlledRotate_Command;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Rotate_Subsystem extends Subsystem implements PIDOutput {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new ControlledRotate_Command());
    }
    
    public void setRotateSpeed(double speed) {
    	rotateToAngle = false;
    	Robot.driveExecutor.setZ(speed);
    }
    
	//predefined constants which have to be tweaked depending on each robot. Most likely kP will have to be tweaked.
	private static final double kP = 0.03;
	private static final double kI = 0.00;
	private static final double kD = 0.00;
	private static final double kF = 0.00;
	
	private static final double kToleranceDegrees = 2.0f; //constant which defines the degree tolerance e.g. if target is 180 and tolerance is 2 then range of 178 - 182 is acceptable 
	private static boolean rotateToAngle; //boolean for whether turning via gyro is enabled
	private static AHRS ahrs; //defines the ahrs object
	private PIDController turnController; // PID turn controller

	//gyroscope initiation code
	public void gyroInit() {
		ahrs = new AHRS(SPI.Port.kMXP); //maps the AHRS to SPI port on RoboRIO - other ports can be used as well - SPI recommended
		turnController = new PIDController(kP, kI, kD, kF, ahrs, this); //Controller which defines how the robot behaves when turning via gyro
		turnController.setInputRange(-180.0f, 180.0f); //sets the rotation range (degrees)
		turnController.setOutputRange(-1.0, 1.0); //sets the output of the turn controller - -1 to 1 is used - negative values for turning to the left and positive for turning to the right
		turnController.setAbsoluteTolerance(kToleranceDegrees); //sets the tolerance
		turnController.setContinuous(true); //idk though has to be here
		turnController.enable();
		addChild(ahrs);
	}
	
	//method for turning via turn controller - accepts input as the degree which the robot will rotate to
	public void turnToAngle(double setPoint) {
		turnController.setSetpoint(setPoint); //sets the degree for the robot to rotate to - this is given as param
		rotateToAngle = true; //sets the boolean rotateToAngle to true - this means that trigger input will be disregarded until it is switched to false. It starts the turnController.
	}
	
	//method which must be included when using PIDOutput interface - the proccessed information from the PIDController is given to the double rotateToAngleRate
	@Override
	public void pidWrite(double output) {
		if(rotateToAngle) {
			Robot.driveExecutor.setZ(output * 0.4);
		}
	}

    
}
