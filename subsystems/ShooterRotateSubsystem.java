/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import frc.robot.RobotMap;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class ShooterRotateSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  Potentiometer pot = new AnalogPotentiometer(0, 270, 0); // second parameter should be 270

  public double getPotentiometer(){
    return pot.get();
  }

  public void rotateDown(){
    RobotMap.shooterBoschMotor.set(-0.4);
    //System.out.println("Rotating down");
  }

  public void rotateUp(){
    RobotMap.shooterBoschMotor.set(0.4);
    //System.out.println("Rotating up");
  }

  public void stopRotate(){
    RobotMap.shooterBoschMotor.set(0);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}