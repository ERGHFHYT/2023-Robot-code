// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ShootingCommnads;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants.ShootingConstants;
import frc.robot.commands.NewArm.NewArmCommand;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.CartridgeSubsystem;
import frc.robot.subsystems.shootingSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ShootingGroupCommand extends ParallelDeadlineGroup {

  public ShootingGroupCommand(shootingSubsystem shootingSubsystem,
      CartridgeSubsystem cartridgeSubsystem, ShootingConstants shootingConstants, ArmSubsystem armSubsystem) {
    super(
        new WaitCommand(1.5),
        new NewArmCommand(armSubsystem, 30, 15),
        new SequentialCommandGroup(
            new WaitCommand(0.3),
            new ShootingCommand(shootingSubsystem, cartridgeSubsystem,  shootingConstants.getShootingWheelsOutput(), shootingConstants.getCartridgeOutput())
        )
    );
  }
}
