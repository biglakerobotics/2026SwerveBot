package frc.robot.commands.Kicker;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Kicker;

public class KickerToShooterCommand extends Command{
    private final Kicker m_Kicker;
    public KickerToShooterCommand(Kicker kicker) {
        m_Kicker = kicker;
        addRequirements(m_Kicker);
    }

    @Override
    public void execute() {
        m_Kicker.setDefaultKickerSpeed();
    }

    @Override
    public void end(boolean interrupted) {
        m_Kicker.stopKicker();
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
