package frc.robot;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import javax.swing.GroupLayout.Group;

import com.fasterxml.jackson.databind.ser.std.BooleanSerializer;

import edu.wpi.first.networktables.PubSub;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.resetCommand;
import frc.robot.commands.ArmCommands.ArmCollectCommand;
import frc.robot.commands.ArmCommands.armCollectOutput;
import frc.robot.commands.ShootingCommnads.ShootingCommand;
import frc.robot.commands.ShootingCommnads.ShootingDownGroupCommand;
import frc.robot.commands.ShootingCommnads.ShootingFixercommand;
import frc.robot.commands.ShootingCommnads.ShootingGroupCommand;
import frc.robot.commands.SwereCommands.BalanceCommand;
import frc.robot.commands.SwereCommands.LeftGRIDmovmentCommand;
import frc.robot.commands.SwereCommands.LimelightCommand;
import frc.robot.commands.SwereCommands.TeleopSwerve;
import frc.robot.commands.SwereCommands.TurnToZeroCommand;
import frc.robot.commands.SwereCommands.lockWheelsCommnad;
import frc.robot.commands.SwereCommands.rightGRIDmovmentCommand;
import frc.robot.commands.IntakeCommands.OpenIntakeAndArm;
import frc.robot.commands.IntakeCommands.collectWheelsCommand;
import frc.robot.commands.IntakeCommands.setPointCollectCommand;
import frc.robot.commands.NewArm.ArmHuman;
import frc.robot.commands.NewArm.CloseArm;
import frc.robot.commands.NewArm.GipperCommand;
import frc.robot.commands.NewArm.NewArmCommand;
import frc.robot.commands.ShootingCommnads.CartridgeOutputCommand;
import frc.robot.commands.ShootingCommnads.CubeFixtureGroupCommand;
import frc.robot.commands.resetCommand;
import frc.robot.subsystems.CollectSubsystem;
import frc.robot.subsystems.GripperSubsys;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.CartridgeSubsystem;
import frc.robot.subsystems.Swerve;
import frc.robot.subsystems.armCollectSubsystem;
// import frc.robot.subsystems.armCollectSubsystem;
import frc.robot.subsystems.collectWheelsSubsystem;
import frc.robot.subsystems.shootingSubsystem;
import frc.util.vision.Limelight;
// import frc.robot.commands.Balance;


// Yteam loadButtons
public class RobotButtons {
    public static BooleanSupplier GRIDmovmentHelper = (() -> true);
    

    public static Joystick systems = new Joystick(1);
    public static Joystick driver = new Joystick(0);
    // driver jpoystick buttons
    public static DoubleSupplier BreakValue = () -> driver.getRawAxis(XboxController.Axis.kRightTrigger.value);
    public Trigger resetGyro = new Trigger(() -> driver.getRawButton(XboxController.Button.kLeftBumper.value));
    public static Trigger Balance = new Trigger(() -> driver.getRawAxis(XboxController.Axis.kLeftTrigger.value) > 0.3);
    public Trigger stop = new Trigger(() -> driver.getRawButton(XboxController.Button.kBack.value));
    public Trigger LimelightAprilTag = new Trigger(() -> driver.getRawButton(XboxController.Button.kB.value));
    public Trigger TurnToZero = new Trigger(() -> driver.getRawButton(XboxController.Button.kA.value));
    public Trigger LimelightRetroReflective = new Trigger(() -> driver.getRawButton(XboxController.Button.kX.value));
    public static Trigger halfSpeed = new Trigger(() -> driver.getRawAxis(XboxController.Axis.kRightTrigger.value) > 0.3);
    public static Trigger forwardJoystick = new Trigger(() -> Math.abs(driver.getRawAxis(XboxController.Axis.kLeftY.value)) > 0.1);
    public static Trigger sidesJoystick = new Trigger(() -> Math.abs(driver.getRawAxis(XboxController.Axis.kLeftX.value)) > 0.1);
    public static Trigger rotationJoystick = new Trigger(() -> Math.abs(driver.getRawAxis(XboxController.Axis.kRightX.value)) > 0.1);
    public static Trigger rightGRIDmovment = new Trigger(() -> driver.getPOV() == 90);
    public static Trigger leftGRIDmovment = new Trigger(() -> driver.getPOV() == 270);
    public Trigger testShooting = new Trigger(() -> driver.getPOV() == 0);

    public Trigger WheelsLock = new Trigger(() -> driver.getRawButton(XboxController.Button.kStart.value));

    // systems joystick buttons
    public Trigger OpenCollect = new Trigger(() -> systems.getRawAxis(XboxController.Axis.kLeftTrigger.value) > 0.3);
    public Trigger OpenCollectTest = new Trigger(() -> !(systems.getRawAxis(XboxController.Axis.kLeftTrigger.value) > 0.3));
    public Trigger collectWheelsBack = new Trigger(() -> systems.getRawButton(XboxController.Button.kStart.value));
    public Trigger shootingLow = new Trigger(() -> systems.getPOV() == 180);
    public Trigger shootingHigh = new Trigger(() -> systems.getPOV() == 0);
    // public Trigger shootingHighMeow = new Trigger(() -> !(systems.getPOV() == 0));
    public Trigger shootingFixture = new Trigger(() -> systems.getPOV() == 270);
    public Trigger shootinghMid = new Trigger(() -> systems.getPOV() == 90);
    // public Trigger openArmCollect = new Trigger(() -> systems.getRawButton(XboxController.Button.kY.value));
    public Trigger gripperTriggerOut = new Trigger(() -> systems.getRawButton(XboxController.Button.kX.value));
    public Trigger reverseShooterTrigger = new Trigger(() -> systems.getRawButton(XboxController.Button.kRightBumper.value));
    public static Trigger forwardShooterTrigger = new Trigger(() -> systems.getRawButton(XboxController.Button.kLeftBumper.value));
    public Trigger gripperTriggerIn = new Trigger(() -> systems.getRawButton(XboxController.Button.kB.value));
    public Trigger SeconderyResetTrigger = new Trigger(() -> systems.getRawButton(XboxController.Button.kBack.value));
    public Trigger restArm = new Trigger(() -> systems.getRawButton(XboxController.Button.kA.value));
    public Trigger testArmTrigger = new Trigger(() -> systems.getRawButton(XboxController.Button.kY.value));
    public Trigger collectCone = new Trigger(() -> systems.getRawAxis(XboxController.Axis.kRightTrigger.value) > 0.3);
    

    /**
     * @param shootingSubsystem
     * @param collectSubsyste
     * @param armSubsystem
     * @param swerve
     */
    public void loadButtons(shootingSubsystem shootingSubsystem, CollectSubsystem collectSubsystem,
             Swerve swerve,collectWheelsSubsystem collectWheels, Limelight limelight, armCollectSubsystem armCollectSubsystem,CartridgeSubsystem cartridgeSubsystem, ArmSubsystem armSubsystem, GripperSubsys gripperSubsys) {
        // driver joystick commands
        swerve.setDefaultCommand(
            new TeleopSwerve(
                    swerve,
                    () -> driver.getRawAxis(XboxController.Axis.kLeftY.value),
                    () -> driver.getRawAxis(XboxController.Axis.kLeftX.value),
                    () -> driver.getRawAxis(XboxController.Axis.kRightX.value)
                    ));
        resetGyro.onTrue(new InstantCommand(() -> swerve.zeroGyro()));
        LimelightAprilTag.whileTrue(new LimelightCommand(limelight, swerve, true, -1, 1));
        LimelightRetroReflective.whileTrue(new LimelightCommand(limelight, swerve, false, 14, 1));
        Balance.onTrue(new BalanceCommand(swerve, false));
        rightGRIDmovment.and(GRIDmovmentHelper).onTrue(new rightGRIDmovmentCommand(swerve));
        leftGRIDmovment.and(GRIDmovmentHelper).onTrue(new LeftGRIDmovmentCommand(swerve));
        TurnToZero.whileTrue(new TurnToZeroCommand(swerve));
        WheelsLock.onTrue(new lockWheelsCommnad(swerve));
        TurnToZero.onTrue(new TurnToZeroCommand(swerve));
        // testShooting.onTrue(new ShootingGroupCommand(shootingSubsystem, armCollectSubsystem, cartridgeSubsystem , Constants.ARM_OPEN_POSITION, 0 , 0.7, 0.9));
        // LimelightRetroReflectiveFloor.whileTrue(new LimelightCommand(limelight, swerve, false, 8));


        // systems joystick commands

        OpenCollect.whileTrue(new OpenIntakeAndArm(collectSubsystem, collectWheels, armSubsystem, Constants.COLLECT_WHEELS_OUTPUT, Constants.CENTERING_WHEELS_OUTPUT, Constants.COLLECT_OPEN_POSITION, -30, 15));
        // whileTrue(new OpenIntakeAndArm(collectSubsystem, collectWheels, armCollectSubsystem, Constants.COLLECT_WHEELS_OUTPUT, Constants.CENTERING_WHEELS_OUTPUT, Constants.COLLECT_OPEN_POSITION, Constants.ARM_OPEN_POSITION));
        collectWheelsBack.whileTrue(new OpenIntakeAndArm(collectSubsystem, collectWheels, armSubsystem, 0.7, 0.15, Constants.COLLECT_OPEN_POSITION, -30, 15));
        // .whileTrue(new collectWheelsCommand(collectWheels, 0.7, 0.5));
        
        
        shootingHigh.onTrue(new ShootingGroupCommand(shootingSubsystem, armCollectSubsystem, cartridgeSubsystem, Constants.SHOOTING_HIGH, armSubsystem));
        shootinghMid.onTrue(new ShootingGroupCommand(shootingSubsystem, armCollectSubsystem, cartridgeSubsystem , Constants.SHOOTING_MID, armSubsystem));
        shootingLow.onTrue(new ShootingGroupCommand(shootingSubsystem, armCollectSubsystem, cartridgeSubsystem , Constants.SHOOTING_LOW, armSubsystem));
        shootingFixture.whileTrue(new ShootingFixercommand(cartridgeSubsystem, 0.15, 200, -0.1, 20));
        // shootingFixture.onTrue(new CubeFixtureGroupCommand(cartridgeSubsystem, 0, 0.15, 1400, -0.2, 20));
        reverseShooterTrigger.whileTrue(new ShootingCommand(shootingSubsystem, cartridgeSubsystem, armCollectSubsystem,-0.3, 0));
        forwardShooterTrigger.whileTrue(new ShootingCommand(shootingSubsystem, cartridgeSubsystem, armCollectSubsystem,0.5, 0));
        
        collectCone.onTrue(new ArmHuman(armSubsystem, -34, 12.5, 0));
        testArmTrigger.onTrue(new NewArmCommand(armSubsystem, -15.5, 15, 0));
        // testArmTrigger.onTrue(new NewArmCommand(armSubsystem, -30, 15, 0));
        restArm.onFalse(new CloseArm(armSubsystem, 0, 0));
        gripperTriggerIn.whileTrue(new GipperCommand(gripperSubsys, 0.5));
        gripperTriggerOut.whileTrue(new GipperCommand(gripperSubsys, -0.5));
        // openArmCollect.onTrue(new InstantCommand(() -> armCollectSubsystem.setArmCollectPosition(Constants.ARM_OPEN_POSITION)));
        // closeArmCollect.onTrue(new InstantCommand(() -> armCollectSubsystem.setArmCollectPosition(0)));
        
        
        // resetTrigger.and(SeconderyResetTrigger).onTrue(new resetCommand(shootingSubsystem, collectSubsystem, armCollectSubsystem, cartridgeSubsystem));
        
        // OpenCollect.whileFalse(new IntakeAndArm(collectSubsystem, collectWheels, armCollectSubsystem, 0, 0, 0, 0));
        
  
         
    }
}
