package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.ShootingConstants;
import frc.robot.autos.AutoCommand;
// import frc.robot.autos.cubefarfromhuman;
import frc.robot.commands.*;
import frc.robot.commands.ShootingCommnads.CartridgeOutputCommand;
import frc.robot.commands.ShootingCommnads.ShootingGroupCommand;
// import frc.robot.commands.autoCommands.CenterCloseToHumanCube;
import frc.robot.commands.autoCommands.CenterFarFromHumanCube;
import frc.robot.commands.timercommand.collectAtuoCommand;
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
    public static final Limelight limelight = new Limelight.Builder().build();
    /* Drive Controls */


    /* Subsystems */
    private final Swerve s_Swerve = new Swerve();
    private final collectWheelsSubsystem m_CollectWheels = new collectWheelsSubsystem();
    private final ShootingSubsystem  m_ShootingSubsystem = new ShootingSubsystem();
    private final CollectSubsystem m_CollectSubsystem = new CollectSubsystem();
    private final CartridgeSubsystem m_cartridgeSubsystem = new CartridgeSubsystem();
    private final ArmSubsystem armSubsystem = new ArmSubsystem();
    private final GripperSubsys gripperSubsys = new GripperSubsys();

    
    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public final CenterFarFromHumanCube CenterFarFromHumanCube = new CenterFarFromHumanCube(s_Swerve,  m_CollectSubsystem, m_cartridgeSubsystem,
    m_CollectWheels, m_ShootingSubsystem, armSubsystem);
    // public final CenterCloseToHumanCube centerCloseToHumanCube = new CenterCloseToHumanCube(s_Swerve,  m_CollectSubsystem, m_cartridgeSubsystem, m_CollectWheels, m_ShootingSubsystem, armSubsystem); 
    public final ShootingGroupCommand justShoot = new ShootingGroupCommand(m_ShootingSubsystem, m_cartridgeSubsystem , Constants.SHOOTING_HIGH, armSubsystem);

    public RobotContainer() {
        // Configure the button bindings
        configureButtonBindings();
    }
    

    /**
     * Use this method to define your button->command mappings. Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
     * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        robotButtons.loadButtons(m_ShootingSubsystem, m_CollectSubsystem,  s_Swerve, m_CollectWheels, limelight, m_cartridgeSubsystem,armSubsystem,gripperSubsys);
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

    public CartridgeSubsystem getM_CartridgeSubsystem(){
        return m_cartridgeSubsystem;
    }
    public RobotButtons getRobotButtons() {
        return robotButtons;
    }
    public static Limelight getLimelight() {
        return limelight;
    }
    public collectWheelsSubsystem getM_CollectWheels() {
        return m_CollectWheels;
    }
    public CollectSubsystem getM_CollectSubsystem() {
        return m_CollectSubsystem;
    }
    public CartridgeSubsystem getM_cartridgeSubsystem() {
        return m_cartridgeSubsystem;
    }
    public ArmSubsystem getArmSubsystem() {
        return armSubsystem;
    }
    // public CenterCloseToHumanCube getCenterCloseToHumanCube() {
    //     return centerCloseToHumanCube;
    // }
    public ShootingGroupCommand getJustShoot() {
        return justShoot;
    }
    public CenterFarFromHumanCube getCenterFarFromHumanCube(){
        return CenterFarFromHumanCube;
    }
    public ShootingGroupCommand getJustShootAtuo(){
        return justShoot;
    }
    public Command getTest(){
       return AutoCommand.getAutoCommand(s_Swerve, "test", 3);
    }


    public GripperSubsys getGripperSubsys() {
        return gripperSubsys;
    }
}
 