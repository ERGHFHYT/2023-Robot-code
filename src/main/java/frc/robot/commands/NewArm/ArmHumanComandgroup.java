// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.NewArm;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.GripperSubsys;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ArmHumanComandgroup extends SequentialCommandGroup {
  /** Creates a new ArmHumanComandgroup. */
  public ArmHumanComandgroup(ArmSubsystem armSubsystem, double MidArmPosition, double BaseArmPosition ) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new NewArmCommand(armSubsystem, 22, 15),
      new MidArmPosition(armSubsystem, MidArmPosition), 
      new BaseArmPosition(armSubsystem, BaseArmPosition)

    );
  }
}
