/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Subsystem for getting and working with vision data
 * Data is sent from DS, where it is processed, via network tables
 */
public class VisionSubsystem extends Subsystem {
  
  private static NetworkTableInstance inst = NetworkTableInstance.getDefault();
  private static NetworkTable table = inst.getTable("GRIP/contours");
  private double[] defaultValue = new double[0];
  private double[] centerX;
  private double[] centerY;
  private double[] width;
  private double[] height;
  private double[] ratio;
  private double visionCenter;

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
    width = table.getEntry("width").getDoubleArray(defaultValue);
    height = table.getEntry("height").getDoubleArray(defaultValue);

    ratio = new double[height.length];
    for(int i = 0; i< centerX.length; i++) {
      try{
        ratio[i] = height[i] / width[i];
      } catch(Exception e) {
        System.out.println("Something went from when getting ratio data: ");
        e.printStackTrace();
      }
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
      if(1 < ratio[0] && ratio[0] < 2 && 1 < ratio[1] && ratio[1] < 2) { //check for validity
        visionCenter = getCenter(centerX);
      }
    } else {
      System.err.println("Targets not found!"); //debug line
      SmartDashboard.putBoolean("Vision Ready", false); //puts data into dashboard for driver
    }
  }

/**
 * Method for getting the current center of vision targets in px
 * @param centerX Array of the centers of contours
 * @return Double value - current X axis center of vision targets
 */
  private double getCenter(double[] centerX) {
    double center = (centerX[0] + centerX[1]) / 2;
    return center;
  }

/**
 * Method for returning the vision offset from center
 * Positive = to the right
 * Negative = to the left
 * @return visionOfset in values from -160 to 160 -> depends on camera resolution
 */
  public double getVisionOffset() {
    return visionCenter - 160;
  }

}
