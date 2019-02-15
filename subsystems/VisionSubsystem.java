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
  private static NetworkTable linesTable = inst.getTable("GRIP/lines");
  private double[] defaultValue = new double[0];
  private double[] centerX, centerY, x1, x2, y1, y2, angle;
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
/*
  public void lineup() {
    if(offset < 0) {
      if(offset < -100) {
        Robot.driveExecutor.setX(-0.16);
      } else if(offset < -50) {
        Robot.driveExecutor.setX(-0.14);
      } else if(offset < -30) {
        Robot.driveExecutor.setX(-0.12);
      } else if(offset < -10) {
        Robot.driveExecutor.setX(-0.1);
      } else if(offset > -10) {
        Robot.driveExecutor.setX(-0.11);
      } else if(offset > -Constants.visionTolerance){
        Robot.driveExecutor.setX(0);
      }
    } else {
      if(offset > 100) {
        Robot.driveExecutor.setX(0.16);
      } else if(offset > 50) {
        Robot.driveExecutor.setX(0.14);
      } else if(offset > 30) {
        Robot.driveExecutor.setX(0.12);
      } else if(offset > 10) {
        Robot.driveExecutor.setX(0.10);
      } else if(offset < 10) {
        Robot.driveExecutor.setX(0.11);
      } else if(offset < Constants.visionTolerance) {
        Robot.driveExecutor.setX(0);
      }
    }
  }
*/
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
    x1 = linesTable.getEntry("x1").getDoubleArray(defaultValue);
    x2 = linesTable.getEntry("x2").getDoubleArray(defaultValue);
    y1 = linesTable.getEntry("y1").getDoubleArray(defaultValue);
    y2 = linesTable.getEntry("y2").getDoubleArray(defaultValue);
    angle = linesTable.getEntry("angle").getDoubleArray(defaultValue);    
    //Lines line = new Lines(x1, x2, y1, y2, angle);
    //Contours contour = new Contours(centerX);
  }

  private void sortData() {
    double temp;
    for(int i = 0; i < x1.length; i++) {
      for(int j = 1; j < (x1.length - i); j++) {
        if(x1[j-1] > x1[j]) {
          temp = x1[j-1];
          x1[j-1] = x1[j];
          x1[j] = temp;

          temp = x2[j-1];
          x2[j-1] = x2[j];
          x2[j] = temp;

          temp = y1[j-1];
          y1[j-1] = y1[j];
          y1[j] = temp;

          temp = y2[j-1];
          y2[j-1] = y2[j];
          y2[j] = temp;

          temp = angle[j-1];
          angle[j-1] = angle[j];
          angle[j] = temp;
        }
      }
    }
  }

  public void vision() {
    System.out.println(x1.length + " + "  + centerX.length);
    double[] lineCenterX;
    if((centerX.length * 2) == x1.length) {
      System.out.println("LINE 0: " + x1[0] + " X0: " + x2[0]);
        System.out.println("LINE 1: " + x1[1] + "  X1: " + x2[1]);
        System.out.println("LINE 2: " + x1[2] + "  X2: " + x2[2]);
        System.out.println("LINE 3: " + x1[3] + " X3: " + x2[3]);
    }
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
    //sortData();
    //vision();
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
