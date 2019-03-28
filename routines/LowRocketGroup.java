/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.routines;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.DoNothingCommand;
import frc.robot.commands.LowRocketShootCommand;
import frc.robot.commands.ShooterRotateInCommand;
import frc.robot.commands.ShooterRotateOutCommand;

public class LowRocketGroup extends CommandGroup {
  /**
   * Command group for firing balls into low rocket.
   */
  public LowRocketGroup() {
    addSequential(new ShooterRotateOutCommand());
    addSequential(new DoNothingCommand(0.15));
    addSequential(new LowRocketShootCommand());
    addSequential(new DoNothingCommand(0.1));
    addSequential(new ShooterRotateInCommand());
  }
}
