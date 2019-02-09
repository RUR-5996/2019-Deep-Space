package frc.robot.utils;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.Button;
import frc.robot.Constants;

/**
 * Class for treating triggers on an XboxController as a button.
 * Threshold can be set manually.
 */
public class TriggerButton extends Button {

    private XboxController controller;
    private int hand;

    /**
     * Controller for setting variables.
     * @param controller XboxController which is used.
     * @param hand side, 0 for left, 1 for right.
     */
    public TriggerButton(XboxController controller, int hand) {
        this.controller = controller;
        this.hand = hand;
    }

    /**
     * Default method which is required for all buttons.
     * Returns either true or false.
     * Returns boolean for left trigger if 0 is selected.
     * Returns boolean for right trigeer if 1 is selected.
     */
    public boolean get() {
        if(hand == 0) {
            return controller.getTriggerAxis(Hand.kLeft) > Constants.triggerThreshold;
        } else if(hand == 1) {
            return controller.getTriggerAxis(Hand.kRight) > Constants.triggerThreshold;
        }
        return false;
    }

}