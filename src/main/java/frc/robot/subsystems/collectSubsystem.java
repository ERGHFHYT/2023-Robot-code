package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import frc.util.SuperSystem;
import frc.util.motor.SuperVictorSP;

public class collectSubsystem extends SuperSystem {
    DutyCycleEncoder encoder;
    SuperVictorSP motor = new SuperVictorSP(0, encoder, encoder.getDistancePerRotation());


    public collectSubsystem (SuperVictorSP motor){
        super("collect");
        this.motor = motor;
    }

    public void periodic(){
        
    }

    public void setOutput(double output) {
        motor.set(output);
    }
}