package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.DriveExecutor;
import frc.robot.Robot;

public class LineTracker extends Subsystem {

  public static AnalogInput line1 = new AnalogInput(0);
  private static AnalogInput line2 = new AnalogInput(1);
  private static AnalogInput line3 = new AnalogInput(2);
  public void lineInit() {
AnalogInput.setGlobalSampleRate(62500);
  }

  int raw1  = line1.getValue();
  int raw2 = line2.getValue();
  int raw3 = line3.getValue();


  public void lineFollowing() {
    if(raw1 < 1000 && raw2 < 1000 && raw3 > 1000) {
      Robot.driveExecutor.setY(0);
    }
    else if(raw1 < 1000) {
      while(raw2 > 1000) {
      Robot.driveExecutor.setY(0.3);
      }
      while(raw2 <= 1000) {

      }
    }
    else if(raw2 < 1000) {
      while(raw2 > 1000) {
      Robot.driveExecutor.setY(-0.3);
      }
    }
    else{
    }

  }

  public int getLine1Value() {
    return line1.getValue();
  }
  public int getLine2Value() {
    return line2.getValue();
  }
  public int getLine3Value() {
    return line3.getValue();
  }
  @Override
  public void initDefaultCommand() {
  }
} 