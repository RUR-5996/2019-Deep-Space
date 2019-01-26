/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 * Add your docs here.
 */
public class BoschSeatMotorSubsystem extends Subsystem {
  public Counter counter;
  private int position;

  public void counterInit() {
    counter = new Counter(new DigitalInput(2));
    position = 0;
    counter.setMaxPeriod(4);
    counter.setReverseDirection(true);
  }

  @Override
  public void initDefaultCommand() {
    //setDefaultCommand(new BoschC);
  }

  public int getPosition() {
    return this.position;
  }

  public void setPosition(int position) {
    this.position = position;
  }

  public int getCounter() {
    return this.counter.get();
  }

}
