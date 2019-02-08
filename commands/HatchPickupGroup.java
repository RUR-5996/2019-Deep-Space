/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class HatchPickupGroup extends CommandGroup {
  /**
   * Command Group for picking up hatch
   */
  public HatchPickupGroup() {
    addSequential(new WallCommand());
    addSequential(new OpenHatchCommand());
  }
}
