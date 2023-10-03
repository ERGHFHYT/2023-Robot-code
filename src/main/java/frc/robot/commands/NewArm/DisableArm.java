// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.NewArm;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.ArmSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class DisableArm extends CommandBase {
  ArmSubsystem armSubsystem;
  public DisableArm(ArmSubsystem armSubsystem) {
    this.armSubsystem = armSubsystem;
    addRequirements(armSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void execute() {
    System.out.println("4444444444444");
    System.out.println("4444444444444");
    System.out.println("4444444444444");
    System.out.println("4444444444444");
    System.out.println("4444444444444");
    System.out.println("4444444444444");
    armSubsystem.setOutputBoth(0, 0);
  }
}
