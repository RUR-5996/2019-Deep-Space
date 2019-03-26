/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.routines;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.DoNothingCommand;
import frc.robot.commands.LineupCommand;
import frc.robot.utils.HatchSelector;

public class LineupGroup extends CommandGroup {
  /**
   * Command group for lining up for hatch pick up / drop off.
   * Calls HatchSelector.
   */
  public LineupGroup() {
    addSequential(new LineupCommand());
    addSequential(new DoNothingCommand(0.35));
    addSequential(new HatchSelector());
  }
}
