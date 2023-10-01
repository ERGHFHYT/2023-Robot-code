// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.dashboard.SuperSystem;
import frc.util.PID.Gains;
import frc.util.motor.SuperTalonFX;



public class GripperSubsys extends SuperSystem {
  public SuperTalonFX gripperMotor;
  public Gains gripperGains;
  /** Creates a new GripperSubsys. */
  public GripperSubsys() {
    super("gripper");
    gripperGains = new Gains("gripperGains", 0.135, 0,0);
    gripperMotor = new SuperTalonFX(19, 30, true, false,NeutralMode.Brake, gripperGains,TalonFXControlMode.PercentOutput );
  }

  @Override
  public void periodic() {
    getTab().putInDashboard("gripper position", gripperMotor.getPosition(), 4, 0, false);
    getTab().putInDashboard("gripper voltage", gripperMotor.getBusVoltage(), 3, 0, false);
    getTab().putInDashboard("gripper amper", gripperMotor.getAmper(), 5, 0, false);

    // This method will be called once per scheduler run
  }

  public void setPosition(double position){
    gripperMotor.set(ControlMode.Position, position);
  }

  public void setOutput(double output){
    gripperMotor.set(ControlMode.PercentOutput, output);
  }
   
  public double getAmper(){
    return gripperMotor.getAmper();
  }

  public double getBusVoltage(){
    return gripperMotor.getBusVoltage();
  }

  public double getPosition(){
    return gripperMotor.getPosition();
  }
  public void resetGripper(){
    gripperMotor.reset(0);
  }
}
