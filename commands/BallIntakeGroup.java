/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BallIntakeGroup extends CommandGroup {
  /**
   * Group for extending the Shooter into intake position.
   */
  public BallIntakeGroup() {
    addSequential(new SetIntakeRotateCommand());
    addSequential(new RotateUpCommand());
    addSequential(new WinchOutCommand());
  }
}