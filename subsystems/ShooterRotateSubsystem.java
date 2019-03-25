/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;
import frc.robot.RobotMap;
import frc.robot.enumeration.ShooterPosition;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class ShooterRotateSubsystem extends Subsystem {

  public ShooterPosition shooterPosition;
  private Counter count = new Counter(8);
  private DigitalInput limitSwitch = new DigitalInput(9);

  public double getCounter() {
    return count.get();
  }

  public boolean getSwitch() {
    return limitSwitch.get();
  }

  public void resetCounter() {
    count.reset();
  }

  public void rotateDown(){
    RobotMap.shooterBoschMotor.set(0.75);
  }

  public void rotateUp(){
    RobotMap.shooterBoschMotor.set(-0.75);
  }

  public void stopRotate(){
    RobotMap.shooterBoschMotor.set(0);
  }

  public void rotateShooter(ShooterPosition pos) {
    switch(pos) {
      case CARGO:
        if(getCounter() < Constants.cargoPos) {
          rotateUp();
        } else {
          stopRotate();
        }
        break;
      case MIDDLE_ROCKET:
        if(getCounter() < Constants.middlePos) {
          rotateUp();
        } else {
          stopRotate();
        }
        break;
      case LOW_ROCKET:
        if(getCounter() < Constants.lowPos) {
          rotateUp();
        } else {
          stopRotate();
        }
        break;
      case INTAKE:
        if(getCounter() < Constants.intakePos) {
          rotateUp();
        } else {
          stopRotate();
        }
        break;
    }
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}