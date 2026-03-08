package frc.robot.commands.TeleOpCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;

public class IntakeFuelCommand extends Command {
    Intake m_Intake;
    boolean deployed = false;
    
    public IntakeFuelCommand(Intake intake) {
        m_Intake = intake;
        addRequirements(m_Intake);
    }

    @Override
    public void initialize() {
        deployed = m_Intake.isDeployed();
    }

    @Override
    public void execute() {
        if (deployed) {    
            m_Intake.intakeFuel();
        } else {
            m_Intake.deployIntake();
            // m_Intake.intakeFuel();
        }
    }

    @Override
    public void end(boolean interrupted) {
        m_Intake.stopIntakeFuel();
        m_Intake.stopIntakePivot();
    }
    
}
