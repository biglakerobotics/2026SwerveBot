package frc.robot;

import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.apriltag.AprilTagFields;
import edu.wpi.first.math.Matrix;
import edu.wpi.first.math.VecBuilder;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.numbers.N1;
import edu.wpi.first.math.numbers.N3;
import edu.wpi.first.math.util.Units;

public class Constants {
    public static final double MAX_FORWARD_SPEED = 6;
    public static final double MAX_ROTATION_SPEED = 3;
    public static final int INTAKESPEED = 0;
    public static final double climberSpeed = -.4;
    public static final double turretSpeed = 0;

    public static final int climberLeadID = 4;
    public static final int climberFollowID = 5;
    public static final int intakeMotorID = 6;
    public static final int intakeJointMotorID = 7;  
    
    public static final double CLIMBERVOLTS_P_VALUE = .8;  
    public static final double CLIMBERVOLTS_I_VALUE = 0;
    public static final double CLIMBERVOLTS_D_VALUE = 0;

    public static final double CLIMBERTORQUE_P_VALUE = .8;  
    public static final double CLIMBERTORQUE_I_VALUE = 0;
    public static final double CLIMBERTORQUE_D_VALUE = 0;

    public static final double TURRETVOLTS_P_VALUE = 0;
    public static final double TURRETVOLTS_I_VALUE = 0;
    public static final double TURRETVOLTS_D_VALUE = 0;

    public static final double TURRET_TORQUE_P_VALUE = 0;
    public static final double TURRET_TORQUE_I_VALUE = 0;
    public static final double TURRET_TORQUE_D_VALUE = 0;

    public static final double TURRETANGLE_P_VALUE = 0;
    public static final double TURRETANGLE_I_VALUE = 0;
    public static final double TURRETANGLE_D_VALUE = 0;

    public static final double TURRET_GOAL_ANGLE = 0;
    public static final double TURRET_ANGLE_TOLERANCE = 0.2;
    public static double kTurretRotationsPerTick = 14.0 / 50.0 * 14.0 / 322.0;

    public static final double peakVoltage = 8;
    public static final double peaKAmps = 70;
    public static final double startPosition = 0;

    public static final double softForwardLimitClimber = 1000;
    public static final double softReverseLimitClimber = 1000;
    public static final double INTAKEJOINTSPEED = 0;
    public static final String Spindexer = null;

    public static class VisionConstants {
        public static final String LIMELIGHT_NAME1 = "limelight";
        public static final String LIMELIGHT_NAME2 = "limelight";
        public static final String LIMELIGHT_NAME3 = "limelight";
        public static final String LIMELIGHT_NAME4 = "limelight";

        public static final AprilTagFieldLayout APRIL_TAG_FIELD_LAYOUT = AprilTagFieldLayout
        .loadField(AprilTagFields.k2026RebuiltWelded);

        public static final Matrix<N3, N1> SINGLE_TAG_STD_DEVS = VecBuilder.fill(0.1, 0.1, 0.1);
        public static final Matrix<N3, N1> MULTI_TAG_STD_DEVS = VecBuilder.fill(0.1, 0.1, 0.1);
   
        

        public static final Transform3d Camera1 = new Transform3d(
            new Translation3d(Units.inchesToMeters(0), Units.inchesToMeters(0), 
            Units.inchesToMeters(0)), 
            new Rotation3d(0, Units.degreesToRadians(0), Units.degreesToRadians(0))
        );
        public static final Transform3d Camera2 = new Transform3d(
            new Translation3d(Units.inchesToMeters(0), Units.inchesToMeters(0), 
            Units.inchesToMeters(0)), 
            new Rotation3d(0, Units.degreesToRadians(0), Units.degreesToRadians(0))
        );
        public static final String[] CAMERA_NAMES = {LIMELIGHT_NAME1, LIMELIGHT_NAME2, LIMELIGHT_NAME3, LIMELIGHT_NAME4};
        
        public static final Transform3d[] ROBOT_TO_CAMERA_TRANSFORMS = new Transform3d[] {
            Camera1, Camera2
        };


    }

    



    


}

