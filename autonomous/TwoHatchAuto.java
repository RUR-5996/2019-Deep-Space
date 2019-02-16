/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.DoNothingCommand;
import frc.robot.commands.MoveLeftCommand;
import frc.robot.commands.MoveRightCommand;
import frc.robot.commands.OpenHatchCommand;
import frc.robot.enumeration.StartingPosition;

public class TwoHatchAuto extends CommandGroup {
  /**
   * Add your docs here.
   */
  public TwoHatchAuto(StartingPosition startingPosition) {

    addSequential(new OpenHatchCommand());

    switch(startingPosition) {
      case LEFT:
        addSequential(new AutonomousHatch());
        addSequential(new DoNothingCommand(0.25));
        addSequential(new MoveLeftCommand(0.75));
        addSequential(new DoNothingCommand(0.25));
        addSequential(new AutonomousHatch());
        addSequential(new DoNothingCommand(0.25));
        addSequential(new MoveLeftCommand(0.75));
        addSequential(new DoNothingCommand(0.25));
        addSequential(new AutonomousHatch());
        break;
      case RIGHT:
        addSequential(new AutonomousHatch());
        addSequential(new DoNothingCommand(0.25));
        addSequential(new MoveRightCommand(0.75));
        addSequential(new DoNothingCommand(0.25));
        addSequential(new AutonomousHatch());
        addSequential(new DoNothingCommand(0.25));
        addSequential(new MoveRightCommand(0.75));
        addSequential(new DoNothingCommand(0.25));
        addSequential(new AutonomousHatch());
        break;
      case CENTER:
        break;
      default:
        System.out.println("Error with starting position");
        break;
    }
  }
}
