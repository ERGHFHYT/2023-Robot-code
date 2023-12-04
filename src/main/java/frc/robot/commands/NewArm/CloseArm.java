// // Copyright (c) FIRST and other WPILib contributors.
// // Open Source Software; you can modify and/or share it under the terms of
// // the WPILib BSD license file in the root directory of this project.

// package frc.robot.commands.NewArm;

// import frc.robot.subsystems.ArmSubsystem;

// import javax.sql.rowset.spi.SyncResolver;

// import edu.wpi.first.wpilibj.Timer;

// import edu.wpi.first.wpilibj2.command.CommandBase;
// import edu.wpi.first.wpilibj2.command.Subsystem;

// /** An example command that uses an example subsystem. */
// public class CloseArm extends CommandBase {
//   private final ArmSubsystem armSubsystem;
//    private double positionBase;
//   private double positionMid;


//   /**
//    * Creates a new ExampleCommand.
//    *
//    * @param subsystem The subsystem used by this command.
//    */
//   public CloseArm(ArmSubsystem armSubsystem, double positionMid , double positionBase) {
//     this.armSubsystem = armSubsystem;
//     this.positionBase = positionBase;
//     this.positionMid = positionMid;
    

//     // Use addRequirements() here to declare subsystem dependencies.
//     addRequirements(armSubsystem);
//   }

//   // Called when the command is initially scheduled.
//   @Override
//   public void initialize() {
    
//   }

//   // Called every time the scheduler runs while the command is scheduled.
//   @Override
//   public void execute() {
//     if ((armSubsystem.getBasePosition() < 14 && armSubsystem.getMidPosition() >  33 )|| (armSubsystem.getBasePosition() < 13 && armSubsystem.getMidPosition() > 28)){
//       // System.out.println("11111111");
//       armSubsystem.setBaseArmPosition(16);
//     }
//     else if ((armSubsystem.getBasePosition() > 13 && armSubsystem.getMidPosition() >  25)){
//       // System.out.println("2222222222");
//       armSubsystem.setMiddleArmPosition(22);
//     }    
//     else if((armSubsystem.getBasePosition() > 12 && armSubsystem.getMidPosition() <  25) ||(armSubsystem.getBasePosition() > 12 && armSubsystem.getMidPosition() >  25 )){
//       // System.out.println("3333333333");
//       armSubsystem.setArmMidAndBase(-1.5, -1.2);
//     }
 
//   }
  

//   // Called once the command ends or is interrupted.
//   @Override
  
//   public void end(boolean interrupted) {
//     armSubsystem.setArmMidAndBase(0, 2.4);
//   }

//   // Returns true when the command should end.
//   @Override
//   public boolean isFinished() {
//     return armSubsystem.isShootingDownMid();
//   }
// }

