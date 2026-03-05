package frc.robot.commands.Spindexer;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Spindexer;

public class SpinToSpeedCommand extends Command{
    private final Spindexer m_Spindexer;
    private final double m_speed;
    public SpinToSpeedCommand(Spindexer spindexer, double speed) {
        m_Spindexer = spindexer;
        m_speed = speed;
        addRequirements(m_Spindexer);
    }

    @Override
    public void execute() {
        m_Spindexer.setSpindexerSpeed(m_speed);
    }

    @Override
    public void end(boolean interrupted) {
        m_Spindexer.stopSpindexer();
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
