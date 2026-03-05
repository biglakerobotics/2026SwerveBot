package frc.robot.subsystems;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.controls.NeutralOut;
import com.ctre.phoenix6.controls.PositionTorqueCurrentFOC;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import static edu.wpi.first.units.Units.*;

import java.lang.annotation.Target;

import org.ejml.equation.IntegerSequence.Range;
import org.photonvision.PhotonCamera;
import org.photonvision.PhotonPoseEstimator;


import edu.wpi.first.apriltag.AprilTagDetection;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants;
import frc.robot.Vision;
import frc.robot.PhotonVision.Camera;


public class Turret implements Subsystem {

    TalonFX turretMotor = new TalonFX(15);

       public void TurretManualMove(){
        turretMotor.set(Constants.turretSpeed);
    }

 }

        
        
    







    
    


    

