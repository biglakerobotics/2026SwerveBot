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
        climberConfigs.Slot0.kP = CLIMBER_MOTOR_CONFIGS.CLIMBERVOLTS_P_VALUE;
        climberConfigs.Slot0.kI = CLIMBER_MOTOR_CONFIGS.CLIMBERVOLTS_I_VALUE;
        climberConfigs.Slot0.kD = CLIMBER_MOTOR_CONFIGS.CLIMBERVOLTS_D_VALUE;

        climberConfigs.Voltage.withPeakForwardVoltage(Volts.of(Constants.peakVoltage))
            .withPeakReverseVoltage(Volts.of(-Constants.peakVoltage));

        climberConfigs.Slot1.kP = CLIMBER_MOTOR_CONFIGS.CLIMBERTORQUE_P_VALUE;
        climberConfigs.Slot1.kI = CLIMBER_MOTOR_CONFIGS.CLIMBERTORQUE_I_VALUE;
        climberConfigs.Slot1.kD = CLIMBER_MOTOR_CONFIGS.CLIMBERTORQUE_D_VALUE;

        climberConfigs.MotorOutput.withInverted(InvertedValue.CounterClockwise_Positive);
        climberConfigs.CurrentLimits.withStatorCurrentLimit(Constants.peaKAmps).withStatorCurrentLimitEnable(true);
        climberConfigs.SoftwareLimitSwitch.withForwardSoftLimitEnable(true).withForwardSoftLimitThreshold(Constants.softForwardLimitClimber);
        climberConfigs.SoftwareLimitSwitch.withReverseSoftLimitEnable(true).withReverseSoftLimitThreshold(Constants.softReverseLimitClimber);

        climberConfigs.TorqueCurrent.withPeakForwardTorqueCurrent(Amps.of(Constants.peaKAmps))
            .withPeakReverseTorqueCurrent(Amps.of(Constants.peaKAmps));

        StatusCode statusLead = StatusCode.StatusCodeNotInitialized;
        StatusCode statusFollow = StatusCode.StatusCodeNotInitialized;
        for (int i = 0; i < 5; ++i ) {
            statusLead = climberLead.getConfigurator().apply(climberConfigs);
            // statusLead = climberFollow.getConfigurator().apply(climberConfigs);
            if (statusLead.isOK() && !statusFollow.isOK()) break;
        }
        if (!statusLead.isOK() && !statusFollow.isOK()) {
            System.out.println("Could not apply configs to lead, error code: " + statusLead.toString());
            System.out.println("Could not apply configs to follow, error code: " + statusFollow.toString());
        }

        climberLead.setPosition(Constants.startPosition);
        // climberFollow.setPosition(Constants.startPosition);

        // climberFollow.setControl(new Follower(climberLead.getDeviceID(), MotorAlignmentValue.Aligned));
            
        
    }

    public Climber(){
        ClimberConfiguration(); 
        System.out.println(climberLeadSwitch.get());
        System.out.println(climberFollowSwitch.get());
        System.out.println(climberLeadSwitch.getChannel());
        System.out.println(climberFollowSwitch.getChannel());
    }



    public boolean ClimberStartPos() {
        if (!climberLeadSwitch.get()) {
            climberLead.set(-climberSpeed);
        } else {
            ClimberStop();
            climberLead.setPosition(0);
            // climberFollow.setPosition(0);
            m_climberLeadZeroTrue = true; 
            System.out.println("\n Hit LimitSwitch Setting Position To Zero");
        }
        return m_climberLeadZeroTrue;
    }

    public void ClimberSwitch() {
        System.out.println("Start activated: " + climberLeadSwitch.get());
    //    if(climberSpeed < 0) {
            System.out.println("If statement passed");
            // if (getLeadLimitSwitch()) {
            //     System.out.println("second if statment passed");
            //     climberSpeed *= -1;  
            //     climberLead.set(climberSpeed);
            // } else {
                // System.out.println("else statment passed");
                climberLead.set(climberSpeed);      
            // }
    //    } else{
    //     System.out.println("start of followswitch code");
        // if (getLeadLimitSwitch()) {
        //     System.out.println("followswitch If statement passed");
        //     climberSpeed *= -1;
        //     climberLead.set(climberSpeed);
        //    } else {
        //     System.out.println("Followswitch else statement passed");
        //       climberLead.set(climberSpeed);
        //    }
    //    }
    }
    
    public void ClimberStop() {
        climberLead.set(0);
    }
    public void SoftLimitDisable() {
        climberConfigs.SoftwareLimitSwitch.withForwardSoftLimitEnable(false);
        climberConfigs.SoftwareLimitSwitch.withReverseSoftLimitEnable(false);
        climberLead.getConfigurator().apply(climberConfigs);
    }

    public boolean getLeadLimitSwitch() {
        return climberLeadSwitch.get();
    }
}

