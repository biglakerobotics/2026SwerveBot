package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Climber;

public class ClimberSwitchCommand extends Command {
    private final Climber m_climber;
    public ClimberSwitchCommand(Climber subsystem){
        m_climber = subsystem;
        addRequirements(m_climber);
    }

    @Override 
    public void execute (){
        if (!m_climber.getLeadLimitSwitch()) {
            m_climber.ClimberSwitch();
        } else {
            m_climber.ClimberStop();
        }
    }

    @Override 
    public void end(boolean interrupted) {
        m_climber.ClimberStop();
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
