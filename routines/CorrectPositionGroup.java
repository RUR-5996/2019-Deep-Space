/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.routines;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.DoNothingCommand;
import frc.robot.commands.RotateCommand;

public class CorrectPositionGroup extends CommandGroup {
  /**
   * Group for checking whether robot is within angle tolerance of gyro setpoint.
   * Calls DistanceChecker.
   */
  public CorrectPositionGroup() {
    addSequential(new RotateCommand(0));
    addSequential(new DoNothingCommand(0.25));
    addSequential(new DistanceChecker());
  }
}
