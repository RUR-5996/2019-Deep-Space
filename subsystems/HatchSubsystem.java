/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem for the hatch manipulator
 */
public class HatchSubsystem extends Subsystem {

  /**
   * Limit switches
   * True = unpressed
   * False = pressed
   */
  private DigitalInput openedLimitSwitch = new DigitalInput(6);
  private DigitalInput closedLimitSwitch = new DigitalInput(5);

  @Override
  public void initDefaultCommand() {
    // setDefaultCommand(new );
  }

  /**
   * Method for returning value of limit switch for opened hatch
   * @return boolean true for unpressed, false for pressed 
   */
  public boolean getOpenedSwitchValue() {
    return openedLimitSwitch.get();
  } 

  /**
   * Method for returning value of limit switch for closed hatch
   * @return boolean true for unpressed, false for pressed
   */
  public boolean getClosedSwitchValue() {
    return closedLimitSwitch.get();
  }

  /**
   * Method for opening the hatch manipulator.
   * Opens the manipulator until limit switch is pressed, then stops.
   */
  public void openHatch() {
    if (getOpenedSwitchValue()) {
      RobotMap.hatchMotor.set(-Constants.boschMotorSpeed);
    } else {
      stop();
    }
  }

  /**
   * Method for closing the hatch manipulator.
   * Closes the manipulator until limit switch is pressed, then stops.
   */
  public void closeHatch() {
    if (getClosedSwitchValue()) {
      RobotMap.hatchMotor.set(Constants.boschMotorSpeed);
    } else {
      stop();
    }
  }

  /**
   * Method for stopping the motor on the hatch manipulator.
   */
  public void stop() {
    RobotMap.hatchMotor.set(0);
  }

}
