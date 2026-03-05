package frc.robot.commands.Spindexer;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Spindexer;

public class SpinReverseCommand extends Command{
    private final Spindexer m_Spindexer;
    public SpinReverseCommand(Spindexer spindexer) {
        m_Spindexer = spindexer;
        addRequirements(m_Spindexer);
    }

    @Override
    public void execute() {
        m_Spindexer.setReverseSpindexerSpeed();
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
