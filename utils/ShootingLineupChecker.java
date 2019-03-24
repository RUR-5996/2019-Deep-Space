/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.utils;

import edu.wpi.first.wpilibj.command.ConditionalCommand;
import frc.robot.Robot;
import frc.robot.routines.ShootingGroup;

public class ShootingLineupChecker extends ConditionalCommand {
  /**
   * Add your docs here.
   */
  public ShootingLineupChecker() {
    super(new ShootingGroup());
  }

  public boolean condition() {
    return Robot.vision.getTargets() == 2;
  }
}
