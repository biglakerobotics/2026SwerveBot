package frc.robot.commands.TurretMath;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import frc.robot.subsystems.CommandSwerveDrivetrain;

public class VectorVelocity {

    CommandSwerveDrivetrain m_drivetrain;

    ChassisSpeeds robotVelocity;
    double velvec = 0.0;
    

    public VectorVelocity(){

        var robotPose = m_drivetrain.getState().Pose;
        var xv = robotVelocity.vxMetersPerSecond;
        var yv = robotVelocity.vyMetersPerSecond;


          
        double latency = 0.0;


    

        Translation2d futurePos = robotPose.getTranslation().plus(
            new Translation2d(xv, yv).times(latency)
        );

        Translation2d goalLocation = null;
        Translation2d targetVec = goalLocation.minus(futurePos);
        double dist = targetVec.getNorm();


          



        



    
        

    };

    

}
