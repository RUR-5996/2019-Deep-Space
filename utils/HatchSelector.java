/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.utils;

import edu.wpi.first.wpilibj.command.ConditionalCommand;
import frc.robot.Robot;
<<<<<<< HEAD
import frc.robot.routines.*;
=======
import frc.robot.commands.HatchDropGroup;
import frc.robot.commands.HatchPickupGroup;
>>>>>>> origin/develop

/**
 * Conditional command. Figures out whether we have a hatch loaded or not.
 * Then calls the required command. Uses limit switch feedback from hatch
 * subsystem.
 */
public class HatchSelector extends ConditionalCommand {
  /**
   * Constructor, defined which command to call if condition is true or false.
   * Calls first command if true, calls second if false.
   */
  public HatchSelector() {
    super(new HatchPickupGroup(), new HatchDropGroup());
  }

  /**
   * Method for figuring out whether we want to pick up or drop off.
   * @return boolean true - hatch is not loaded, false - hatch is loaded.
   */
  public boolean condition() {
    return Robot.hatch.getHatchSwitch();
  }

}
