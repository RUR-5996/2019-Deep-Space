/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.enumeration.ShooterPosition;

/*
 * Command which rotates the shooter upwards
 */
public class RotateUpCommand extends Command {

  public RotateUpCommand() {
    requires(Robot.shooterRotate);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.shooterRotate.resetCounter();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(!(Robot.shooterRotate.shooterPosition == null)) {
      Robot.shooterRotate.rotateShooter(Robot.shooterRotate.shooterPosition);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    if(Robot.shooterRotate.shooterPosition == ShooterPosition.CARGO) {
      if(Robot.shooterRotate.getCounter() >= Constants.cargoPos) {
        return true;
      }

    } else if(Robot.shooterRotate.shooterPosition == ShooterPosition.MIDDLE_ROCKET) {
      if(Robot.shooterRotate.getCounter() >= Constants.middlePos) {
        return true;
      }
    } else if(Robot.shooterRotate.shooterPosition == ShooterPosition.LOW_ROCKET) {
      if(Robot.shooterRotate.getCounter() >= Constants.lowPos) {
        return true;
      }
    } else if(Robot.shooterRotate.shooterPosition == ShooterPosition.INTAKE) {
      if(Robot.shooterRotate.getCounter() >= Constants.intakePos) {
        return true;
      }
    } else {
      return true;
    }
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.shooterRotate.stopRotate();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.shooterRotate.stopRotate();
  }
}