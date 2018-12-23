/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;

import java.nio.file.attribute.PosixFileAttributeView;

import frc.robot.Robot;
import frc.robot.commands.ControlledRotate_Command;
import frc.robot.commands.Drive_Command;
import frc.robot.commands.Turn_Command;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger.ButtonScheduler;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	public XboxController controller = new XboxController(0); //creates a new controller object for XboXController and assigns its port value to 0
	public Joystick joystick = new Joystick(1);
	public Joystick secondJoystick = new Joystick(2);
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
	final static double deadzone = 0.1;
	
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
	//public final double grabberSpeed = -1;
	public final double lifterSpeed = 0.2;
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
	
	
	
	//defines and creates button objects for each joystick button
	private final Button num1But = new JoystickButton(joystick, button1);
	private final Button num2But = new JoystickButton(joystick, button2);
	private final Button num3But = new JoystickButton(joystick, button3);
	private final Button num4But = new JoystickButton(joystick, button4);
	private final Button num5But = new JoystickButton(joystick, button5);
	private final Button num6But = new JoystickButton(joystick, button6);
	private final Button num7But = new JoystickButton(joystick, button7);
	private final Button num8But = new JoystickButton(joystick, button8);
	private final Button num9But = new JoystickButton(joystick, button9);
	private final Button num10But = new JoystickButton(joystick, button10);
	private final Button num11But = new JoystickButton(joystick, button11);
	private final Button num12But = new JoystickButton(joystick, button12);
	//------------------------------------------------------------------------------------------------------
	//methods for driving
	public double getDriveForward() {
		return getLStickXAxis();				//uncomment for controller drive
		//return getJoystickXAxis();				//uncomment for joystick drive
	}
	
	public double getDriveSideways() {
		return getLStickYAxis();				//uncomment for controller drive
		//return getJoystickYAxis();				//uncomment for joystick drive
	}
	
	public double getDriveRotation() {
		double offset = 0.3; // Biger than normal, because the axis is smaller (I mean its easier to rotoate it to same value)
		double input = joystick.getTwist();
		if(Math.abs(input) < offset)
			return 0.0;
		else
			return Math.signum(input) * (Math.abs(input) - offset);
		
//		return deadzone(joystick.getTwist());
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
	
	public double getJoystickXAxis() {
		return deadzone(joystick.getX());
	}
	
	public double getJoystickYAxis() {
		return deadzone(joystick.getY());
	}
	
	public double getTriggerAxis()
	{
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
	
	public double getJawsSpeed() {
		return getTriggerAxis();
	}
	
	public double getLifterSpeed() {
		return deadzone(getBumper());
	}

	public double getArmSpeed() {
		return getRStickYAxis();
	}
	
	public double getDartSpeed() {
		return getLStickYAxis();
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
	/*
	 * You can only bind via BUTTON_OBJECT.whenPressed/whenActive/whenReleased etc.
	 * Binding via if(X) then.... is not possible for some weird reasons.
	 */
	
	/**
	 * 					KEY BINDINGS
	 *  ------------------------------------------
	 * 						XBOX
	 * A => 
	 * B => 
	 * X => 
	 * Y => 
	 * LEFT BUMPER => 
	 * RIGHT BUMPER => 
	 * LEFT TRIGGER => 
	 * RIGHT TRIGGER => 
	 * LEFT STICK => 
	 * RIGHT STICK =>  
	 * POV => UP = ;  DOWN = ; RIGHT = ; LEFT = ;
	 * -------------------------------------------
	 * 					JOYSTICK
	 * STICK => Robot Drive
	 * POV => UP = ;  DOWN = ; RIGHT = ; LEFT = ;
	 * SLIDER => 
	 * BUTTONS:
	 * 1 => 
	 * 2 => 
	 * 3 => 
	 * 4 => 
	 * 5 => 
	 * 6 => 
	 * 7 => 
	 * 8 => Lift command (sets the position of the grabber to "upper" position)
	 * 9 => 
	 * 10 => Lift command (sets the position of the grabber to "bottom" position)
	 * 11 => Stop all command (stops darts, stops arm and stops grabber wheels)
	 * 12 => Lift command (currently set to stop motors)
	 * 
	 */
	
		
	public OI() {
		//new StartServo_Command();
		
		//				XBOX COMMANDS
		//num3But.whileHeld(new MoveDart_Command(-0.5));
		//num5But.whileHeld(new MoveDart_Command(0.5));
		
		//num4But.whileHeld(new MoveArm_Command(-0.25));
		//num6But.whileHeld(new MoveArm_Command(0.25));
		
		//aBut.toggleWhenActive(new Grab_Command(-1));
		//bBut.toggleWhenActive(new Grab_Command(1));
		//rightBum.whenPressed(new Lift_Command(2));
		//leftBum.whenPressed(new Lift_Command(1));
		//backBut.whenPressed(new StopAll_Command());

//		num8But.whenPressed(new Lift_Command(1));
//		num10But.whenPressed(new Lift_Command(2));
//		num12But.whenPressed(new Lift_Command(0));

//		num5But.whenPressed(new Grab_Command(1));
		//num2But.toggleWhenPressed(new Grab_Command(1));
		//num1But.toggleWhenPressed(new Grab_Command(-1));
		
		
		// 			JOYSTICK COMMANDS
		//gyro commands
		num7But.whenPressed(new Turn_Command(0.0f));
		num8But.whenPressed(new Turn_Command(90.0f));
		num10But.whenPressed(new Turn_Command(180.0f));		
		num9But.whenPressed(new Turn_Command(-90.0f));

		//startBut.whenPressed(new StartServo_Command());
		
		//invoking methods and commands from Smart Dashboard
		//SmartDashboard.putData("Start Grabber", new Grab_Command(-1));
		
	}
	

	Command rotateCommand = new ControlledRotate_Command();
	
	public void periodic() {
		if(getDriveRotation() != 0) {
			rotateCommand.start();
		}		
	}
	
}
