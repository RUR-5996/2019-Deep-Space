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
import frc.robot.Constants;
import frc.robot.Robot;

/**
 * Subsystem for getting and working with vision data
 * Data is sent from DS, where it is processed, via network tables
 */
public class VisionSubsystem extends PIDSubsystem {
  
  private static NetworkTableInstance inst = NetworkTableInstance.getDefault();
  private static NetworkTable contoursTable = inst.getTable("GRIP/contours");
  private double[] defaultValue = new double[0];
  private double[] centerX;
  private double offset;

  /**
   * Constructor, creates and sets properties for the PID controller
   */
  public VisionSubsystem() {
    super("Vision", Constants.visionKp, Constants.visionKi, Constants.visionKd);
    setAbsoluteTolerance(Constants.visionTolerance);
    setInputRange(-60, 60);
    setOutputRange(-0.4, 0.4);
    getPIDController().setContinuous(true);
  }

  /**
	 * Method for passing info to the PID controller
	 * @return X-axis offset processed from camera
	 */
	protected double returnPIDInput() {
    return offset;
  }
  
  /**
   * Method for getting the X-axis offset from the center
   * @return double offset in px - depends on resolution
   * Should be between -160 and 160
   */
  public double getOffset(){
    return offset;
  }

  /**
   * Method for checking whether the controller is enabled.
   * @return boolean true = enabled, false = disabled.
   */
  public boolean isEnabled() {
    return getPIDController().isEnabled();
  }

	/**
	 * Method for using the output of the PID controller
	 * @param output method for driving forward/backwards
	 * In range of -1 to 1
	 */
	protected void usePIDOutput(double output) {
    if(output > 0) {
      if(output < 0.16 && output > 0.03) {
        output = 0.17;
      }
    } else if(output < 0) {
      if(output > -0.16 && output < -0.03) {
        output = -0.17;
      }
    }
		Robot.driveExecutor.setX(-output);
	}

  /**
   * Default required method by subsystem.
   */
  public void initDefaultCommand() {
  }

  /**
   * Method for getting how many targets the vision system sees.
   * @return Number of targets. Should be equal to 2 if valid.
   */
  public int getTargets() {
    return centerX.length;
  }

  /**
   * Method for getting vision data from network tables
   */
  private void setVars() {
    centerX = contoursTable.getEntry("centerX").getDoubleArray(defaultValue);
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
      //System.err.println("Targets not found!"); //debug line
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
