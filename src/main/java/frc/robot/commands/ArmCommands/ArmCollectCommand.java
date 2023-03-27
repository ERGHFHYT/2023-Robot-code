// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ArmCommands;

import frc.robot.subsystems.armCollectSubsystem;

import edu.wpi.first.wpilibj.Timer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;

/** An example command that uses an example subsystem. */
public class ArmCollectCommand extends CommandBase {
  private final armCollectSubsystem armCollect;
  private double position;
  private double seconds;
  private boolean isFinished;
  private Timer timer = new Timer();

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ArmCollectCommand(armCollectSubsystem armCollect, double position, double seconds) {
    this.armCollect = armCollect;
    this.position = position;
    this.seconds = seconds;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(armCollect);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.reset();
    timer.start();
    isFinished = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() { 
  }

  // Called once the command ends or is interrupted.
  @Override

  public void end(boolean interrupted) {
    // timer.delay(1);
    // armCollect.setArmCollectPosition(0);
    armCollect.setArmCollectPosition(position);

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (seconds == 0){
      isFinished = true;
    }
    else if(timer.hasElapsed(seconds)){
      // System.out.println("rrrrrrrrrrrrrrrr entered! " + position);
      isFinished = true;
    }
    return isFinished;
  }
}