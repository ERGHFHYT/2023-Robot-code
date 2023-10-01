// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.NewArm;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.GripperSubsys;

public class GipperCommand extends CommandBase {
  private final GripperSubsys gripperSubsys;
  // private double position;
  private double output;
  /** Creates a new GipperCommand. */
  public GipperCommand(GripperSubsys gripperSubsys,double output) {
    this.gripperSubsys = gripperSubsys;
    this.output = output;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    gripperSubsys.setOutput(output);
      }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // gripperSubsys.setOutput(0);
    gripperSubsys.setPosition(gripperSubsys.getPosition());
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (gripperSubsys.getBusVoltage() < 7.8){
      return true;
    }
    return false;
  }
}
