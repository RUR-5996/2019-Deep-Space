/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.Robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 * Add your docs here.
 */
public class RotateSubsystem extends PIDSubsystem {

  private AHRS ahrs;

  // Constructor, creates the PID controller, sets properties
  public RotateSubsystem() {
    super("Gyroscope", Constants.gyroKp, Constants.gyroKi, Constants.gyroKd);
    setAbsoluteTolerance(Constants.gyroTolerance);
    setInputRange(-180.0f, 180.0f);
    setOutputRange(-1, 1);
    getPIDController().setContinuous(true);
    ahrs = new AHRS(SPI.Port.kMXP);
  }

  // Boolean for checking whether the PID controller is enabled
  public boolean isEnabled() {
    return getPIDController().isEnabled();
  }

  // Method which is passed to the PID controller and used by it
  @Override
  protected double returnPIDInput() {
    return ahrs.getAngle();
  }

  // Method for using the PID values
  @Override
  protected void usePIDOutput(double output) {
    Robot.driveExecutor.setZ(output * 0.5);
  }

  @Override
  public void initDefaultCommand() {
    // setDefaultCommand(new MySpecialCommand());
  }
}
