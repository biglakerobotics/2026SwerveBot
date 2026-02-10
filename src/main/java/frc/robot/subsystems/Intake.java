package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants;

public class Intake implements Subsystem {
     TalonFX IntakeMotor = new TalonFX(Constants.intakeMotorID);
     TalonFX IntakeJointMotor = new TalonFX(Constants.intakeJointMotorID);

    // double voltage_scale_factor = 5/RobotController.getVoltage5V();
    // double currentDistanceCm = 0.0;

    public void IntakeCommand(){
        IntakeMotor.set(Constants.INTAKESPEED);
    }
    public void InverseIntakeCommand(){
        IntakeMotor.set(-Constants.INTAKESPEED);
    }

    public void StopIntakeCommand(){
        IntakeMotor.set(0);
    }
    
    public void DeployIntakeCommand(){
        IntakeJointMotor.set(Constants.INTAKEJOINTSPEED);
    }

    public void RetractIntakeCommand(){
        IntakeJointMotor.set(-Constants.INTAKEJOINTSPEED);

    }   

    public void LockIntakeCommand(){
        IntakeMotor.set(0);
    }


}
