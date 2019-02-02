/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

/**
 * Subsystem for getting and working with vision data
 * Data is sent from DS, where it is processed, via network tables
 */
public class VisionSubsystem extends PIDSubsystem {
  
  private static NetworkTableInstance inst = NetworkTableInstance.getDefault();
  private static NetworkTable table = inst.getTable("GRIP/contours");
  private double[] defaultValue = new double[0];
  private double[] centerX;
  private double[] centerY;
  private double offset;

  public VisionSubsystem() {
    super("Vision", 0.03, 0.00, 0.00);
    setAbsoluteTolerance(2);
    setInputRange(-160, 160);
    setOutputRange(-1, 1);
    getPIDController().setContinuous(true);
  }

  /**
	 * Method for passing info to the PID controller
	 * @return distance from ultrasonic in cm
	 */
	protected double returnPIDInput() {
		return offset;
  }
  
  public double getOffset(){
    return offset;
  }

  public boolean isEnabled() {
    return getPIDController().isEnabled();
  }

	/**
	 * Method for using the output of the PID controller
	 * @param output method for driving forward/backwards
	 * In range of -1 to 1
	 */
	protected void usePIDOutput(double output) {
		Robot.driveExecutor.setX(output * 0.5);
	}

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

/**
 * Method for getting vision data from network tables
 */
  private void setVars() {
    centerX = table.getEntry("centerX").getDoubleArray(defaultValue);
    centerY = table.getEntry("centerY").getDoubleArray(defaultValue);
  }

/**
 * Method for processing vision data
 */
  public void visionLogic() {
    //getting data from network tables
    setVars();

    //check for validity of data
    if(centerX.length == 2) {
      SmartDashboard.putBoolean("Vision Ready", true); //puts data into dashboard for driver
      offset = getVisionOffset(centerX);
    } else {
      System.err.println("Targets not found!"); //debug line
      SmartDashboard.putBoolean("Vision Ready", false); //puts data into dashboard for driver
    }
  }

/**
 * Method for returning the vision offset from center
 * @param centerX Array of the centers of contours
 * @return visionOfset in values from -160 to 160 -> depends on camera resolution
 * Positive = to the right
 * Negative = to the left
 */
  private double getVisionOffset(double[] centerX) {
    double center = (centerX[0] + centerX[1]) / 2;
    return center - 160;
  }


}
