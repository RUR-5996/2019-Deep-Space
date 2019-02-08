/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

//imports needed for camera
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;

//required dependencies
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//Constants and subsystems
import frc.robot.subsystems.*;
import frc.robot.Constants;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  public static DriveSubsystem drive = new DriveSubsystem();
  public static RotateSubsystem rotate = new RotateSubsystem();
  public static UltrasonicSubsystem ultrasonic = new UltrasonicSubsystem();
  public static DriveExecutor driveExecutor = new DriveExecutor();
  public static VisionSubsystem vision = new VisionSubsystem();
  public static HatchSubsystem hatch = new HatchSubsystem();
  public static ShooterSubsystem shooter = new ShooterSubsystem();
  public static OI m_oi;
  public enum DrivingType {
    NORMAL,
    FIELD_ORIENTED
  }

  public static DrivingType drivingType;

  private UsbCamera camera;
  public double[] centerX, centerY, size, height, width;
  public int contours;


  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {

    drivingType = DrivingType.FIELD_ORIENTED;
    //drivingType = DrivingType.NORMAL;

    m_oi = new OI();
    //m_chooser.setDefaultOption("Default Auto", new ExampleCommand());
    // chooser.addOption("My Auto", new MyAutoCommand());
    //SmartDashboard.putData("Auto mode", m_chooser);
    new Thread( () -> {
      camera = CameraServer.getInstance().startAutomaticCapture();
      camera.setResolution(Constants.imageWidth, Constants.imageHeight);
      camera.setFPS(Constants.imageFPS);
      camera.setExposureAuto();
    }).start();
    shooter.shooterInit();
    rotate.resetGyro();

    configureTalon(RobotMap.leftBack);
    configureTalon(RobotMap.leftFront);
    configureTalon(RobotMap.rightBack);
    configureTalon(RobotMap.rightFront);

    configureVictor(RobotMap.shooter1);
    configureVictor(RobotMap.shooter2);
    configureVictor(RobotMap.shooter3);
    configureVictor(RobotMap.shooter4);
  }

  private void configureTalon(WPI_TalonSRX talon) {
    talon.configFactoryDefault();
		talon.configNominalOutputForward(0, Constants.timeOutMs);
		talon.configNominalOutputReverse(0, Constants.timeOutMs);
		talon.configPeakOutputForward(1, Constants.timeOutMs);
		talon.configPeakOutputReverse(-1, Constants.timeOutMs);
		talon.configAllowableClosedloopError(0, 0, Constants.timeOutMs);
		talon.configNeutralDeadband(0.05, Constants.timeOutMs); 
		talon.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Brake);
		talon.setInverted(false);

		// Peak current and duration must be exceeded before corrent limit is activated.
		// When activated, current will be limited to continuous current.
		// Set peak current params to 0 if desired behavior is to immediately
		// current-limit.
		talon.enableCurrentLimit(true);
		talon.configContinuousCurrentLimit(40, Constants.timeOutMs); // Must be 5 amps or more
		talon.configPeakCurrentLimit(40, Constants.timeOutMs); // 100 A
		talon.configPeakCurrentDuration(200, Constants.timeOutMs); // 200 ms
  }

  private void configureVictor(WPI_VictorSPX victor) {
    victor.configFactoryDefault();
		victor.configNominalOutputForward(0, Constants.timeOutMs);
		victor.configNominalOutputReverse(0, Constants.timeOutMs);
		victor.configPeakOutputForward(1, Constants.timeOutMs);
		victor.configPeakOutputReverse(-1, Constants.timeOutMs);
		victor.configAllowableClosedloopError(0, 0, Constants.timeOutMs);
		victor.configNeutralDeadband(0.05, Constants.timeOutMs); 
		victor.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Brake);
		victor.setInverted(false);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
    driveExecutor.execute();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    driveExecutor.execute();
    //System.out.println(Robot.ultrasonic.isEnabled() + "Distance: " + ultrasonic.getDistanceCM());
   vision.visionLogic();
   //System.out.println("Is enabled: " + vision.isEnabled() + " Offset: " + vision.getOffset());
   //System.out.println(rotate.isEnabled() + " Position: " + rotate.getPosition() + " Setpoint: " + rotate.getSetpoint());
   //System.out.println(rotate.currentPosition);
   SmartDashboard.putNumber("Current angle", rotate.ahrs.getYaw());
   SmartDashboard.putNumber("Current position", rotate.getPosition());
   SmartDashboard.putNumber("Gyro setpoint", rotate.getSetpoint());
   SmartDashboard.putBoolean("Gyro enabled", rotate.isEnabled());
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
