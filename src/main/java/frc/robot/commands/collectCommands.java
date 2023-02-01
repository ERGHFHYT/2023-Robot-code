package frc.robot.commands;

import frc.robot.subsystems.collectSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.collectSubsystem;

public class collectCommands extends CommandBase {
    collectSubsystem collectsystem;
    double output;

    public collectCommands(collectSubsystem collectSubsystem,double output){
        addRequirements(collectSubsystem);
        this.collectsystem = collectsystem;
        this.output = output;
    }

    public void initialize(){
        
    }

    public void execute(){
        collectsystem.setOutput();
    }

    public void end(boolean interrupted) {
        collectcSystem.setOutput(0);
    }

    public boolean isFinished() {
        return false;
    }
}