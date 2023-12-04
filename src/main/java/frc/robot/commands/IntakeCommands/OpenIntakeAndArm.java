package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.ARM1.ARMposition;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.CollectSubsystem;
import frc.robot.subsystems.collectWheelsSubsystem;

public class OpenIntakeAndArm extends ParallelCommandGroup
{
    
    public OpenIntakeAndArm(CollectSubsystem collectSubsystem,collectWheelsSubsystem collectWheels, ArmSubsystem armSubsystem, double wheelsOutput, double centeringOutput, double collectPoint)
    {
        addCommands(
            new CollectPosition(collectSubsystem, collectPoint),
            new ARMposition(armSubsystem, 12.5),
            new collectWheelsCommand(collectWheels, wheelsOutput, centeringOutput)
            );
    }
}
