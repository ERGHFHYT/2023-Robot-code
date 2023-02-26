// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotButtons;
import frc.robot.subsystems.Swerve;
import frc.robot.autos.TestAuto;
import frc.robot.autos.centerToRampa;
import frc.robot.commands.timercommand.timeSetPointCollectCommand;
import frc.robot.subsystems.CollectSubsystem;
import frc.robot.subsystems.GripperSubsystem;
import frc.robot.subsystems.ShootingSubsystem;
import frc.robot.subsystems.armSubsystem;
import frc.robot.commands.timercommand.TimerArmPosition;
import frc.robot.commands.timercommand.TimerGripperCommand;
import frc.robot.commands.timercommand.openInParallel;


// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class atuo1 extends SequentialCommandGroup {
  /** Creates a new atuo1. */
  private final Swerve s_Swerve;
  private ShootingSubsystem ShootingSubsystem;
  private CollectSubsystem collectSubsystem;
  private GripperSubsystem gripperSubsystem;
  private armSubsystem armSubsystem;
  private collectGroupCommand collectGroupCommand;
  private timeSetPointCollectCommand timeSetPointCollectCommand;
  private TimerArmPosition TimerArmPosition;
  

  
  public atuo1(Swerve swerve,
  armSubsystem armSubsystem,
  CollectSubsystem collectSubsystem,
  ShootingSubsystem ShootingSubsystem,
  GripperSubsystem gripperSubsystem
) {
    this.s_Swerve = swerve;
    this.collectSubsystem = collectSubsystem;
    this.ShootingSubsystem = ShootingSubsystem;
    this.armSubsystem = armSubsystem;
    
  
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    
  
    addCommands(new resetCommand(ShootingSubsystem, collectSubsystem, armSubsystem, gripperSubsystem),
    new openInParallel(armSubsystem, collectSubsystem,  gripperSubsystem, 0.65, -63.5, 4.4, 290, 2, 0, 0),
    new TimerGripperCommand(armSubsystem, -12.5, 0.5, gripperSubsystem),
    new openInParallel(armSubsystem, collectSubsystem, gripperSubsystem,0.4, -10, 4.4, 0, 1.5, 1, 0),
    centerToRampa.getAutoCommand(s_Swerve));
  }
}
