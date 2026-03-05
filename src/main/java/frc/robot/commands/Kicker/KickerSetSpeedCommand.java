package frc.robot.commands.Kicker;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Kicker;

public class KickerSetSpeedCommand extends Command{
    private final Kicker m_Kicker;
    private final double m_Speed;
    public KickerSetSpeedCommand(Kicker kicker, double speed) {
        m_Kicker = kicker;
        m_Speed = speed;
        addRequirements(m_Kicker);
    }

    @Override
    public void execute() {
        m_Kicker.setKickerSpeed(m_Speed);
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
