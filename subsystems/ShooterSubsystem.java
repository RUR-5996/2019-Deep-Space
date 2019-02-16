/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FollowerType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.RobotMap;
import frc.robot.utils.ReportingInterface;

/**
 * Subsystem for the shooter.
 */
public class ShooterSubsystem extends Subsystem implements ReportingInterface {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private DigitalInput ballLimitSwitch = new DigitalInput(Constants.shooterSwitch);

  public void shooterInit() {
    RobotMap.shooter2.follow(RobotMap.shooter1, FollowerType.PercentOutput);
    RobotMap.shooter4.follow(RobotMap.shooter3, FollowerType.PercentOutput);
    RobotMap.shooter3.follow(RobotMap.shooter1, FollowerType.PercentOutput);
    RobotMap.shooter3.setInverted(true);
  }

  public void shoot(double speed) {
    RobotMap.shooter1.set(speed);
    //RobotMap.shooter3.set(speed);
  }

  public void intake() {
    RobotMap.shooter1.set(Constants.intakeSpeed);
    //RobotMap.shooter3.set(Constants.intakeSpeed);
  }

  public void stop(){
    RobotMap.shooter1.set(0);
    //RobotMap.shooter3.set(0);
  }

  public boolean getSwitch() {
    return ballLimitSwitch.get();
  }

  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    //setDefaultCommand(new ShootCommand());
  }

  public void report() {
    SmartDashboard.putBoolean("Ball limit switch", getSwitch());
  }
}
