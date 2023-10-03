// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.time.chrono.ThaiBuddhistChronology;

import javax.swing.text.Position;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Constants;
import frc.robot.commands.ArmCommands.ResetArmCommand;
import frc.robot.commands.NewArm.DisableArm;
import frc.util.dashboard.SuperSubSystemTab;
import frc.util.SuperSystem;
import frc.util.PID.Gains;
import frc.util.motor.SuperSparkMax;
import frc.util.motor.SuperTalonFX;

// Yteam Example Subsystem
public class ArmSubsystem extends SuperSystem {
  public SuperSparkMax middleArmMotor;
  public SuperSparkMax baseArmMotor;
  public CollectSubsystem collectSubsystem;
  public Gains armMidGains;
  public Gains armBaseGains;
  public DigitalInput downMicroSwitchBase;
  public DigitalInput downMicroSwitchMid;




  // Motors, Selenoid and Sensors declaration
  public ArmSubsystem() {
    super("arm");
    armBaseGains = new Gains("armBaseGains", 0, 0, 0.05, 0.000, 0.00035, 0.001, 0);
    armMidGains = new Gains("armMidGains", 0, 0, 0.04, 0, 0.0002, 0.001, 0 );
    downMicroSwitchBase = new DigitalInput(Constants.ARM_DOWN_MICROSWITCH);
    downMicroSwitchMid = new DigitalInput(1);
      middleArmMotor = new SuperSparkMax(18, MotorType.kBrushless, 30, false, 1, 1, IdleMode.kBrake,
      ControlType.kSmartMotion, armMidGains, 1, 1, 0);
      baseArmMotor = new SuperSparkMax(20, MotorType.kBrushless, 40, false, 1, 1, IdleMode.kBrake,
      ControlType.kSmartMotion, armBaseGains, 1, 1, 0);
      this.resetBaseArmEncoder();
      this.resetMiddleArmEncoder();
        getTab().addCommandToDashboard("Reset base", new InstantCommand(() -> this.resetBaseArmEncoder()));
        getTab().addCommandToDashboard("Reset middle", new InstantCommand(() -> this.resetMiddleArmEncoder()));
  }

  /** Creates a new ExampleSubsystem. */

  @Override
  public void periodic() {
    if (this.isShootingDownMid()){
      this.resetMiddleArmEncoder();
    }
    if (this.isShootingDownBase()){
      this.resetBaseArmEncoder();
    }
    getTab().putInDashboard("collect middle position", middleArmMotor.getPosition(), 4, 0, false);
    getTab().putInDashboard("collect base position", baseArmMotor.getPosition(), 5, 0, false);
    getTab().putInDashboard("collect micriMid", downMicroSwitchMid.get(), 6, 0, false);
    getTab().putInDashboard("collect micriBase", downMicroSwitchBase.get(), 7, 0, false);
    // This method will be called once per scheduler run
    // SmartDashboard.putNumber("Gripper Position", gripperMotor.getPosition());
  }



  public void resetBaseArmEncoder(){
    baseArmMotor.reset(0);
  }
  public void resetMiddleArmEncoder(){
    middleArmMotor.reset(0);
  }

  public  void setMiddleArmPosition(double position) {
    middleArmMotor.setMode(ControlMode.Position);
    middleArmMotor.getPIDController().setReference(position, ControlType.kPosition);
    // SmartDashboard.putNumber("gripper target", position);
  }

  public  void setBaseArmPosition(double position) {
    baseArmMotor.setMode(ControlMode.Position);
    baseArmMotor.getPIDController().setReference(position, ControlType.kPosition);

  }

  public void setArmMidAndBase(double positionMid , double positionBase){
   this.setBaseArmPosition(positionBase);
   this.setMiddleArmPosition(positionMid);
  }

  public void setArmOutput(double output) {
    middleArmMotor.setMode(ControlMode.PercentOutput);
    middleArmMotor.set(output);
  //   if (((output <= 0 && !isShootingDown()))){
  //     armCollectMotor.set(output);
  //   }
  }

  public void setArmMidOutput(double MidOutput) {
    middleArmMotor.setMode(ControlMode.PercentOutput);
    middleArmMotor.set(MidOutput);
  //   if (((output <= 0 && !isShootingDown()))){
  //     armCollectMotor.set(output);
  //   }
  }

  public void setArmBaseOutput(double BaseOutput) {
    middleArmMotor.setMode(ControlMode.PercentOutput);
    middleArmMotor.set(BaseOutput);
  }

  public void setOutputBoth(double BaseOutput, double MidOutput){
    this.setArmMidOutput(MidOutput);
    this.setArmBaseOutput(BaseOutput);
  }


  public boolean isShootingDownMid(){
    
    return downMicroSwitchMid.get();
    
  }

  public double getBasePosition(){
    
    return baseArmMotor.getPosition();
    
  }

  public double getMidPosition(){
    
    return middleArmMotor.getPosition();
    
  }

  public boolean isShootingDownBase(){
    return downMicroSwitchBase.get();
}

public void SetDisable(){
  // setDefaultCommand(new DisableArm(this));
}

public void SetTeleop(){
  // setDefaultCommand
}
}

