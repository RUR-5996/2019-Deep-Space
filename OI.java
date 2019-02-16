/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.*;
import frc.robot.routines.*;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.utils.TriggerButton;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	public XboxController controller = new XboxController(0); //creates a new controller object for XboXController and assigns its port value to 0
	//-----------------------------------------------------------------------------------------------
	//					XBOX
	//values for controller buttons
	final static int aButton = 1;
	final static int bButton = 2;
	final static int xButton = 3;
	final static int yButton = 4;
	final static int leftBumper = 5;
	final static int rightBumper = 6;
	final static int backButton = 7;
	final static int startButton = 8; 
	final static int lStickButton = 9;
	final static int rStickButton = 10;
	final static int leftTriggerButton = 0;
	final static int rightTriggerButton = 1;
															//Change these to re-bind
	//values for controller axis
	final static int lStickXAxis = 0;
	final static int lStickYAxis = 1;
	final static int lTriggerAxis = 2;
	final static int rTriggerAxis = 3;
	final static int rStickXAxis = 4;
	final static int rStickYAxis = 5;
	
	//---------------------------------------------------------------------------------------------------
	//defines and creates button objects for each xbox button
	private final Button aBut = new JoystickButton(controller, aButton);
	private final Button bBut = new JoystickButton(controller, bButton);
	private final Button xBut = new JoystickButton(controller, xButton);
	private final Button yBut = new JoystickButton(controller, yButton);
	private final Button leftBum = new JoystickButton(controller, leftBumper);
	private final Button rightBum = new JoystickButton(controller, rightBumper);
	private final Button startBut = new JoystickButton(controller, startButton);
	private final Button backBut = new JoystickButton(controller, backButton);
	private final Button lStickBut = new JoystickButton(controller, lStickButton);
	private final Button rStickBut = new JoystickButton(controller, rStickButton);
	private final TriggerButton lTriggerButton = new TriggerButton(controller, leftTriggerButton);
	private final TriggerButton rTriggerButton = new TriggerButton(controller, rightTriggerButton);
	private final POVButton povUp = new POVButton(controller, 0);
	private final POVButton povRight = new POVButton(controller, 90);
	private final POVButton povDown = new POVButton(controller, 180);
	private final POVButton povLeft = new POVButton(controller, 270);
	//------------------------------------------------------------------------------------------------------
	//methods for driving
	/**
	 * Method for driving. Used for X axis.
	 * @return X axis value of the left stick on the controller.
	 */
	public double getDriveForward() {
		return getLStickXAxis();			
	}
	
	/**
	 * Method for driving. Used for Y axis.
	 * @return Y axis value of the left stick on the controller.
	 */
	public double getDriveSideways() {
		return getLStickYAxis();				
	}
	
	/**
	 * Method for driving. Used for Z axis.
	 * @return Z axis value, got from the triggers on the controller.
	 * Value < 0 = to the left
	 * Value > 0 = to the right
	 * Range from -1 to 1
	 */
	public double getDriveRotation() {
		return getTriggerAxis();
	}
	
	//------------------------------------------------------------------------------------------------------------
	//specific methods for driving
	public double getLStickXAxis() {
		return deadzone(controller.getX(Hand.kLeft));
	}
	
	public double getLStickYAxis() {
		return deadzone(controller.getY(Hand.kLeft));
	}
	
	public double getRStickXAxis() {
		return deadzone(controller.getX(Hand.kRight));
	}
	
	public double getRStickYAxis() {
		return deadzone(controller.getY(Hand.kRight));
	}
	
	public double getTriggerAxis() {
		if(controller.getTriggerAxis(Hand.kLeft) > 0)
			return -deadzone(controller.getTriggerAxis(Hand.kLeft));
		else if (controller.getTriggerAxis(Hand.kRight) > 0)
			return deadzone(controller.getTriggerAxis(Hand.kRight));
		else
			return 0.0;
	}
	
	public double getBumper() {
		if(controller.getBumper(Hand.kLeft))
			return -1;
		else if (controller.getBumper(Hand.kRight))
			return 1;
		else
			return 0.0;
	}
	
	//specific methods for manipulators
	public int getPOV() {
		return controller.getPOV();
	}

	/**
	 * Method for negating micro movements, which occur on the xbox controller
	 * without any driver input.
	 * @param input passed value from -1 to 1.
	 * @return 0 or value.
	 * Returns 0 if value is under the deadzone threshold.
	 */
	public double deadzone(double input) {
		if(Math.abs(input) >= Constants.controllerDeadzone)
			return input;
		else
			return 0.0;
	}

	/*

				ADD METHODS FOR MANIPULATORS HERE

	*/

	/**
	 * Constructor. Used for calling commands on button press.
	 * Bind commands here.
	 */
	public OI() {
		//gyro commands
		/*yBut.whenPressed(new RotateCommand(0.0f));
		bBut.whenPressed(new RotateCommand(90.0f));
		aBut.whenPressed(new RotateCommand(180.0f));
		xBut.whenPressed(new RotateCommand(-90.0f));*/

		leftBum.whenPressed(new RotateCommand(-1));
		rightBum.whenPressed(new RotateCommand(1));
		lTriggerButton.whenPressed(new RotateCommand(-2));
		rTriggerButton.whenPressed(new RotateCommand(2));
		//aBut.whenPressed(new LineupCommand());
		//yBut.whenPressed(new CloseHatchCommand());		
		//xBut.whenPressed(new OpenHatchCommand());
		//aBut.whenPressed(new UltrasonicWallCommand(30));
		//xBut.whenPressed(new IntakeCommand());
		//
		//yBut.whenPressed(new ShootCommand());
		//xBut.whenPressed(new StopIntakeCommand());
		//aBut.whenPressed(new LineupSelector());
		//bBut.whenPressed(new HatchSelector());
		//xBut.whenPressed(new StartPosCommand());
		//yBut.whenPressed(new IntakePosCommand());
		//yBut.whenPressed(new UltrasonicCommand(160));
		//xBut.whenPressed(new UltrasonicCommand(80));
		//bBut.whenPressed(new EncoderDriveCommand(100));
		aBut.whenPressed(new HatchRoutine());
		bBut.whenPressed(new LeftTwoHatchAuto());
		//xBut.whenPressed(new RotateCommand(0));
	}
}