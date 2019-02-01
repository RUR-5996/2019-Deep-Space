/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class OpenBoschCommand extends Command {

  // Boolean for checking whether the command is finished -> terminating the
  // command
  private boolean isFinished;

  // declaration of dependencies
  public OpenBoschCommand() {
    requires(Robot.bosch);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    isFinished = false;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (Robot.bosch.getPosition() <= Constants.boschLowerBound) {
      while (Robot.bosch.getPosition() < Constants.boschUpperBound) {
        Robot.bosch.setPosition(Robot.bosch.getPosition() + Robot.bosch.counter.get() / 10000);
        RobotMap.boschMotor.set(0.5);
      }
      RobotMap.boschMotor.set(0);
      Robot.bosch.counter.reset();
    }
    isFinished = true;
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return isFinished;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
