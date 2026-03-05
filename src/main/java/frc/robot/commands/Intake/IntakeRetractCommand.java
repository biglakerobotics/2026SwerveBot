package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;

public class IntakeRetractCommand extends Command {
    private final Intake m_intake;
    public IntakeRetractCommand(Intake subsyIntake) {
        m_intake = subsyIntake;
        addRequirements(m_intake);
    }

    @Override
    public void execute() {
        m_intake.retractIntake();
    }

    @Override
    public void end(boolean interrupted) {
        m_intake.stopIntakePivot();
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
