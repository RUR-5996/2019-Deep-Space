/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.utils;

import edu.wpi.first.wpilibj.command.ConditionalCommand;
import frc.robot.Robot;

public class ShootingNullChecker extends ConditionalCommand {
  /**
   * Add your docs here.
   */
  public ShootingNullChecker() {
    super(new ShootingPositionChecker());
  }

  public boolean condition() {
    return Robot.shooterRotate.shooterPosition != null;
  }
}
