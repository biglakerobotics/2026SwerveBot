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
    
    public static final double PIVOT_SPEED = .5;
    public static final double INTAKE_DEPLOY_POSITION = 10;
    public static final double INTAKE_RETRACT_POSITION = 0;

    public static class PIVOT_MOTOR_CONFIGS {
        public static final int MOTOR_ID = 1;
        public static final double SLOT_0_kS = 0.25;
        public static final double SLOT_0_kV = 0.12;
        public static final double SLOT_0_kA = 0.03;
        public static final double SLOT_0_kP = 4.8;
        public static final double SLOT_0_kI = 0;
        public static final double SLOT_0_kD = 0.1;
        public static final int MOTION_MAGIC_CRUISE_VELOCITY = 80;
        public static final int MOTION_MAGIC_ACCELERATION = 160;
        public static final int MOTION_MAGIC_JERK = 1600;
        public static final double PEEK_FORWARD_VOLTAGE = 8;
        public static final int PEEK_REVERSE_VOLTAGE = 8;
        public static final double PEAK_AMPS = 70;
        public static final double SOFT_FORWARD_LIMIT = 10;
        public static final double SOFT_REVERSE_LIMIT = 0;
    }

    public static class INTAKE_MOTOR_CONFIGS {
        public static final int MOTOR_ID = 2;
        public static final double PEEK_FORWARD_VOLTAGE = 12;
        public static final double PEEK_REVERSE_VOLTAGE = 12;
        public static final double PEAK_AMPS = 40;
        public static final double INTAKE_MOTOR_SPEED = .5;
    }
    
    public static class SHOOTER_MOTOR_CONFIGS {
        public static final int LEFT_MOTOR_ID = 3;
        public static final int RIGHT_MOTOR_ID = 4;
        public static final double PEEK_FORWARD_VOLTAGE = 12.0;
        public static final double PEEK_REVERSE_VOLTAGE = 12.0;
        public static final int PEAK_AMPS = 40;
        public static final double SHOOTER_MOTOR_PASS_SPEED = 0.6;
        public static final double SHOOTER_MOTOR_MAX_SPEED = 1.0;
        public static final double SLOT_0_kS = 0.25;
        public static final double SLOT_0_kV = 0.12;
        public static final double SLOT_0_kA = 0.03;
        public static final double SLOT_0_kP = 4.8;
        public static final double SLOT_0_kI = 0;
        public static final double SLOT_0_kD = 0.1;
    }

    public static class SPINDEXER_MOTOR_CONFIGS {
        public static final int SPINDEXER_MOTOR_ID = 6;
        public static final double PEEK_FORWARD_VOLTAGE = 12.0;
        public static final double PEEK_REVERSE_VOLTAGE = 12.0;
        public static final int PEAK_AMPS = 20;
        public static final double SPINDEXER_MOTOR_SPEED = 0.3;
    }

    public static class KICKER_MOTOR_CONFIGS {
        public static final int KICKER_MOTOR_ID = 5;
        public static final double PEEK_FORWARD_VOLTAGE = 12.0;
        public static final double PEEK_REVERSE_VOLTAGE = 12.0;
        public static final int PEAK_AMPS = 20;
        public static final double KICKER_MOTOR_SPEED = 0.5;
    }



    


}

