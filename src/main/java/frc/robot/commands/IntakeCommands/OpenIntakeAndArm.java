package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.NewArm.NewArmCommand;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.CollectSubsystem;
import frc.robot.subsystems.armCollectSubsystem;
import frc.robot.subsystems.collectWheelsSubsystem;

public class OpenIntakeAndArm extends ParallelCommandGroup
{
    
    public OpenIntakeAndArm(CollectSubsystem collectSubsystem,collectWheelsSubsystem collectWheels, ArmSubsystem armSubsystem, double wheelsOutput, double centeringOutput, double collectPoint, double middleArmPosition, double baseArmPosition)
    {
        addCommands(
            new setPointCollectCommand(collectSubsystem, collectPoint, armSubsystem, baseArmPosition, middleArmPosition),
            new NewArmCommand(armSubsystem, middleArmPosition, baseArmPosition, 0),
            new collectWheelsCommand(collectWheels, wheelsOutput, centeringOutput)
            );
    }
}
