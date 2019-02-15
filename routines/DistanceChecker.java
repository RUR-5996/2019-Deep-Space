/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.routines;

import edu.wpi.first.wpilibj.command.ConditionalCommand;
import frc.robot.Robot;
import frc.robot.commands.UltrasonicCommand;
import frc.robot.utils.LineupSelector;

/**
 * Add your docs here.
 */
public class DistanceChecker extends ConditionalCommand {
    public DistanceChecker() {
        super(new LineupSelector(), new UltrasonicCommand(80));
    }

    public boolean condition() {
        return Robot.ultrasonic.getDistanceCM() > 50 && Robot.ultrasonic.getDistanceCM() < 85 && Robot.vision.getTargets() == 2;
    }
}
