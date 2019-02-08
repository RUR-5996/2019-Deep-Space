/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.Constants;
import frc.robot.Robot;

/**
 * Command for shooting from the shooter. Timed - will execute only for some time, then terminate.
 */
public class ShootCommand extends TimedCommand {
  /**
   * Constructor. Declares dependencies and sets timeout.
   */
  public ShootCommand() {
    super(Constants.shootingTime);
    requires(Robot.shooter);
  }

  /**
   * Called just before this Command runs the first time
   */
  @Override
  protected void initialize() {
  }

  /**
   * Called repeatedly when this Command is scheduled to run.
   * Speed < 0 = outwards.
   * Speed > 0 = inwards.
   */
  @Override
  protected void execute() {
    Robot.shooter.shoot(-0.95);
  }

  /**
   * Called once after timeout. Stops shooter.
   */
  @Override
  protected void end() {
    Robot.shooter.stop();
  }

  /**
   * Called when another command which requires one or more of the same 
   * subsystems is scheduled to run. Stops shooter.
   */
  @Override
  protected void interrupted() {
    Robot.shooter.stop();
  }
}
