/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class BoschSeatMotorSubsystem extends Subsystem {

  private DigitalInput openedLimitSwitch = new DigitalInput(6);
  private DigitalInput closedLimitSwitch = new DigitalInput(5);

  @Override
  public void initDefaultCommand() {
    // setDefaultCommand(new BoschC);
  }

  public boolean getOpenedSwitchValue() {
    return openedLimitSwitch.get();
  } 

  public boolean getClosedSwitchValue() {
    return closedLimitSwitch.get();
  }

}
