package frc.robot.commands.Kicker;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Kicker;

public class KickerReverseCommand extends Command{
    private final Kicker m_Kicker;
    public KickerReverseCommand(Kicker kicker) {
        m_Kicker = kicker;
        addRequirements(m_Kicker);
    }

    @Override
    public void execute() {
        m_Kicker.setReverseKickerSpeed();
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
