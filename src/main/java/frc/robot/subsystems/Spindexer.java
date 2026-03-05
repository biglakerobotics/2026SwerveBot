package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants.SPINDEXER_MOTOR_CONFIGS;

public class Spindexer implements Subsystem {
    // define spindexer motor here
    private final TalonFX m_spindexer_motor = new TalonFX(SPINDEXER_MOTOR_CONFIGS.SPINDEXER_MOTOR_ID);

    // configure motor in constructor
    private void applySpindexerConfigs() {
        var spindexerConfigs = new TalonFXConfiguration();
        spindexerConfigs.Voltage.withPeakForwardVoltage(SPINDEXER_MOTOR_CONFIGS.PEEK_FORWARD_VOLTAGE)
                            .withPeakReverseVoltage(-SPINDEXER_MOTOR_CONFIGS.PEEK_REVERSE_VOLTAGE);
        spindexerConfigs.CurrentLimits.withStatorCurrentLimitEnable(true).withStatorCurrentLimit(SPINDEXER_MOTOR_CONFIGS.PEAK_AMPS);
        spindexerConfigs.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
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
        m_spindexer_motor.set(SPINDEXER_MOTOR_CONFIGS.SPINDEXER_MOTOR_SPEED);
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
