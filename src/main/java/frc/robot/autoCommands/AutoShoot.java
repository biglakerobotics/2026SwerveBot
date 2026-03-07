package frc.robot.autoCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shooter;

public class AutoShoot extends Command{
    private final Shooter m_Shooter;
    public AutoShoot(Shooter autoShooter) {
        m_Shooter = autoShooter;
        addRequirements(m_Shooter);
    }

    @Override
    public void execute() {
        m_Shooter.setMaxSpeed();
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
