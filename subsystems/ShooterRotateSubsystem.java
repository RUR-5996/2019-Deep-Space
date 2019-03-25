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
 * Subsystem for rotating the shooter.
 */
public class ShooterRotateSubsystem extends Subsystem {

  public ShooterPosition shooterPosition;
  private Counter count = new Counter(8);
  private DigitalInput limitSwitch = new DigitalInput(9);

  /**
   * Method for getting current counter ticks.
   * @return double counter ticks.
   */
  public double getCounter() {
    return count.get();
  }

  /**
   * Method for getting state of limit switch.
   * @return boolean is limit switch pressed.
   */
  public boolean getSwitch() {
    return limitSwitch.get();
  }

  /**
   * Method for reseting the counter.
   */
  public void resetCounter() {
    count.reset();
  }

  /**
   * Method for rotating the shooter down at a pre-set speed.
   */
  public void rotateDown(){
    RobotMap.shooterBoschMotor.set(0.75);
  }

  /**
   * Method for rotating the shooter up at a pre-set speed.
   */
  public void rotateUp(){
    RobotMap.shooterBoschMotor.set(-0.75);
  }

  /**
   * Method for stopping the shooter from rotating.
   */
  public void stopRotate(){
    RobotMap.shooterBoschMotor.set(0);
  }

  /**
   * Method for rotating the shooter to a position from ShooterPosition enum.
   * @param pos desired position of the shooter.
   */
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

  /**
   * Default required method. Unused.
   */
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}