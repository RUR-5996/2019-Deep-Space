/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.CloseBoschCommand;
import frc.robot.commands.OpenBoschCommand;
import frc.robot.commands.RotateCommand;
import frc.robot.commands.UltrasonicWallCommand;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

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
															//Change these to re-bind
	//values for controller axis
	final static int lStickXAxis = 0;
	final static int lStickYAxis = 1;
	final static int lTriggerAxis = 2;
	final static int rTriggerAxis = 3;
	final static int rStickXAxis = 4;
	final static int rStickYAxis = 5;
	final static double deadzone = Constants.controllerDeadzone;
	
	//values for joystick buttons 
	final static int button1 = 1;
	final static int button2 = 2;
	final static int button3 = 3;
	final static int button4 = 4;
	final static int button5 = 5;
	final static int button6 = 6;
	final static int button7 = 7;
	final static int button8 = 8;
	final static int button9 = 9;
	final static int button10 = 10;
	final static int button11 = 11;
	final static int button12 = 12;
	//---------------------------------------------------------------------------------------------------
	//defines and creates button objects for each xbox button
	private final Button aBut = new JoystickButton(controller, aButton);
	private final Button bBut = new JoystickButton(controller, bButton);
	private final Button xBut = new JoystickButton(controller, xButton);
	private final Button yBut = new JoystickButton(controller, yButton);
	public final Button leftBum = new JoystickButton(controller, leftBumper);
	private final Button rightBum = new JoystickButton(controller, rightBumper);
	private final Button startBut = new JoystickButton(controller, startButton);
	private final Button backBut = new JoystickButton(controller, backButton);
	private final Button lStickBut = new JoystickButton(controller, lStickButton);
	private final Button rStickBut = new JoystickButton(controller, rStickButton);
	//------------------------------------------------------------------------------------------------------
	//methods for driving
	public double getDriveForward() {
		return getLStickXAxis();			
	}
	
	public double getDriveSideways() {
		return getLStickYAxis();				
	}
	
	public double getDriveRotation() {
		return getTriggerAxis();
	}
	
	//------------------------------------------------------------------------------------------------------------
	//specific methods for driving
	public double getLStickXAxis()
	{
		return deadzone(controller.getX(Hand.kLeft));
	}
	
	public double getLStickYAxis()
	{
		return deadzone(controller.getY(Hand.kLeft));
	}
	
	public double getRStickXAxis()
	{
		return deadzone(controller.getX(Hand.kRight));
	}
	
	public double getRStickYAxis() 
	{
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
	
	public double getBumper()
	{
		if(controller.getBumper(Hand.kLeft))
			return -1;
		else if (controller.getBumper(Hand.kRight))
			return 1;
		else
			return 0.0;
	}
	
	//-----------------------------------------------------------------------------------
	//specific methods for manipulators
	public int getPOV() {
		return controller.getPOV();
	}

	//--------------------------------------------------------------------------------------------------------
	//negates micro-movements smaller than int deadzone. 
	public double deadzone(double input)
	{
		if(Math.abs(input) >= deadzone)
			return input;
		else
			return 0.0;
	}
	//----------------------------------------------------------------------------------------------------------
	//constructor - put button commands here
		
	public OI() {
		//gyro commands
	//	aBut.whenPressed(new RotateCommand(0.0f));
		//bBut.whenPressed(new RotateCommand(90.0f));
		yBut.whenPressed(new CloseBoschCommand());		
		xBut.whenPressed(new OpenBoschCommand());
		aBut.whenPressed(new UltrasonicWallCommand(30));
	}
}