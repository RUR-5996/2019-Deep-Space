/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.RobotMap;
import frc.robot.utils.ReportingInterface;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Subsystem for the hatch manipulator
 */
public class HatchSubsystem extends Subsystem implements ReportingInterface {

  /**
   * Limit switches
   * True = unpressed
   * False = pressed
   */
  private DigitalInput openedLimitSwitch = new DigitalInput(Constants.openHatchSwitch);
  private DigitalInput closedLimitSwitch = new DigitalInput(Constants.closeHatchSwitch);

  private DigitalInput hatchSwitch = new DigitalInput(Constants.hatchSwitch);

  private Servo hatchServo = new Servo(Constants.hatchServo);

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
   * Method for figuring out if we have a switch loaded.
   * @return boolean true if not loaded, false if loaded.
   */
  public boolean getHatchSwitch() {
    return hatchSwitch.get();
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

  /**
   * Method for opening the hatch servo.
   */
  public void openServo() {
    hatchServo.set(0);
  }

  /**
   * Method for closing the hatch servo.
   */
  public void closeServo() {
    hatchServo.set(1);
  }

  /**
   * Method for getting whether the servo is in open position or not.
   * @return boolean is servo open.
   */
  public boolean isServoOpen() {
    return hatchServo.get() == 0;
  }

  public void report() {
    SmartDashboard.putBoolean("Opened Hatch Switch", getOpenedSwitchValue());
    SmartDashboard.putBoolean("Closed Hatch Switch", getClosedSwitchValue());
    SmartDashboard.putBoolean("Hatch Loaded Switch", getHatchSwitch());
    SmartDashboard.putBoolean("Servo opened",isServoOpen());
  }

}
