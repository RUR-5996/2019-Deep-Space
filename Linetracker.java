package frc.robot.sensors;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.DriveExecutor;
import frc.robot.Robot;

public class Linetracker extends Subsystem {

  public static AnalogInput line1 = new AnalogInput(0);
  private static AnalogInput line2 = new AnalogInput(1);
  private static AnalogInput line3 = new AnalogInput(2);

  int raw1  = line1.getValue();
  int raw2 = line2.getValue();
  int raw3 = line3.getValue();

  public void lineFollowing() {
    if(raw1 < 1000 && raw2 < 1000 && raw3 > 1000) {
      Robot.driveExecutor.setY(0);
    }
    else if(raw1 < 1000) {
      while(raw2 > 1000) {
      Robot.driveExecutor.setY(0.1);
      }
      while(raw2 <= 1000) {

      }
    }
    else if(raw2 < 1000) {
      while(raw2 > 1000) {
      Robot.driveExecutor.setY(-0.1);
      }
    }
    else{
    }

  }

  @Override
  public void initDefaultCommand() {
  }
}