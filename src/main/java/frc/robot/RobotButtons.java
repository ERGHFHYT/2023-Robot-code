package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.TeleopSwerve;
import frc.robot.subsystems.Swerve;


// Yteam loadButtons
public class RobotButtons {
    public static Joystick driver = new Joystick(0);
    public static Joystick systems = new Joystick(1);
    public final Trigger robotCentric = new Trigger(() -> driver.getRawButton(XboxController.Button.kLeftBumper.value));
    public final Trigger halfSpeed = new Trigger(() -> driver.getRawButton(XboxController.Button.kX.value));
    private final Trigger zeroGyro = new Trigger(() -> driver.getRawButton(XboxController.Button.kY.value));

    public void loadButtons(Swerve swerve) {
        swerve.setDefaultCommand(
            new TeleopSwerve(
                swerve, 
                // () -> driver.getRawAxis(XboxController.Axis.kLeftY.value), // up & down
                () -> driver.getRawAxis(0), // up & down
                // () -> driver.getRawAxis(XboxController.Axis.kLeftX.value),  // left & right 
                () -> driver.getRawAxis(1),  // left & right 
                () -> driver.getRawAxis(XboxController.Axis.kRightX.value), // rotation
                () -> robotCentric.getAsBoolean()
            )
        );

        zeroGyro.onTrue(new InstantCommand(() -> swerve.zeroGyro()));
    }
}