/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.utils;

import frc.robot.Constants;

import edu.wpi.first.wpilibj.command.ConditionalCommand;
import frc.robot.Robot;
import frc.robot.routines.ShooterCorrectPositionGroup;

public class ShootingRotationChecker extends ConditionalCommand {
  /**
   * Add your docs here.
   */
  public ShootingRotationChecker() {
    super(new ShootingDistanceChecker(), new ShooterCorrectPositionGroup());
  }

  public boolean condition() {
    return Math.abs(Math.abs(Robot.rotate.getPosition()) - Robot.rotate.getSetpoint()) < Constants.gyroTolerance;
  }
}
