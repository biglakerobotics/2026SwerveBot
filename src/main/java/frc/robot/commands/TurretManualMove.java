package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Turret;

public class TurretManualMove extends Command {
    private final Turret m_Turret;
    public TurretManualMove(Turret subsystem) {
        m_Turret = subsystem;
        addRequirements(m_Turret);
    }

    @Override
    public void execute() {
        m_Turret.TurretManualMove();
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
