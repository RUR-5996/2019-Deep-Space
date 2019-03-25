package frc.robot.enumeration;

/**
 * Enum for shooter positions.
 * Starting position = tucked in as much as possible.
 * Cargo = shooting over the front of the robot for cargo.
 * Middle Rocket = shooting over the front of the robot for middle rocket.
 * Low Rocket = shooting from the back of the robot for low rocket, 
 * slower speed and longer shooting time.
 * Intake = shooter as much out as possible for intake.
 */
public enum ShooterPosition {
    STARTING_POSITION,
    CARGO,
    MIDDLE_ROCKET,
    LOW_ROCKET,
    INTAKE
}