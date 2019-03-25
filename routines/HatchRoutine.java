/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.routines;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.DoNothingCommand;
import frc.robot.commands.StopDriveCommand;

public class HatchRoutine extends CommandGroup {
  /**
   * First command in the chain for dropping / picking up hatches.
   * Calls PositionChecker.
   */
  public HatchRoutine() {
    addSequential(new StopDriveCommand());
    addSequential(new DoNothingCommand(0.1));
    addSequential(new PositionChecker());
  }
}
