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
import frc.robot.commands.RotateDownCommand;
import frc.robot.commands.RotateUpCommand;
import frc.robot.commands.ShootCommand;

public class ShootingGroup extends CommandGroup {
  /**
   * Add your docs here.
   */
  public ShootingGroup() {
    addSequential(new LineupCommand());
    addSequential(new RotateUpCommand());
    addSequential(new DoNothingCommand(0.15));
    addSequential(new ShootCommand());
    addSequential(new DoNothingCommand(0.1));
    addSequential(new RotateDownCommand());
  }
}
