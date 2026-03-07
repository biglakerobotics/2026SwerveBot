package frc.robot.subsystems;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants.KICKER_MOTOR_CONFIGS;
import frc.robot.Constants.SHOOTER_MOTOR_CONFIGS;

public class Kicker implements Subsystem {
    // define kicker motor here
    private final TalonFX m_kicker_motor = new TalonFX(KICKER_MOTOR_CONFIGS.KICKER_MOTOR_ID);
    public final StatusSignal<AngularVelocity> kickerAngularVelocity = m_kicker_motor.getVelocity();
    private final VelocityVoltage m_velocityVoltage = new VelocityVoltage(0).withSlot(0);

        // configure motor in constructor
        private void applyKickerConfigs() {
            var kickerConfigs = new TalonFXConfiguration();
            kickerConfigs.Voltage.withPeakForwardVoltage(KICKER_MOTOR_CONFIGS.PEEK_FORWARD_VOLTAGE)
                                .withPeakReverseVoltage(-KICKER_MOTOR_CONFIGS.PEEK_REVERSE_VOLTAGE);
            kickerConfigs.CurrentLimits.withStatorCurrentLimitEnable(true).withStatorCurrentLimit(KICKER_MOTOR_CONFIGS.PEAK_AMPS);
            kickerConfigs.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
            m_kicker_motor.getConfigurator().apply(kickerConfigs);
            m_kicker_motor.setNeutralMode(NeutralModeValue.Brake);
        }

        public Kicker() {
            applyKickerConfigs();
        }
    
        // method to set kicker speed
        public void setKickerSpeed(double speedMultiplier) {
            m_kicker_motor.set(KICKER_MOTOR_CONFIGS.KICKER_MOTOR_SPEED * speedMultiplier);
        }

        // method to set kicker to default speed
        public void setDefaultKickerSpeed() {
            m_kicker_motor.setControl(m_velocityVoltage.withVelocity(KICKER_MOTOR_CONFIGS.KICKER_MOTOR_SPEED));
        }

        // method to set kicker to reverse speed
        public void setReverseKickerSpeed() {
            m_kicker_motor.set(-KICKER_MOTOR_CONFIGS.KICKER_MOTOR_SPEED);
        }

        public boolean isAtSpeed() {
            return kickerAngularVelocity.refresh().isNear(KICKER_MOTOR_CONFIGS.KICKER_MOTOR_SPEED, KICKER_MOTOR_CONFIGS.KICKER_RPS_TOLERANCE);
        }
    
        // method to stop kicker
        public void stopKicker() {
            m_kicker_motor.stopMotor();
        }
}
