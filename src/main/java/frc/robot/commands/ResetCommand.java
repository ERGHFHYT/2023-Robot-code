// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.RobotContainer;
import frc.robot.SwerveModule;
import frc.robot.subsystems.Swerve;
import edu.wpi.first.wpilibj.AnalogEncoder;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class ResetCommand extends CommandBase {
  private final Swerve exampleSubsystem;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ResetCommand(Swerve exampleSubsystem) {
    this.exampleSubsystem = exampleSubsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(exampleSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  // for(SwerveModule mod : RobotContainer.s_Swerve.mSwerveMods){
    for(int i = 0; i < RobotContainer.s_Swerve.mSwerveMods.length; i++){
      RobotContainer.s_Swerve.mSwerveMods[i].angleEncoder.setSelectedSensorPosition(0);
  }
}
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public boolean runsWhenDisabled() {
      // TODO Auto-generated method stub
      return true;
  }
}

    

