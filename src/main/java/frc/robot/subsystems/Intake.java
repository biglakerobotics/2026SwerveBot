package frc.robot.subsystems;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.controls.PositionTorqueCurrentFOC;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.GravityTypeValue;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants.INTAKE_MOTOR_CONFIGS;
import frc.robot.Constants.PIVOT_MOTOR_CONFIGS;
import frc.robot.Constants;

import static edu.wpi.first.units.Units.Amps;
import static edu.wpi.first.units.Units.Volts;

public class Intake implements Subsystem {

    // pivot motor configs
    private void applyPivotConfigs(TalonFX talonFXMotor) {
        // in init function
        var pivotConfigs = new TalonFXConfiguration();
        pivotConfigs.Voltage.withPeakForwardVoltage(Volts.of(PIVOT_MOTOR_CONFIGS.PEEK_FORWARD_VOLTAGE))
                              .withPeakReverseVoltage(Volts.of(-PIVOT_MOTOR_CONFIGS.PEEK_REVERSE_VOLTAGE));
        pivotConfigs.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
        pivotConfigs.CurrentLimits.withStatorCurrentLimitEnable(true).withStatorCurrentLimit(PIVOT_MOTOR_CONFIGS.PEAK_AMPS);
        pivotConfigs.SoftwareLimitSwitch.withForwardSoftLimitEnable(true).withForwardSoftLimitThreshold(PIVOT_MOTOR_CONFIGS.SOFT_FORWARD_LIMIT);
        pivotConfigs.SoftwareLimitSwitch.withReverseSoftLimitEnable(true).withReverseSoftLimitThreshold(PIVOT_MOTOR_CONFIGS.SOFT_REVERSE_LIMIT);
        pivotConfigs.TorqueCurrent.withPeakForwardTorqueCurrent(Amps.of(PIVOT_MOTOR_CONFIGS.PEAK_AMPS))
                                    .withPeakReverseTorqueCurrent(Amps.of(PIVOT_MOTOR_CONFIGS.PEAK_AMPS));
        pivotConfigs.Feedback.SensorToMechanismRatio = PIVOT_MOTOR_CONFIGS.SENSOR_TO_MECHANISM_RATIO;
        
        // set slot 0 gains
        var pivotSlot0Configs = pivotConfigs.Slot0;
        pivotSlot0Configs.kS = PIVOT_MOTOR_CONFIGS.SLOT_0_kS; // Add 0.25 V output to overcome static friction
        pivotSlot0Configs.kV = PIVOT_MOTOR_CONFIGS.SLOT_0_kV; // A velocity target of 1 rps results in 0.12 V output
        pivotSlot0Configs.kA = PIVOT_MOTOR_CONFIGS.SLOT_0_kA; // An acceleration of 1 rps/s requires 0.01 V output
        pivotSlot0Configs.kP = PIVOT_MOTOR_CONFIGS.SLOT_0_kP; // A position error of 2.5 rotations results in 12 V output
        pivotSlot0Configs.kI = PIVOT_MOTOR_CONFIGS.SLOT_0_kI; // no output for integrated error
        pivotSlot0Configs.kD = PIVOT_MOTOR_CONFIGS.SLOT_0_kD; // A velocity error of 1 rps results in 0.1 V output
        pivotSlot0Configs.kG = PIVOT_MOTOR_CONFIGS.SLOT_0_kG; // Gravity Feed Forward
        pivotSlot0Configs.GravityType = GravityTypeValue.Arm_Cosine;
        
        // set Motion Magic settings
        var motionMagicConfigs = pivotConfigs.MotionMagic;
        motionMagicConfigs.MotionMagicCruiseVelocity = PIVOT_MOTOR_CONFIGS.MOTION_MAGIC_CRUISE_VELOCITY; // Target cruise velocity
        motionMagicConfigs.MotionMagicAcceleration = PIVOT_MOTOR_CONFIGS.MOTION_MAGIC_ACCELERATION; // Target acceleration
        motionMagicConfigs.MotionMagicJerk = PIVOT_MOTOR_CONFIGS.MOTION_MAGIC_JERK; // Target jerk

        talonFXMotor.getConfigurator().apply(pivotConfigs);
        talonFXMotor.setPosition(0);
        talonFXMotor.setNeutralMode(NeutralModeValue.Brake);
    }

    // intake motor configs
    private void applyIntakeConfigs(TalonFX talonFXMotor) {
        var intakeConfigs = new TalonFXConfiguration();
        intakeConfigs.Voltage.withPeakForwardVoltage(Volts.of(INTAKE_MOTOR_CONFIGS.PEEK_FORWARD_VOLTAGE))
                              .withPeakReverseVoltage(Volts.of(-INTAKE_MOTOR_CONFIGS.PEEK_REVERSE_VOLTAGE));
        intakeConfigs.CurrentLimits.withStatorCurrentLimitEnable(true).withStatorCurrentLimit(INTAKE_MOTOR_CONFIGS.PEAK_AMPS);
        intakeConfigs.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;

        talonFXMotor.getConfigurator().apply(intakeConfigs);
        talonFXMotor.setNeutralMode(NeutralModeValue.Brake);
    }

    // new talonFX motor - Pivot
    private TalonFX pivotMotor = new TalonFX(PIVOT_MOTOR_CONFIGS.MOTOR_ID);
    // new talonFX motor - Intake
    private TalonFX intakeMotor = new TalonFX(INTAKE_MOTOR_CONFIGS.MOTOR_ID);

    public final PositionVoltage m_positionVoltage = new PositionVoltage(0).withSlot(0);
    public final MotionMagicVoltage m_MotionMagicVoltage = new MotionMagicVoltage(0).withEnableFOC(true);
    public final PositionTorqueCurrentFOC m_positionTorque = new PositionTorqueCurrentFOC(0).withSlot(1);
    public final StatusSignal<Angle> intakePosition = pivotMotor.getPosition();


    
     // constructor
    public Intake() {
        applyPivotConfigs(pivotMotor);
        applyIntakeConfigs(intakeMotor);
    }

    // method to set pivot motor speed
    public void deployIntake() {
        pivotMotor.setControl(m_MotionMagicVoltage.withPosition(Constants.INTAKE_DEPLOY_POSITION));
    }

    public void retractIntake() {
        pivotMotor.setControl(m_MotionMagicVoltage.withPosition(Constants.INTAKE_RETRACT_POSITION));
    }

    public void stopIntakePivot() {
        pivotMotor.setControl(m_positionVoltage.withPosition(pivotMotor.getPosition().getValueAsDouble()));
    }

    public void intakeFuel() {
        intakeMotor.set(INTAKE_MOTOR_CONFIGS.INTAKE_MOTOR_SPEED);
    }
    
    public void reverseIntakeFuel() {
        intakeMotor.set(-INTAKE_MOTOR_CONFIGS.INTAKE_MOTOR_SPEED);
    }

    public void stopIntakeFuel() {
        intakeMotor.stopMotor();
    }

    public boolean isAtPosition() {
        return intakePosition.refresh().isNear(m_MotionMagicVoltage.Position, PIVOT_MOTOR_CONFIGS.INTAKE_POS_TOLERANCE_IN_ROTATIONS);
    }

    public boolean isDeployed() {
        return (isAtPosition() && intakePosition.refresh().isNear(Constants.INTAKE_DEPLOY_POSITION, PIVOT_MOTOR_CONFIGS.INTAKE_POS_TOLERANCE_IN_ROTATIONS));
    }

    public boolean isRetracted() {
        return (isAtPosition() && intakePosition.refresh().isNear(Constants.INTAKE_RETRACT_POSITION, PIVOT_MOTOR_CONFIGS.INTAKE_POS_TOLERANCE_IN_ROTATIONS));
    }
}
