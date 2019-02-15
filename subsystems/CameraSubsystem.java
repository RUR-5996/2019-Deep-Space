/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;
import frc.robot.Robot.CameraSettings;

/**
 * Add your docs here.
 */
public class CameraSubsystem extends Subsystem {

  private UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void cameraInit() {
    new Thread( () -> {
      camera = CameraServer.getInstance().startAutomaticCapture();
      camera.setResolution(Constants.imageWidth, Constants.imageHeight);
      camera.setFPS(Constants.imageFPS);
    }).start();
  }

  public void setCamera(CameraSettings cameraSettings) {
    if(cameraSettings == CameraSettings.NORMAL) {
      visionSettings();
    } else if(cameraSettings == CameraSettings.VISION) {
      normalSettings();
    }
  }

  private void visionSettings() {
    camera.setExposureManual(0);
    camera.setBrightness(0);
  }

  private void normalSettings() {
    camera.setExposureAuto();
    camera.setBrightness(100);
  }
}
