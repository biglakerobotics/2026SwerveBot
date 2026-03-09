package frc.robot.commands.TeleOpCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;

public class IntakeFuelCommand extends Command {
    Intake m_Intake;
    
    public IntakeFuelCommand(Intake intake) {
        m_Intake = intake;
        addRequirements(m_Intake);
    }

    @Override
    public void initialize() {
        m_Intake.deployIntake();
    }

    @Override
    public void execute() {
        if (m_Intake.isDeployed()) {    
            m_Intake.intakeFuel();
        } else {
            m_Intake.deployIntake();
        }
    }

    @Override
    public void end(boolean interrupted) {
        m_Intake.stopIntakeFuel();
        m_Intake.stopIntakePivot();
    }
    
}
