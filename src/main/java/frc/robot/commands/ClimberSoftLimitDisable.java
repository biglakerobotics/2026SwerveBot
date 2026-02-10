package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Climber;

public class ClimberSoftLimitDisable extends Command {
    private final Climber m_climber;

    public ClimberSoftLimitDisable(Climber subsystem) {
        m_climber = subsystem;
        addRequirements(m_climber);
    }

    @Override
    public void execute() {
        m_climber.SoftLimitDisable();

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
