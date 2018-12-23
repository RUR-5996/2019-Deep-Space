package frc.robot.subsystems;

//imports
import frc.robot.Robot;
import frc.robot.commands.Drive_Command;
import edu.wpi.first.wpilibj.command.Subsystem;

//declares class - Drive_Subsystem - extends Subsystem REQUIRED!!! Cannot run otherwise. Since this is a subsystem call methods using commands.
public class Drive_Subsystem extends Subsystem
{
	
	//sets default command for the subsystem - required for this subsystem.
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new Drive_Command()); //sets Drive_Command as default command
	}
	
	//method used during teleop drive - xAxis for x coordinate input, yAxis for y coordinate input, zAxis for turning, ahrs for gyro
	public void TeleopDrive(double xAxis, double yAxis) {
		//drive method, uses cartesian drive for mecanumDrive type robotDrive object - driveCartesian automatically processes the values fed
		Robot.driveExecutor.setX(xAxis);
		Robot.driveExecutor.setY(yAxis);
	}	
}