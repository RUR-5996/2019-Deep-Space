/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;
import frc.robot.RobotMap;
/**
 * Subsystem for the ramp winch.
 */
public class RampSubsystem extends Subsystem {

  private Encoder rampWinchEncoder = new Encoder(Constants.rampEncoderA, Constants.rampEncoderB, false, Encoder.EncodingType.k4X);

  /**
   * Method for getting encoder.
   * @return current encoder ticks.
   */
  public int getEncoder() {
    return rampWinchEncoder.get();
  }

  /**
   * Method for reseting the encoder.
   */
  public void resetEncoder(){
    rampWinchEncoder.reset();
  }

  /**
   * Method for setting the witch motor to move inwards at a pre-set speed.
   */
  public void moveIn(){
    RobotMap.rampWinchMotor.set(-0.5);
  }

  /**
   * Method for setting the winch motor to move outwards at a pre-set speed.
   */
  public void moveOut(){
    RobotMap.rampWinchMotor.set(0.5);
  }

  /**
   * Method for stopping the winch motor.
   */
  public void moveStop(){
    RobotMap.rampWinchMotor.set(0);
  }

  /**
   * Default method required by the subsystem. Unused.
   */
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}