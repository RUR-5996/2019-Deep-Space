/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.enumeration.DrivingType;

/**
 * Add your docs here.
 */
public class StopAllCommand extends InstantCommand {
  /**
   * Add your docs here.
   */
  public StopAllCommand() {
    super();
    requires(Robot.drive);
    requires(Robot.hatch);
    requires(Robot.rotate);
    requires(Robot.shooter);
    requires(Robot.shooterRotate);
    requires(Robot.ultrasonic);
    requires(Robot.vision);
    requires(Robot.winch);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    Robot.drivingType = DrivingType.FIELD_ORIENTED;
  }

}
