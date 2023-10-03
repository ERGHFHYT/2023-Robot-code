// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autoCommands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.RobotButtons;
import frc.robot.autos.AutoCommand;
import frc.robot.subsystems.Swerve;
import frc.robot.commands.timercommand.timeSetPointCollectCommand;
import frc.robot.subsystems.CollectSubsystem;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.CartridgeSubsystem;
import frc.robot.subsystems.collectWheelsSubsystem;
import frc.robot.subsystems.shootingSubsystem;
import frc.robot.commands.ShootingCommnads.ShootingCommand;
import frc.robot.commands.ShootingCommnads.ShootingGroupCommand;
import frc.robot.commands.SwereCommands.BalanceCommand;
import frc.robot.commands.SwereCommands.TurnToZeroCommand;
import frc.robot.commands.SwereCommands.lockWheelsCommnad;
import frc.robot.commands.NewArm.CloseArm;
// import frc.robot.commands.ClosingCollectGroupCommand;
import frc.robot.commands.ShootingCommnads.CartridgeOutputCommand;


// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class CenterFarFromHumanCube extends SequentialCommandGroup {
  /** Creates a new atuo1. */
  public CenterFarFromHumanCube(Swerve swerve,
  CollectSubsystem collectSubsystem,
  CartridgeSubsystem cartridgeSubsystem,
  collectWheelsSubsystem collectWheels, shootingSubsystem shootingSubsystem, ArmSubsystem armSubsystem
) {

    
  
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    
  
    addCommands(new ParallelDeadlineGroup(new WaitCommand(14.85), new SequentialCommandGroup(new InstantCommand(() -> swerve.zeroGyro()), new ShootingGroupCommand(shootingSubsystem, cartridgeSubsystem, Constants.SHOOTING_AUTO_HIGH, armSubsystem),
    new StartAuto(AutoCommand.getAutoCommand(swerve, "center - start far from human cube", 3), swerve),
    new moveInParallel(swerve, collectSubsystem, collectWheels, armSubsystem, cartridgeSubsystem, AutoCommand.getAutoCommand(swerve, "center - far from human cube", 3), Constants.COLLECT_OPEN_POSITION, Constants.ARM_OPEN_POSITION, 2, 0.5, false),
    // AutoCommand.getAutoCommand(s_Swerve),
    new BalanceCommand(swerve, true),
    new TurnToZeroCommand(swerve),
    new lockWheelsCommnad(swerve, true),
    // new ShootingCommand(shootingSubsystem, cartridgeSubsystem, armCollectSubsystem, 0.75, 0.3)
    new ShootingGroupCommand(shootingSubsystem, cartridgeSubsystem, Constants.SHOOTING_AUTO_HIGH, armSubsystem)
    )),
      new lockWheelsCommnad(swerve),
      new CloseArm(armSubsystem, -0.4, -0.4)
    );  
  }
}
