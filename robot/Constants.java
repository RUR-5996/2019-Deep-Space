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

    public static final double triggerThreshold = 0.9;
    //Camera settings
    public static final int imageWidth = 320;
    public static final int imageHeight = 240;
    public static final int imageFPS = 30;
    
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

    public static final double shootingTime = 0.5;
    public static final double intakeSpeed = 0.2;

    //Talon and Victor PWM ports
    public static final int boschMotor = 4;

    //Driver controller deadzones
    public static final double controllerDeadzone = 0.1;
    public static final double joystickDeadzone = 0.3;

    //
    public static final int timeOutMs = 10;

    //Bosch motor speed
    public static final double boschMotorSpeed = 0.4;

    //Ultrasonic PID constants
    public static final double ultrasonicKp = 0.03;
    public static final double ultrasonicKi = 0.00;
    public static final double ultrasonicKd = 0.00;
    public static final int ultrasonicTolerance = 2;
    public static final double ultrasonicScalingFactor = 0.5;

    //Gyro PID constants
    public static final double gyroKp = 0.1;//0.1;
    public static final double gyroKi = 0;
    public static final double gyroKd = 0.082;//0.082;
    public static final int gyroTolerance = 2;
    public static final double gyroScalingFactor = 0.4;

    //Vision PID constants
    public static final double visionKp = 0.03;
    public static final double visionKi = 0.00;
    public static final double visionKd = 0.00;
    public static final int visionTolerance = 2;
    public static final double visionScalingFactor = 0.5;
}
