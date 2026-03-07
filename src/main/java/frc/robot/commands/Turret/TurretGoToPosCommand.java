package frc.robot.commands.Turret;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Turret;

public class TurretGoToPosCommand extends Command {
    private final Turret m_turret;
    private final double m_position;
    public TurretGoToPosCommand(Turret subsyTurret, double position) {
        m_turret = subsyTurret;
        m_position = position;
        addRequirements(m_turret);
    }

    @Override
    public void execute() {
        m_turret.turretSpinToPosition(m_position);
    }

    @Override
    public void end(boolean interrupted) {
        m_turret.stopTurret();
    }

    @Override
    public boolean isFinished() {
        return m_turret.isAtPosition();
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;
    }
}
