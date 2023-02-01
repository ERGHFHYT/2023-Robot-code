package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;


// Yteam loadButtons
public class RobotButtons {

    public RobotButtons(Joystick driver, Joystick systems) {
        Trigger halfSpeed = new Trigger(() -> driver.getRawButton(XboxController.Button.kX.value));
        Trigger collect = new Trigger(() -> systems.getRawAxis(XboxController.Axis.kLeftTrigger.value) > 0.02);

    }

    public void loadButtons() {
        // load buttons
        
        }
    }

