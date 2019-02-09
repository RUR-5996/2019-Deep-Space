/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * 
 */
public class LineTrackerSubsystem extends Subsystem {

  AnalogInput lineTrackerLeft = new AnalogInput(0);
  AnalogInput lineTrackerCenter = new AnalogInput(1);
  AnalogInput lineTrackerRight = new AnalogInput(2);

  @Override
  public void initDefaultCommand() {
    
  }

  public int getLeftValue() {
    return lineTrackerLeft.getValue();
  }

  public int getCenterValue() {
    return lineTrackerCenter.getValue();
  }

  public int getRightValue() {
    return lineTrackerRight.getValue();
  }
} 
