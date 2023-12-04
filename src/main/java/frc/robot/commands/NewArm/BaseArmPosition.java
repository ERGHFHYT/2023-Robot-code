// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.NewArm;

import frc.robot.subsystems.ArmSubsystem;

import javax.sql.rowset.spi.SyncResolver;

import edu.wpi.first.wpilibj.Timer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;

/** An example command that uses an example subsystem. */
public class BaseArmPosition extends CommandBase {
  private final ArmSubsystem armSubsystem;
   private double positionBase;


  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public BaseArmPosition(ArmSubsystem armSubsystem, double positionBase) {
    this.armSubsystem = armSubsystem;
    this.positionBase = positionBase;


    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(armSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() { 
    armSubsystem.setBaseArmPosition(positionBase);
  }
  
  // Called once the command ends or is interrupted.
  @Override
  
  public void end(boolean interrupted) {
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (armSubsystem.getBasePosition() > 11){
      return true;
    }else{
      return false;
    }
  }
}

