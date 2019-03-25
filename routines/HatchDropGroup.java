/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.routines;

import frc.robot.commands.*;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
   * Command group for dropping off hatch.
   */
public class HatchDropGroup extends CommandGroup {
  /**
   * Command grouip for dropping off hatches.
   */
  public HatchDropGroup() {
    addSequential(new WallCommand());
    addSequential(new CloseHatchCommand());
    addSequential(new MoveBackCommand());
  }
}
