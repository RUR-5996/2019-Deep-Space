/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class WinchSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  Encoder encoder = new Encoder(3, 4, false, Encoder.EncodingType.k4X);

  public int getEncoder() {
    return encoder.get();
  }

  public void resetEncoder(){
    encoder.reset();
  }

  public void moveIn(){
    RobotMap.winchMotor.set(-0.45);
  }

  public void moveOut(){
    RobotMap.winchMotor.set(0.8);
    System.out.println("Hejbu se");
  }

  public void moveStop(){
    RobotMap.winchMotor.set(0);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}