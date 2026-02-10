package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;

public class DeployIntakeCommand extends Command {
    private final Intake m_intake;
    public DeployIntakeCommand(Intake subsystem) {
        m_intake = subsystem;
        addRequirements(m_intake);
    }

    @Override
    public void execute() {
        m_intake.DeployIntakeCommand();
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
