package frc.robot.subsystems;

import com.ctre.phoenix6.StatusCode;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.controls.NeutralOut;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.PositionTorqueCurrentFOC;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.MotorAlignmentValue;

import static edu.wpi.first.units.Units.Amps;
import static edu.wpi.first.units.Units.Volts;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants;
import frc.robot.Constants.CLIMBER_MOTOR_CONFIGS;

public class Climber implements Subsystem {

    TalonFX climberLead = new TalonFX(CLIMBER_MOTOR_CONFIGS.climberLeadID);
    // TalonFX climberFollow = new TalonFX(Constants.climberFollowID);
    public DigitalInput climberLeadSwitch = new DigitalInput(0);
    public DigitalInput climberFollowSwitch = new DigitalInput(1);
    public boolean m_climberLeadZeroTrue;
    public double climberPos;

    CommandXboxController xboxController = new CommandXboxController(1);

    public TalonFXConfiguration climberConfigs = new TalonFXConfiguration();

    public final PositionVoltage m_positionVoltage = new PositionVoltage(0).withSlot(0);
    public final MotionMagicVoltage m_MotionMagicVoltage = new MotionMagicVoltage(0).withEnableFOC(true);
    public final PositionTorqueCurrentFOC m_positionTorque = new PositionTorqueCurrentFOC(0).withSlot(1);
    public final NeutralOut m_brake = new NeutralOut();

    double climberSpeed = CLIMBER_MOTOR_CONFIGS.climberSpeed;

    public void ClimberConfiguration() {
    }

    public Climber(){
        ClimberConfiguration(); 
        System.out.println(climberLeadSwitch.get());
        System.out.println(climberFollowSwitch.get());
        System.out.println(climberLeadSwitch.getChannel());
        System.out.println(climberFollowSwitch.getChannel());
    }
}

