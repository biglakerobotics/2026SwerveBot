package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;

public class InverseIntakeCommand extends Command {
    private final Intake m_intake;
    public InverseIntakeCommand(Intake subsystem) {
        m_intake = subsystem;
        addRequirements(m_intake);
    }

    @Override
    public void execute() {
        m_intake.InverseIntakeCommand();
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
