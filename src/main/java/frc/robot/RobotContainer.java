package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import frc.robot.autos.*;
import frc.robot.commands.*;
import frc.robot.commands.timercommand.TimerArmPosition;
import frc.robot.commands.timercommand.TimerGripperCommand;
import frc.robot.commands.timercommand.collectAtuoCommand;
import frc.robot.commands.timercommand.openInParallel;
import frc.robot.subsystems.*;
import frc.util.vision.Limelight;
import frc.util.vision.Limelight.limelightStreamMode;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    /* Controllers */
    private RobotButtons robotButtons = new RobotButtons();
    public Limelight limelight = new Limelight.Builder().build();
    /* Drive Controls */
    private final TestAuto testAuto = new TestAuto(); 


    /* Subsystems */
    private final Swerve s_Swerve = new Swerve();
    private final collectWheels m_CollectWheels = new collectWheels();
    private final ShootingSubsystem  m_ShootingSubsystem = new ShootingSubsystem();
    private final CollectSubsystem m_CollectSubsystem = new CollectSubsystem();
    private final GripperSubsystem m_GripperSubsystem = new GripperSubsystem();
    private final armSubsystem m_ArmSubsystem = new armSubsystem();

    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    // private final atuo1 atuo = new atuo1(s_Swerve, m_ArmSubsystem,  m_CollectSubsystem, ShootingSubsystem);
    public RobotContainer() {

        // Configure the button bindings
        configureButtonBindings();
        
    }

    private void configureSwerveButtons() {
        
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
     * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        configureSwerveButtons();
        robotButtons.loadButtons(m_ShootingSubsystem, m_CollectSubsystem, m_ArmSubsystem, s_Swerve, m_CollectWheels, limelight,  m_GripperSubsystem);
    }
 
    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous
        return null; // atuo;
    }

    // gets & sets 
    public Swerve getS_Swerve() {
        return s_Swerve;
    }

    public ShootingSubsystem getM_ShootingSubsystem() {
        return m_ShootingSubsystem;
    }

    public CollectSubsystem getM_CollectSubsyste() {
        return m_CollectSubsystem;
    }

    public armSubsystem getM_ArmSubsystem() {
        return m_ArmSubsystem;
    }
}
 