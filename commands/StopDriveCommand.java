/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class StopDriveCommand extends InstantCommand {
  /**
   * Command for stopping robot drive.
   * Is instantly terminated after initialization code runs.
   * Dependent on drive subsystem.
   */
  public StopDriveCommand() {
    super();
    requires(Robot.drive);
  }

  /**
   * Initialization code, runs when command is called.
   */
  @Override
  protected void initialize() {
    Robot.driveExecutor.setX(0);
    Robot.driveExecutor.setY(0);
  }

}
