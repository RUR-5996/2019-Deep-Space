package frc.robot.enumeration;

/**
 * Enums for driving types.
 * Normal = driving is not influenced by angle.
 * Field oriented = driving is corrected by the angle of gyro
 * e.g. forward is always forward.
 */
public enum DrivingType {
    NORMAL,
    FIELD_ORIENTED
}