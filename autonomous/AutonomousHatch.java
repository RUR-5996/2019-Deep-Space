/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.CloseHatchCommand;
import frc.robot.commands.DoNothingCommand;
import frc.robot.commands.LineupCommand;
import frc.robot.commands.MoveBackCommand;
import frc.robot.commands.OpenHatchCommand;
import frc.robot.commands.RotateCommand;
import frc.robot.commands.StopDriveCommand;
import frc.robot.commands.UltrasonicCommand;
import frc.robot.commands.WallCommand;

public class AutonomousHatch extends CommandGroup {
  /**
   * Add your docs here.
   */
  public AutonomousHatch() {
    addSequential(new StopDriveCommand());
    addSequential(new DoNothingCommand(0.1));
    hatchCycle();
  }

  private void hatchCycle() {
    fixPosition();
    fixDistance();
  }

  private void fixPosition() {
    if(!(Math.abs(Math.abs(Robot.rotate.getPosition()) - Robot.rotate.getSetpoint()) < Constants.gyroTolerance)) {
      addSequential(new RotateCommand(0));
      addSequential(new DoNothingCommand(0.25));
    }
  }

  private void fixDistance() {
    if(Robot.ultrasonic.getDistanceCM() > 50 && Robot.ultrasonic.getDistanceCM() < 85 && Robot.vision.getTargets() == 2) {
      lineup();
    } else {
      addSequential(new UltrasonicCommand(80));
    }
  }

  private void lineup() { 
    if(Robot.vision.getTargets() == 2) {
      addSequential(new LineupCommand());
      addSequential(new DoNothingCommand(0.35));
      selectHatch();
    }
  }

  private void selectHatch() {
    if(Robot.hatch.getHatchSwitch()) {
      addSequential(new WallCommand());
      addSequential(new OpenHatchCommand());
      addSequential(new MoveBackCommand());
    } else {
      addSequential(new WallCommand());
      addSequential(new CloseHatchCommand());
      addSequential(new MoveBackCommand());
    }
  }

}
