// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.NewArm;

import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.armCollectSubsystem;

import edu.wpi.first.wpilibj.Timer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;

/** An example command that uses an example subsystem. */
public class NewArmCommand extends CommandBase {
  private final ArmSubsystem armCollect;
   private double positionBase;
  private double positionMid;
  private double seconds;
  private boolean isFinished;
  private Timer timer = new Timer();

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public NewArmCommand(ArmSubsystem armCollect, double positionMid , double positionBase, double seconds) {
    this.armCollect = armCollect;
    this.positionBase = positionBase;
    this.positionMid = positionMid;
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
  public void execute(

  ) { 
  }

  // Called once the command ends or is interrupted.
  @Override

  public void end(boolean interrupted) {
    // timer.delay(1);
    // armCollect.setArmCollectPosition(0);
    armCollect.setArmMidAndBase(positionMid , positionBase);

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

