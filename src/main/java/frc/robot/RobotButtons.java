// package frc.robot;

// import edu.wpi.first.wpilibj.Joystick;
// import edu.wpi.first.wpilibj2.command.button.Trigger;
// import frc.robot.commands.TeleopSwerve;
// import frc.robot.subsystems.Swerve;


// // Yteam loadButtons
// public class RobotButtons {

//     // Joysticks:
//     public static Joystick driverJoystick = new Joystick(0);

//     // Triggers:
//     public Trigger ExampleButton = new Trigger(() -> driverJoystick.getRawButton(0));

//     private Swerve s_Swerve;  

//     public void loadButtons(Swerve swerve1) {
//         // Triggers active
//         ExampleButton.whileTrue( new TeleopSwerve(
//             , 
//             () -> driver.getRawAxis(translationAxis), 
//             () -> -driver.getRawAxis(strafeAxis), 
//             () -> driver.getRawAxis(rotationAxis), 
//             () -> robotCentric.getAsBoolean()
//         ));
//     }
// }