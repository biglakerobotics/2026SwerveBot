package frc.robot.subsystems;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.controls.PositionTorqueCurrentFOC;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants.TURRET_MOTOR_CONFIGS;

import static edu.wpi.first.units.Units.Amps;
import static edu.wpi.first.units.Units.Volts;

import java.io.ObjectInputFilter.Status;

public class Turret implements Subsystem {

    // turret motor configs
    private void applyTurretConfigs(TalonFX talonFXMotor) {
        // in init function
        var turretConfigs = new TalonFXConfiguration();
        turretConfigs.Voltage.withPeakForwardVoltage(Volts.of(TURRET_MOTOR_CONFIGS.PEEK_FORWARD_VOLTAGE))
                              .withPeakReverseVoltage(Volts.of(-TURRET_MOTOR_CONFIGS.PEEK_REVERSE_VOLTAGE));
        turretConfigs.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
        turretConfigs.CurrentLimits.withStatorCurrentLimitEnable(true).withStatorCurrentLimit(TURRET_MOTOR_CONFIGS.PEAK_AMPS);
        turretConfigs.SoftwareLimitSwitch.withForwardSoftLimitEnable(true).withForwardSoftLimitThreshold(TURRET_MOTOR_CONFIGS.SOFT_FORWARD_LIMIT);
        turretConfigs.SoftwareLimitSwitch.withReverseSoftLimitEnable(true).withReverseSoftLimitThreshold(TURRET_MOTOR_CONFIGS.SOFT_REVERSE_LIMIT);
        turretConfigs.TorqueCurrent.withPeakForwardTorqueCurrent(Amps.of(TURRET_MOTOR_CONFIGS.PEAK_AMPS))
                                    .withPeakReverseTorqueCurrent(Amps.of(TURRET_MOTOR_CONFIGS.PEAK_AMPS));

        turretConfigs.Feedback.SensorToMechanismRatio = TURRET_MOTOR_CONFIGS.SENSOR_TO_MECHANISM_RATIO; // 1 rotation of the mechanism results in 2048 units of sensor movement

        // set slot 0 gains
        var turretSlot0Configs = turretConfigs.Slot0;
        turretSlot0Configs.kS = TURRET_MOTOR_CONFIGS.SLOT_0_kS; // Add 0.25 V output to overcome static friction
        turretSlot0Configs.kV = TURRET_MOTOR_CONFIGS.SLOT_0_kV; // A velocity target of 1 rps results in 0.12 V output
        turretSlot0Configs.kA = TURRET_MOTOR_CONFIGS.SLOT_0_kA; // An acceleration of 1 rps/s requires 0.01 V output
        turretSlot0Configs.kP = TURRET_MOTOR_CONFIGS.SLOT_0_kP; // A position error of 2.5 rotations results in 12 V output
        turretSlot0Configs.kI = TURRET_MOTOR_CONFIGS.SLOT_0_kI; // no output for integrated error
        turretSlot0Configs.kD = TURRET_MOTOR_CONFIGS.SLOT_0_kD; // A velocity error of 1 rps results in 0.1 V output
        
        // set Motion Magic settings
        var motionMagicConfigs = turretConfigs.MotionMagic;
        motionMagicConfigs.MotionMagicCruiseVelocity = TURRET_MOTOR_CONFIGS.MOTION_MAGIC_CRUISE_VELOCITY; // Target cruise velocity
        motionMagicConfigs.MotionMagicAcceleration = TURRET_MOTOR_CONFIGS.MOTION_MAGIC_ACCELERATION; // Target acceleration
        motionMagicConfigs.MotionMagicJerk = TURRET_MOTOR_CONFIGS.MOTION_MAGIC_JERK; // Target jerk

        talonFXMotor.getConfigurator().apply(turretConfigs);
        talonFXMotor.setPosition(0);
        talonFXMotor.setNeutralMode(NeutralModeValue.Brake);
    }

    // new talonFX motor - Turret
    private TalonFX turretMotor = new TalonFX(TURRET_MOTOR_CONFIGS.MOTOR_ID);

    public final PositionVoltage m_positionVoltage = new PositionVoltage(0).withSlot(0);
    public final MotionMagicVoltage m_MotionMagicVoltage = new MotionMagicVoltage(0).withEnableFOC(true);
    public final PositionTorqueCurrentFOC m_positionTorque = new PositionTorqueCurrentFOC(0).withSlot(1);
    public final StatusSignal<Angle> turretPosition = turretMotor.getPosition();


    
     // constructor
    public Turret() {
        applyTurretConfigs(turretMotor);
    }

    // method to set turret motor speed
    public void turretSpinToZeroPos() {
        turretMotor.setControl(m_MotionMagicVoltage.withPosition(TURRET_MOTOR_CONFIGS.TURRET_ZERO_POS));
    }

    public void turretSpinToMaxPos() {
        turretMotor.setControl(m_MotionMagicVoltage.withPosition(TURRET_MOTOR_CONFIGS.TURRET_MAX_POS));
    }

    public void turretSpinToPosition(double position) {
        double targetPosition = MathUtil.inputModulus(position + TURRET_MOTOR_CONFIGS.TURRET_ZERO_POS_ROTATIONS_OFFSET, TURRET_MOTOR_CONFIGS.SOFT_REVERSE_LIMIT, TURRET_MOTOR_CONFIGS.SOFT_FORWARD_LIMIT);
        turretMotor.setControl(m_MotionMagicVoltage.withPosition(targetPosition));
    }

    public boolean isPosistinReachable(double position) {
        double targetPosition = MathUtil.inputModulus(position + TURRET_MOTOR_CONFIGS.TURRET_ZERO_POS_ROTATIONS_OFFSET, TURRET_MOTOR_CONFIGS.SOFT_REVERSE_LIMIT, TURRET_MOTOR_CONFIGS.SOFT_FORWARD_LIMIT);
        return targetPosition >= TURRET_MOTOR_CONFIGS.SOFT_REVERSE_LIMIT && targetPosition <= TURRET_MOTOR_CONFIGS.SOFT_FORWARD_LIMIT;
    }

    public boolean isAtPosition() {
        return turretPosition.refresh().isNear(m_MotionMagicVoltage.Position, TURRET_MOTOR_CONFIGS.TURRET_POS_TOLERANCE_IN_ROTATIONS);
    }

    public static Translation2d GetTurretTranslation(Pose2d robotPose) {
        return robotPose.getTranslation().plus(new Translation2d(TURRET_MOTOR_CONFIGS.TURRET_X_OFFSET_METERS, TURRET_MOTOR_CONFIGS.TURRET_Y_OFFSET_METERS).rotateBy(robotPose.getRotation()));
    }

    public void stopTurret() {
        turretMotor.stopMotor();
    }
}
