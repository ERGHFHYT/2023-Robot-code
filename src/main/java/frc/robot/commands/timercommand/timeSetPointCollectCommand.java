// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.timercommand;
import javax.swing.text.Position;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.CollectSubsystem;

public class timeSetPointCollectCommand extends CommandBase {
  private  CollectSubsystem collectSubsystem;
  private ArmSubsystem armSubsystem;
  private double collectPoint;
  private Timer timer = new Timer();
  private double timeOfFunctioning;
  private double delay;
  /** Creates a new setPoitCollectCommand. */
  public timeSetPointCollectCommand(CollectSubsystem collectSubsystem, ArmSubsystem armSubsystem,double collectPoint, double timeOfFunctioning, double delay) {
    this.collectSubsystem = collectSubsystem;
    this.armSubsystem =armSubsystem;
    this.collectPoint = collectPoint;
    this.timeOfFunctioning = timeOfFunctioning;
    this.delay = delay;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(collectSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.reset();
    timer.start();
    collectSubsystem.setPosition(collectPoint);
    armSubsystem.setBaseArmPosition(12.5);
    // collectSubsystem.setPosition(point);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // SmartDashboard.putNumber("123456789", point);
    if(timer.hasElapsed(delay)){
      // System.out.println("collect pass - " + timer.get());
      // System.out.println("position: " + collectSubsystem.getPosition());
      collectSubsystem.setPosition(collectPoint);
      // armCollectSubsystem.setArmCollectPosition(armCollectPoint); TODO 2.10
    // if (timer.hasElapsed(timeOfFunctioning - 0.7)){
    //   armCollectSubsystem.setArmCollectPosition(0.3);

    //   }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    collectSubsystem.setPosition(0);
    

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (timer.hasElapsed(timeOfFunctioning)){
        timer.reset();
        return true;
      }
      else
        return false;
      }
}