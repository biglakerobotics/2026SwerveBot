package frc.robot;

import static edu.wpi.first.units.Units.Inches;

import com.pathplanner.lib.util.FlippingUtil;

import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.apriltag.AprilTagFields;
import edu.wpi.first.math.Matrix;
import edu.wpi.first.math.VecBuilder;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.interpolation.InterpolatingDoubleTreeMap;
import edu.wpi.first.math.numbers.N1;
import edu.wpi.first.math.numbers.N3;
import edu.wpi.first.math.util.Units;

public class Constants {
    public static final double MAX_FORWARD_SPEED = 6;
    public static final double MAX_ROTATION_SPEED = 3;
    public static final int INTAKESPEED = 0;

    // public static final int intakeMotorID = 6;
    // public static final int intakeJointMotorID = 10;  

    public static final double peakVoltage = 8;
    public static final double peaKAmps = 70;
    public static final double startPosition = 0;

    public static final double softForwardLimitClimber = 1000;
    public static final double softReverseLimitClimber = 1000;
    public static final double INTAKEJOINTSPEED = 0;
    public static final String Spindexer = null;

    public static class VisionConstants {
        public static final String PHOTON_CAMERA_LEFT = "Camera_Left";
        public static final String PHOTON_CAMERA_RIGHT = "Camera_Right";
  

        public static final AprilTagFieldLayout APRIL_TAG_FIELD_LAYOUT = AprilTagFieldLayout
        .loadField(AprilTagFields.k2026RebuiltWelded);

        public static final Matrix<N3, N1> SINGLE_TAG_STD_DEVS = VecBuilder.fill(0.1, 0.1, 0.1);
        public static final Matrix<N3, N1> MULTI_TAG_STD_DEVS = VecBuilder.fill(0.1, 0.1, 0.1);
   
        

        public static final Transform3d CameraLeft = new Transform3d(
            new Translation3d(Units.inchesToMeters(-11.75), Units.inchesToMeters(3.5), 
            Units.inchesToMeters(6.25)), 
            new Rotation3d(0, Units.degreesToRadians(0), Units.degreesToRadians(0))
        );
        public static final Transform3d CameraRight = new Transform3d(
            new Translation3d(Units.inchesToMeters(-11.75), Units.inchesToMeters(7.25), 
            Units.inchesToMeters(6.25)), 
            new Rotation3d(0, Units.degreesToRadians(0), Units.degreesToRadians(0))
        );
        public static final String[] CAMERA_NAMES = {PHOTON_CAMERA_LEFT, PHOTON_CAMERA_RIGHT};
        
        public static final Transform3d[] ROBOT_TO_CAMERA_TRANSFORMS = new Transform3d[] {
            CameraLeft, CameraRight
        };


    }
    
    public static final double PIVOT_SPEED = .5;
    public static final double INTAKE_DEPLOY_POSITION = 0;
    public static final double INTAKE_RETRACT_POSITION = 10;

    public static class PIVOT_MOTOR_CONFIGS {
        public static final int MOTOR_ID = 1;
        public static final double SLOT_0_kS = 0.25;
        public static final double SLOT_0_kV = 0.12;
        public static final double SLOT_0_kA = 0.03;
        public static final double SLOT_0_kP = 4.8;
        public static final double SLOT_0_kI = 0;
        public static final double SLOT_0_kD = 0.1;
        public static final double SLOT_0_kG = .1;
        public static final int MOTION_MAGIC_CRUISE_VELOCITY = 80;
        public static final int MOTION_MAGIC_ACCELERATION = 160;
        public static final int MOTION_MAGIC_JERK = 1600;
        public static final double PEEK_FORWARD_VOLTAGE = 8;
        public static final int PEEK_REVERSE_VOLTAGE = 8;
        public static final double PEAK_AMPS = 70;
        public static final double SOFT_FORWARD_LIMIT = 0; // Ground, Intake deployed
        public static final double SOFT_REVERSE_LIMIT = 10; // Up position, Intake retracted
        public static final double SENSOR_TO_MECHANISM_RATIO = 25;
        public static final double INTAKE_POS_TOLERANCE_IN_ROTATIONS = .01;
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
        public static final double SHOOTER_RPS_TOLERANCE = 1.5;
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
        public static final double KICKER_MOTOR_SPEED = 20; // in Rotations Per Second
        public static final double KICKER_RPS_TOLERANCE = 4;
    }

    public static class CLIMBER_MOTOR_CONFIGS {
        public static final double CLIMBERVOLTS_P_VALUE = .8;  
        public static final double CLIMBERVOLTS_I_VALUE = 0;
        public static final double CLIMBERVOLTS_D_VALUE = 0;

        public static final double CLIMBERTORQUE_P_VALUE = .8;  
        public static final double CLIMBERTORQUE_I_VALUE = 0;
        public static final double CLIMBERTORQUE_D_VALUE = 0;

    
        public static final int climberLeadID = 8;
        public static final int climberFollowID = 9;

        public static final double climberSpeed = -.4;
    }

    public static class TURRET_MOTOR_CONFIGS{
        public static final int MOTOR_ID = 7;
        public static final double SLOT_0_kS = 0.25;
        public static final double SLOT_0_kV = 0.12;
        public static final double SLOT_0_kA = 0.03;
        public static final double SLOT_0_kP = 4.8;
        public static final double SLOT_0_kI = 0;
        public static final double SLOT_0_kD = 0.1;
        public static final int MOTION_MAGIC_CRUISE_VELOCITY = 80;
        public static final int MOTION_MAGIC_ACCELERATION = 160;
        public static final int MOTION_MAGIC_JERK = 1600;
        public static final double PEEK_FORWARD_VOLTAGE = 12;
        public static final double PEEK_REVERSE_VOLTAGE = 12;
        public static final double PEAK_AMPS = 70;
        public static final double SOFT_FORWARD_LIMIT = 10;
        public static final double SOFT_REVERSE_LIMIT = 0;
        public static final double kTurretRotationsPerTick = 14.0 / 50.0 * 14.0 / 322.0;
        public static final double TURRET_SPEED = 0;
        // Turret Pos values
        public static final double TURRET_ZERO_POS = 0;
        public static final double TURRET_MAX_POS = 200;
        public static final double TURRET_ZERO_POS_ROTATIONS_OFFSET = 10;
        public static final double TURRET_X_OFFSET_METERS = -0.1016; // 13 - 9 INCHES CONVERTED TO METERS
        public static final double TURRET_Y_OFFSET_METERS = 0; // 0 INCHES CONVERTED TO METERS
        public static final double TURRET_Z_OFFSET_METERS = 0.37465; // 14.75 INCHES CONVERTED TO METERS
        public static final Transform3d ROBOT_TO_TURRET = new Transform3d(
            new Translation3d(TURRET_X_OFFSET_METERS, TURRET_Y_OFFSET_METERS, TURRET_Z_OFFSET_METERS), 
            new Rotation3d(0, 0, 0)
        );
        public static final double SENSOR_TO_MECHANISM_RATIO = 10;
        public static final double TURRET_POS_TOLERANCE_IN_ROTATIONS = 0.0082; 

    }

    public static class SHOOTING_CONSTANTS {
        private static InterpolatingDoubleTreeMap createShooterLookup() {
            var map = new InterpolatingDoubleTreeMap();
            map.put(Units.inchesToMeters(40), 20.0); 
            map.put(Units.inchesToMeters(127), 50.0);
            return map;
        }
        public static final InterpolatingDoubleTreeMap SHOOT_LOOKUP_TABLE = createShooterLookup();
        
        private static InterpolatingDoubleTreeMap createShuttleLookup() {
            var map = new InterpolatingDoubleTreeMap();
            map.put(Units.inchesToMeters(40), 20.0); 
            map.put(Units.inchesToMeters(127), 50.0);
            return map;
        }
        public static final InterpolatingDoubleTreeMap SHUTTLE_LOOKUP_TABLE = createShuttleLookup();

        public static final Translation2d HUB_BLUE = new Translation2d(Inches.of(182.143595), Inches.of(158.84375));
        public static final Translation2d HUB_RED = new Translation2d(Inches.of(469.078905), Inches.of(158.84375));

        
        public static final Translation2d SHUTTLE_BLUE_LOW = new Translation2d(Inches.of(150.00), Inches.of(75.0));
        public static final Translation2d SHUTTLE_BLUE_HIGH = new Translation2d(Inches.of(150.00), Inches.of(225.0));
        public static final Translation2d SHUTTLE_RED_LOW = FlippingUtil.flipFieldPosition(SHUTTLE_BLUE_LOW);
        public static final Translation2d SHUTTLE_RED_HIGH = FlippingUtil.flipFieldPosition(SHUTTLE_BLUE_HIGH);
        
    }
    
}

