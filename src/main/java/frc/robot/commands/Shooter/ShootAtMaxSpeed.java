package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shooter;

public class ShootAtMaxSpeed extends Command{
    private final Shooter m_Shooter;
    public ShootAtMaxSpeed(Shooter shooter) {
        m_Shooter = shooter;
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
