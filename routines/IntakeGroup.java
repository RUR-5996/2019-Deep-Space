/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.routines;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.BallIntakeGroup;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.ShooterReturnGroup;

public class IntakeGroup extends CommandGroup {
  /**
   * Command grouip for intaking balls.
   * Calls ShooterReturnGroup.
   */
  public IntakeGroup() {
    addSequential(new BallIntakeGroup());
    addSequential(new IntakeCommand());
    addSequential(new ShooterReturnGroup());
  }
}
