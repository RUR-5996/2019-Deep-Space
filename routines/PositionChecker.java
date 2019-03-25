/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.routines;

import edu.wpi.first.wpilibj.command.ConditionalCommand;
import frc.robot.Constants;
import frc.robot.Robot;

public class PositionChecker extends ConditionalCommand {

  /**
   * Conditional Command for checking whether robot is within tolerance of pre-set angle.
   * Calls distance checker if true, otherwise calls CorrectPositionGroup.
   */
  public PositionChecker() {
    super(new DistanceChecker(), new CorrectPositionGroup());
  }

  public boolean condition() {
    return Math.abs(Math.abs(Robot.rotate.getPosition()) - Robot.rotate.getSetpoint()) < Constants.gyroTolerance;
  }
}
