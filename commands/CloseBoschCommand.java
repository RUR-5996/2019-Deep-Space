/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class CloseBoschCommand extends Command {

  DigitalInput closedLimitSwitch = new DigitalInput(5);

  //Boolean for checking whether the command is finished -> terminating the command
  private boolean isFinished;

  // declaration of dependencies
  public CloseBoschCommand() {
    requires(Robot.hatchManipulator);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    isFinished = false;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (Robot.hatchManipulator.getClosedSwitchValue() == true) {
      RobotMap.boschMotor.set(Constants.boschMotorSpeed);
    } else {
      RobotMap.boschMotor.set(0);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return !Robot.hatchManipulator.getClosedSwitchValue();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    RobotMap.boschMotor.set(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    RobotMap.boschMotor.set(0);
  }
}
