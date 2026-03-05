package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shooter;

public class ShootAtSetSpeed extends Command{
    private final Shooter m_Shooter;
    private final double m_Speed;
    public ShootAtSetSpeed(Shooter shooter, double speed) {
        m_Shooter = shooter;
        m_Speed = speed;
        addRequirements(m_Shooter);
    }

    @Override
    public void execute() {
        m_Shooter.setShooterSpeed(m_Speed);
    }

    @Override
    public void end(boolean interrupted) {
        m_Shooter.stopShooter();
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
