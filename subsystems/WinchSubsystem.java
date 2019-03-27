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
 * Subsystem for the winch - lower part of the shooter movement.
 */
public class WinchSubsystem extends Subsystem {

  private Encoder shooterWinchEncoder = new Encoder(Constants.shooterEncoderA, Constants.shooterEncoderB, false, Encoder.EncodingType.k4X);

  /**
   * Method for getting encoder.
   * @return current encoder ticks.
   */
  public int getEncoder() {
    return shooterWinchEncoder.get();
  }

  /**
   * Method for reseting the encoder.
   */
  public void resetEncoder(){
    shooterWinchEncoder.reset();
  }

  /**
   * Method for setting the witch motor to move inwards at a pre-set speed.
   */
  public void moveIn(){
    RobotMap.winchMotor.set(-0.45);
  }

  /**
   * Method for setting the winch motor to move outwards at a pre-set speed.
   */
  public void moveOut(){
    RobotMap.winchMotor.set(0.8);
  }

  /**
   * Method for stopping the winch motor.
   */
  public void moveStop(){
    RobotMap.winchMotor.set(0);
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