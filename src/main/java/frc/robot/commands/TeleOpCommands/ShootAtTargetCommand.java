package frc.robot.commands.TeleOpCommands;

import static edu.wpi.first.units.Units.Rotations;

import java.lang.constant.Constable;
import java.util.Map;

import com.ctre.phoenix6.swerve.SwerveRequest;
import com.ctre.phoenix6.swerve.SwerveRequest.Idle;

import edu.wpi.first.math.filter.MedianFilter;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.interpolation.InterpolatingDoubleTreeMap;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.SHOOTING_CONSTANTS;
import frc.robot.subsystems.CommandSwerveDrivetrain;
import frc.robot.subsystems.Kicker;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Spindexer;
import frc.robot.subsystems.Turret;

public class ShootAtTargetCommand extends Command{
    // import subsystems and variables here
    CommandSwerveDrivetrain m_drivetrain;
    Turret m_turret;
    Shooter m_shooter;
    Kicker m_kicker;
    Spindexer m_spindexer;
    Translation2d m_targetRed;
    Translation2d m_targetBlue;
    Translation2d targetTranslation;
    InterpolatingDoubleTreeMap m_lookupTable;
    boolean shooting = false;
    ChassisSpeeds robotVelocity;
    InterpolatingDoubleTreeMap m_latencyTable;
   
    


    public ShootAtTargetCommand(CommandSwerveDrivetrain drivetrain, Turret turret, Shooter shooter, Kicker kicker, Spindexer spindexer, Translation2d targetRed, Translation2d targetBlue, InterpolatingDoubleTreeMap lookupTable, InterpolatingDoubleTreeMap latencyTable) {
        m_drivetrain = drivetrain;
        m_turret = turret;
        m_shooter = shooter;
        m_kicker = kicker;
        m_spindexer = spindexer;
        m_targetRed = targetRed;
        m_targetBlue = targetBlue;
        m_lookupTable = lookupTable; 
        m_latencyTable = latencyTable;
        
        robotVelocity = m_drivetrain.getState().Speeds;
        addRequirements( m_turret, m_shooter, m_kicker, m_spindexer);
    }

    @Override
    public void initialize() {
        // m_drivetrain.setControl(new SwerveRequest.Idle());
        shooting = false;
        var alliance = DriverStation.getAlliance();
        targetTranslation = (alliance.isEmpty() || alliance.get() == DriverStation.Alliance.Blue) ? m_targetBlue : m_targetRed;
    }

    @Override
    public void execute() {

        double latency = m_latencyTable.get(Math.sqrt(robotVelocity.vxMetersPerSecond*robotVelocity.vxMetersPerSecond + robotVelocity.vyMetersPerSecond*robotVelocity.vyMetersPerSecond)); // 200 ms latency compensation
        var robotPose = m_drivetrain.getState().Pose;

        var futurePose = robotPose.transformBy(new Transform2d(new Translation2d( robotVelocity.vxMetersPerSecond, robotVelocity.vyMetersPerSecond).times(latency), new Rotation2d(robotVelocity.omegaRadiansPerSecond).times(latency)));
         
      

        

        var turretTranslation = Turret.GetTurretTranslation(futurePose);

        var distanceToTarget = turretTranslation.getDistance(targetTranslation);

        var shooterRPS = m_lookupTable.get(distanceToTarget);
        m_shooter.setShooterSpeed(shooterRPS);

        // Turret
        var angleToTarget = targetTranslation.minus(turretTranslation).getAngle();

        m_turret.turretSpinToPosition(angleToTarget.minus(futurePose.getRotation()).getRotations());

        // System.out.println(m_turret.isAtPosition());

        //if shooter is at speed, and turret is at position, then kick
        if (shooting || m_shooter.isAtSpeed() && m_turret.isAtPosition()) {
            shooting = true;
            m_kicker.setDefaultKickerSpeed();
            m_spindexer.setDefaultSpindexerSpeed();
        }
        
    }

    @Override
    public void end(boolean interrupted) {
        m_spindexer.stopSpindexer();
        m_kicker.stopKicker();
        m_shooter.stopShooter();
        m_turret.stopTurret();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
    
    @Override
    public boolean runsWhenDisabled() {
        return false;
    }

}
