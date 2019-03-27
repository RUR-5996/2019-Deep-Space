/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * Add your docs here.
 */
public class Constants {

    /*
    private static final double wheelDiameter = 14.7;
    public static final double wheelCircumference = wheelDiameter * Math.PI;
    public static final double pulsesPerRevolution = 1437; // ONLY BACK ENCODERS
    public static final double pulsesPerCm = pulsesPerRevolution / wheelCircumference;
    */

    //TalonSRX CAN ports
    public static final int frontLeftMotor = 2;
    public static final int backLeftMotor = 3;
    public static final int frontRightMotor = 1;
    public static final int backRightMotor = 0;

    //VictorSPX CAN ports
    public static final int shooter1 = 6;
    public static final int shooter2 = 7;
    public static final int shooter3 = 8;
    public static final int shooter4 = 9;
    public static final int shooterBoschMotor = 4;
    public static final int winchMotor = 5;
    public static final int rampWinchMotor = 10;

    //Talon and Victor PWM ports
    public static final int boschMotor = 4;
    public static final int hatchServo = 3;

    //Limit switches
    public static final int openHatchSwitch = 6;
    public static final int closeHatchSwitch = 5;
    public static final int hatchSwitch = 7;
    public static final int shooterSwitch = 2;
    public static final int shooterRotateSwitch = 9;
    public static final int rampLimitSwitch = 10;

    //Encoders
    public static final int shooterEncoderA = 3;
    public static final int shooterEncoderB = 4;
    public static final int rampEncoderA = 10;
    public static final int rampEncoderB = 11;

    //Encoders
    public static final int shooterEncoderA = 3;
    public static final int shooterEncoderB = 4;
    public static final int rampEncoderA = 10;
    public static final int rampEncoderB = 11;

    //shooter
    public static final double shootingSpeed = 0.95;
    public static final double intakeSpeed = 0.3;
    public static final double shootingTime = 0.25;

    //Camera settings
    public static final int imageWidth = 320;
    public static final int imageHeight = 240;
    public static final int imageFPS = 30;

    //Driver controller deadzones
    public static final double controllerDeadzone = 0.1;
    public static final double joystickDeadzone = 0.3;
    public static final double triggerThreshold = 0.9;

    //
    public static final int timeOutMs = 10;

    //Bosch motor speed
    public static final double boschMotorSpeed = 1;

    //Ultrasonic PID constants
    public static final double ultrasonicKp = 0.03;
    public static final double ultrasonicKi = 0;
    public static final double ultrasonicKd = 0.0;
    public static final int ultrasonicTolerance = 3;
    public static final double ultrasonicRange = 700;
    public static final double ultrasonicSpeed = 0.3;

    public static final int ultrasonicPort1 = 0;
    public static final int ultrasonicPort2 = 1;

    public static final double hatchToWallDistance = 15;

    //Gyro PID constants
    public static final double gyroKp = 0.0235;//0;//0.0301;//0.1;
    public static final double gyroKi = 0.00;
    public static final double gyroKd = 0.046;//0.13;//0.05;//0.15
    public static final double gyroTolerance = 3;

    //Vision PID constants
    public static final double visionKp = 0.00374;//0.00454;//0.00462;
    public static final double visionKi = 0;
    public static final double visionKd = 0.007;//0.011;//0.035;
    public static final double visionTolerance = 5;

    //Move back
    public static final double moveBackTime = 0.7;
    public static final double moveBackSpeed = 0.3;

    public static final int cargoPos = 40000; //45 - 65cm
    public static final int middlePos = 40000; //24cm
    public static final int lowPos = 100000; //bumper distance
    public static final int intakePos = 138000;

    public static final double lowRocketSpeed = 0.25;
    public static final double lowRocketTime = 1.2;

    public static final int shooterRotateCounter = 8;
}
