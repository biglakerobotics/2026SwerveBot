package frc.robot.commands.Turret;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Turret;

public class TurretGoToZeroCommand extends Command {
    private final Turret m_turret;
    public TurretGoToZeroCommand(Turret subsyTurret) {
        m_turret = subsyTurret;
        addRequirements(m_turret);
    }

    @Override
    public void execute() {
        m_turret.turretSpinToZeroPos();
    }

    @Override
    public void end(boolean interrupted) {
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
