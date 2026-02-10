package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;

public class IntakeStopCommand extends Command {
    private final Intake m_intake;
    public IntakeStopCommand(Intake subsystem) {
        m_intake = subsystem;
        addRequirements(m_intake);
    }

    @Override
    public void execute() {
        m_intake.StopIntakeCommand();
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
