package frc.robot.subsystems;

import static edu.wpi.first.units.Units.Volts;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.MotorAlignmentValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants.SHOOTER_MOTOR_CONFIGS;

public class Shooter implements Subsystem {
    // define motors for left and right motors of the shooter
    private final TalonFX m_right_motor = new TalonFX(SHOOTER_MOTOR_CONFIGS.RIGHT_MOTOR_ID);
    private final TalonFX m_left_motor = new TalonFX(SHOOTER_MOTOR_CONFIGS.LEFT_MOTOR_ID);

    // Shooter motor configs
    private void applyShooterConfigs(TalonFX talonFXMotor, boolean isLeftMotor) {
        var shootConfigs = new TalonFXConfiguration();
        shootConfigs.Voltage.withPeakForwardVoltage(Volts.of(SHOOTER_MOTOR_CONFIGS.PEEK_FORWARD_VOLTAGE))
                              .withPeakReverseVoltage(Volts.of(-SHOOTER_MOTOR_CONFIGS.PEEK_REVERSE_VOLTAGE));
        shootConfigs.CurrentLimits.withStatorCurrentLimitEnable(true).withStatorCurrentLimit(SHOOTER_MOTOR_CONFIGS.PEAK_AMPS);
        shootConfigs.MotorOutput.Inverted = isLeftMotor ? InvertedValue.Clockwise_Positive : InvertedValue.CounterClockwise_Positive;
        talonFXMotor.getConfigurator().apply(shootConfigs);
        talonFXMotor.setNeutralMode(NeutralModeValue.Brake);
        // set slot 0 gains
        var shooterSlot0Configs = shootConfigs.Slot0;
        shooterSlot0Configs.kS = SHOOTER_MOTOR_CONFIGS.SLOT_0_kS; // Add 0.25 V output to overcome static friction
        shooterSlot0Configs.kV = SHOOTER_MOTOR_CONFIGS.SLOT_0_kV; // A velocity target of 1 rps results in 0.12 V output
        shooterSlot0Configs.kA = SHOOTER_MOTOR_CONFIGS.SLOT_0_kA; // An acceleration of 1 rps/s requires 0.01 V output
        shooterSlot0Configs.kP = SHOOTER_MOTOR_CONFIGS.SLOT_0_kP; // A position error of 2.5 rotations results in 12 V output
        shooterSlot0Configs.kI = SHOOTER_MOTOR_CONFIGS.SLOT_0_kI; // no output for integrated error
        shooterSlot0Configs.kD = SHOOTER_MOTOR_CONFIGS.SLOT_0_kD; // A velocity error of 1 rps results in 0.1 V output
    }
    
    // configure motors in constructor
    public Shooter() {
        applyShooterConfigs(m_left_motor, true);
        applyShooterConfigs(m_right_motor, false);
        m_right_motor.setControl(new Follower(m_left_motor.getDeviceID(), MotorAlignmentValue.Opposed));
    }

    // method to set shooter speed
    public void setShooterSpeed(double speedMultiplier) {
        m_left_motor.set(SHOOTER_MOTOR_CONFIGS.SHOOTER_MOTOR_MAX_SPEED * speedMultiplier);
    }

    // method to stop shooter
    public void stopShooter() {
        m_left_motor.stopMotor();
    }
    
    // method to set shooter to pass speed
    public void setPassSpeed() {
        m_left_motor.set(SHOOTER_MOTOR_CONFIGS.SHOOTER_MOTOR_PASS_SPEED);
    }

    // method to set shooter to max speed
    public void setMaxSpeed() { 
        m_left_motor.set(SHOOTER_MOTOR_CONFIGS.SHOOTER_MOTOR_MAX_SPEED);
    }
}
