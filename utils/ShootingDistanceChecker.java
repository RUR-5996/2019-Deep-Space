/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.utils;

import edu.wpi.first.wpilibj.command.ConditionalCommand;
import frc.robot.Robot;
import frc.robot.commands.UltrasonicCommand;

public class ShootingDistanceChecker extends ConditionalCommand {
  /**
   * Add your docs here.
   */
  public ShootingDistanceChecker() {
    super(new ShootingLineupChecker(), new UltrasonicCommand(55));
  }

  public boolean condition() {
    return Robot.ultrasonic.getDistanceCM() > 45 && Robot.ultrasonic.getDistanceCM() < 65;
  }
}
