/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.ConditionalCommand;
import frc.robot.Robot;

public class LineupSelector extends ConditionalCommand {

  /**
   * Conditional command for finding out whether robot sees just 2 vision targets.
   * If yes, LineupCommand is invoked.
   * If not, robot moves to 80cm via ultrasonic.
   */
  public LineupSelector() {
    super(new LineupCommand(), new UltrasonicCommand(80));
  }

  /**
   * Condition for the command.
   */
  public boolean condition() {
    return Robot.vision.getTargets() == 2;
  }
}
