package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants.SHOOTER_MOTOR_CONFIGS;
import frc.robot.Constants.SPINDEXER_MOTOR_CONFIGS;

public class Spindexer implements Subsystem {
    // define spindexer motor here
    private final TalonFX m_spindexer_motor = new TalonFX(SPINDEXER_MOTOR_CONFIGS.SPINDEXER_MOTOR_ID);
    private final VelocityVoltage m_velocityVoltage = new VelocityVoltage(0).withSlot(0);
    private final VoltageOut m_voltageOut = new VoltageOut(0);

    // configure motor in constructor
    private void applySpindexerConfigs() {
        var spindexerConfigs = new TalonFXConfiguration();
        spindexerConfigs.Voltage.withPeakForwardVoltage(SPINDEXER_MOTOR_CONFIGS.PEEK_FORWARD_VOLTAGE)
                            .withPeakReverseVoltage(-SPINDEXER_MOTOR_CONFIGS.PEEK_REVERSE_VOLTAGE);
        spindexerConfigs.CurrentLimits.withStatorCurrentLimitEnable(true)
                                        .withStatorCurrentLimit(SPINDEXER_MOTOR_CONFIGS.STATOR_PEAK_AMPS)
                                        .withSupplyCurrentLimit(SPINDEXER_MOTOR_CONFIGS.SUPPLY_PEAK_AMPS);
        spindexerConfigs.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
        
        var spindexerSlot0Configs = spindexerConfigs.Slot0;
        spindexerSlot0Configs.kS = SHOOTER_MOTOR_CONFIGS.SLOT_0_kS; // Add 0.25 V output to overcome static friction
        spindexerSlot0Configs.kV = SHOOTER_MOTOR_CONFIGS.SLOT_0_kV; // A velocity target of 1 rps results in 0.12 V output
        spindexerSlot0Configs.kA = SHOOTER_MOTOR_CONFIGS.SLOT_0_kA; // An acceleration of 1 rps/s requires 0.01 V output
        spindexerSlot0Configs.kP = SHOOTER_MOTOR_CONFIGS.SLOT_0_kP; // A position error of 2.5 rotations results in 12 V output
        spindexerSlot0Configs.kI = SHOOTER_MOTOR_CONFIGS.SLOT_0_kI; // no output for integrated error
        spindexerSlot0Configs.kD = SHOOTER_MOTOR_CONFIGS.SLOT_0_kD; // A velocity error of 1 rps results in 0.1 V output
        m_spindexer_motor.getConfigurator().apply(spindexerConfigs);
        m_spindexer_motor.setNeutralMode(NeutralModeValue.Brake);
    }

    public Spindexer() {
        applySpindexerConfigs();
    }

    // method to set spindexer speed
    public void setSpindexerSpeed(double speedMultiplier) {
        m_spindexer_motor.set(SPINDEXER_MOTOR_CONFIGS.SPINDEXER_MOTOR_SPEED * speedMultiplier);
    }

    // method to set spindexer to default speed
    public void setDefaultSpindexerSpeed() {
        m_spindexer_motor.setControl(m_velocityVoltage.withVelocity(SPINDEXER_MOTOR_CONFIGS.SPINDEXER_MOTOR_SPEED));
    }

    // method to set spindexer to reverse speed
    public void setReverseSpindexerSpeed() {
        m_spindexer_motor.set(-SPINDEXER_MOTOR_CONFIGS.SPINDEXER_MOTOR_SPEED);
    }

    // method to stop spindexer
    public void stopSpindexer() {
        m_spindexer_motor.stopMotor();
    }
}
