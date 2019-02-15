/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.RobotMap;
import frc.robot.utils.ReportingInterface;

/**
 * Add your docs here.
 */
public class ShooterTiltSubsystem extends Subsystem implements ReportingInterface {
  AnalogPotentiometer pot = new AnalogPotentiometer(0, 270, -135);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public double getPot() {
    return pot.get();
  }

  public void stop() {
    RobotMap.tilter.set(0);
  }

  public void setSpeed(double speed) {
    RobotMap.tilter.set(speed);
  }

  public void intake() {
    if(getPot() > Constants.intakePos) {
      setSpeed(-Constants.tilterSpeed);
    } else {
      stop();
    }
  }

  public void startPos() {
    if(getPot() < Constants.startingPos) {
      setSpeed(2 * Constants.tilterSpeed);
    } else {
      stop();
    }
  }

  public void report() {
    SmartDashboard.putNumber("Potentiometer", getPot());
  }
}
