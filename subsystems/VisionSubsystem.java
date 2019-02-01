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

/**
 * Add your docs here.
 */
public class VisionSubsystem extends Subsystem {
  
  private static NetworkTableInstance inst = NetworkTableInstance.getDefault();
  private static NetworkTable table = inst.getTable("GRIP/contours");
  double[] defaultValue = new double[0];
  double[] centerX;
  double[] centerY;
  double[] width;
  double[] height;
  double[] ratio;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void setVars() {
    centerX = table.getEntry("centerX").getDoubleArray(defaultValue);
    centerY = table.getEntry("centerY").getDoubleArray(defaultValue);
    width = table.getEntry("width").getDoubleArray(defaultValue);
    height = table.getEntry("height").getDoubleArray(defaultValue);

    ratio = new double[height.length];
    for(int i = 0; i< centerX.length; i++) {
      ratio[i] = height[i] / width[i];
    }
  }

  public void visionLogic() {
    setVars();

    if(centerX.length == 2) {
      System.out.println(ratio[0]);
    }
  }

}
