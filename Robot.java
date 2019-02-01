package frc.robot;

import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;

//imports needed for camera
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DriverStation;
//required dependencies
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.vision.VisionThread;
import edu.wpi.first.vision.VisionRunner;
//Commands and subsystems
import frc.robot.subsystems.*;
import frc.robot.sensors.Linetracker;
import frc.robot.sensors.UltrasonicSensor;
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
  public static UltrasonicSensor ultrasonic = new UltrasonicSensor();
  public static DriveExecutor driveExecutor = new DriveExecutor();
  private static MyVisionPipeline pipeline = new MyVisionPipeline();
  public static BoschSeatMotorSubsystem bosch = new BoschSeatMotorSubsystem();
  private final Object imgLock = new Object();
  public static OI m_oi;
  public static Linetracker line = new Linetracker();


  private VisionThread visionThread;
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
    //rotate.gyroInit();
    //ultrasonic.controllerInit();
    /*
    
    configureTalon(RobotMap.leftBack);
    configureTalon(RobotMap.leftFront);
    configureTalon(RobotMap.rightBack);
    configureTalon(RobotMap.rightFront);
    visionInit();*/
    //bosch.encoder.reset();
  }
/*
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
		talon.configContinuousCurrentLimit(30, Constants.timeOutMs); // Must be 5 amps or more
		talon.configPeakCurrentLimit(30, Constants.timeOutMs); // 100 A
		talon.configPeakCurrentDuration(200, Constants.timeOutMs); // 200 ms
  }
  public void visionInit() {
    visionThread = new VisionThread(camera, new MyVisionPipeline(), pipeline ->  {
      if(false)
      {
        synchronized (imgLock) {
          contours = pipeline.filterContoursOutput().size();
          centerX = new double[contours];
          centerY = new double[contours];
          size = new double[contours];
          height = new double[contours];
          width = new double[contours];
          if(!pipeline.filterContoursOutput().isEmpty()) {
            for(int i = 0; i < contours; i++){
              Rect r = Imgproc.boundingRect(pipeline.filterContoursOutput().get(i));
              centerX[i] = r.x+(r.width/2);
              centerY[i] = r.y+(r.height/2);
              size[i] = r.area();
              height[i] = r.height;
              width[i] = r.width;
            }
          }
        }
      }
    });
    visionThread.start();
  } 
  
  public void visionLogic() {
    synchronized (imgLock) {
      if(contours == 2) {
        System.out.println("READY!!!");
        double[] ratio = new double[contours];
        for(int i = 0; i < contours; i++) {
          ratio[i] = width[i] / height[i];
          System.out.println("Width: " + width[i] + " Height: " + height[i] + " Ratio: " + ratio[i] + " Size: " + size[i]);
        }
  
        double ratioLowerBound = ratio[0] * 0.9;
        double ratioUpperBound = ratio[0] * 1.1;
        try{
          if(ratioLowerBound < ratio[1] && ratio[1] < ratioUpperBound) {
            System.out.println("Detected successfully!");
          }
        } catch(Exception e) {
          System.out.println("Something went wrong with Image recognition. Exception log:" + e.toString());
        }
        
      } else {
        System.out.println(contours + " Contours detected!");
      } 
    }
  }*/
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
    m_oi.periodic();
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
    m_oi.periodic();
    //visionLogic();
    System.out.println("Distance: " + ultrasonic.getDistanceCM());
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}