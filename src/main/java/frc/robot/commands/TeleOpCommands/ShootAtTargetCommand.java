package frc.robot.commands.TeleOpCommands;

import static edu.wpi.first.units.Units.Rotations;

import edu.wpi.first.math.filter.MedianFilter;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.interpolation.InterpolatingDoubleTreeMap;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.Command;
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

    public ShootAtTargetCommand(CommandSwerveDrivetrain drivetrain, Turret turret, Shooter shooter, Kicker kicker, Spindexer spindexer, Translation2d targetRed, Translation2d targetBlue, InterpolatingDoubleTreeMap lookupTable) {
        m_drivetrain = drivetrain;
        m_turret = turret;
        m_shooter = shooter;
        m_kicker = kicker;
        m_spindexer = spindexer;
        m_targetRed = targetRed;
        m_targetBlue = targetBlue;
        m_lookupTable = lookupTable;
        addRequirements(m_drivetrain, m_turret, m_shooter, m_kicker, m_spindexer);
    }

    @Override
    public void initialize() {
        shooting = false;
        var alliance = DriverStation.getAlliance();
        targetTranslation = (alliance.isEmpty() || alliance.get() == DriverStation.Alliance.Blue) ? m_targetBlue : m_targetRed;
    }

    @Override
    public void execute() {
        var robotPose = m_drivetrain.getState().Pose;

        var turretTranslation = Turret.GetTurretTranslation(robotPose);

        var distanceToTarget = turretTranslation.getDistance(targetTranslation);

        var shooterRPS = m_lookupTable.get(distanceToTarget);
        m_shooter.setShooterSpeed(shooterRPS);

        // Turret
        var angleToTarget = targetTranslation.minus(turretTranslation).getAngle();
        m_turret.turretSpinToPosition(angleToTarget.minus(robotPose.getRotation()).getRotations());

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
