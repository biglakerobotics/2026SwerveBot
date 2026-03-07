package frc.robot.commands.TeleOpCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;

public class RetractIntakeCommand extends Command {
    Intake m_Intake;
    boolean retracted = false;
    
    public RetractIntakeCommand(Intake intake) {
        m_Intake = intake;
        addRequirements(m_Intake);
    }

    @Override
    public void initialize() {
        retracted = m_Intake.isRetracted();
    }

    @Override
    public void execute() {
        if (!retracted) {    
            m_Intake.retractIntake();
            m_Intake.stopIntakeFuel();
        }
    }

    @Override
    public void end(boolean interrupted) {
        m_Intake.stopIntakePivot();
    }
    
}
