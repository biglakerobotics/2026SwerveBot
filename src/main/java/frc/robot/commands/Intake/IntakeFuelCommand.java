package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;

public class IntakeFuelCommand extends Command {
    private final Intake m_intake;
    public IntakeFuelCommand(Intake subsyIntake) {
        m_intake = subsyIntake;
        addRequirements(m_intake);
    }

    @Override
    public void execute() {
        m_intake.intakeFuel();
    }

    @Override
    public void end(boolean interrupted) {
        m_intake.stopIntakeFuel();
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
