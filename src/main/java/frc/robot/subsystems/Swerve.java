package frc.robot.subsystems;

import frc.robot.SwerveModule;
import frc.robot.commands.ResetCommand;
import frc.robot.dashboard.SuperSystem;
import frc.robot.Constants;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModulePosition;

import com.ctre.phoenix.sensors.Pigeon2;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;


public class Swerve extends SuperSystem {
    public SwerveDriveOdometry swerveOdometry;
    public SwerveModule[] mSwerveMods;
    // public Pigeon2 gyro;
    public AHRS gyro;

    public Swerve() {
        super("Swerve");
        gyro = new AHRS(SPI.Port.kMXP);
        // gyro = new Pigeon2(Constants.Swerve.pigeonID);
        // gyro.configFactoryDefault();
        zeroGyro();

        mSwerveMods = new SwerveModule[] {
            new SwerveModule(0, Constants.Swerve.Mod0.constants),
            new SwerveModule(1, Constants.Swerve.Mod1.constants),
            new SwerveModule(2, Constants.Swerve.Mod2.constants),
            new SwerveModule(3, Constants.Swerve.Mod3.constants)
        };

        // getTab().putInDashboard("Reset Sensors", new ResetCommand(this), false);

        swerveOdometry = new SwerveDriveOdometry(Constants.Swerve.swerveKinematics, getYaw(), getModulePositions());
    }

    public void drive(Translation2d translation, double rotation, boolean fieldRelative, boolean isOpenLoop) {
        SwerveModuleState[] swerveModuleStates =
            Constants.Swerve.swerveKinematics.toSwerveModuleStates(
                fieldRelative ? ChassisSpeeds.fromFieldRelativeSpeeds(
                                    translation.getX(), 
                                    translation.getY(), 
                                    rotation, 
                                    getYaw()
                                )
                                : new ChassisSpeeds(
                                    translation.getX(), 
                                    translation.getY(), 
                                    rotation)
                                );
        SwerveDriveKinematics.desaturateWheelSpeeds(swerveModuleStates, Constants.Swerve.maxSpeed);
        getTab().putInDashboard("module 0 - vel", swerveModuleStates[0].speedMetersPerSecond, false);
        getTab().putInDashboard("module 0 - angel", swerveModuleStates[0].angle.getDegrees(), false);
        // getTab().putInDashboard("BBB3", swerveModuleStates[3].angle.getDegrees(), false);
        getTab().putInDashboard("translation y", translation.getY(), false);
        getTab().putInDashboard("translation x", translation.getX(), false);
        getTab().putInDashboard("rotation", rotation, false);

        for(SwerveModule mod : mSwerveMods){
            // SwerveModule mod = mSwerveMods[3];
            // System.out.println("BBB " + mod.moduleNumber + " rotation: " +rotation +" check:"+ swerveModuleStates[mod.moduleNumber].angle);
            // System.out.println(""+rotation +" check:"+ swerveModuleStates[mod.moduleNumber].angle);
            // getTab().putInDashboard(""+rotation, swerveModuleStates[mod.moduleNumber].angle,false);
            // getTab().putInDashboard(""+rotation, swerveModuleStates[mod.moduleNumber].angle,false);
            mod.setDesiredState(swerveModuleStates[mod.moduleNumber], isOpenLoop);
        }

        // mSwerveMods[2].setDesiredState(swerveModuleStates[2], isOpenLoop);
    }    

    /* Used by SwerveControllerCommand in Auto */
    public void setModuleStates(SwerveModuleState[] desiredStates) {
        SwerveDriveKinematics.desaturateWheelSpeeds(desiredStates, Constants.Swerve.maxSpeed);
        
        for(SwerveModule mod : mSwerveMods){
            mod.setDesiredState(desiredStates[mod.moduleNumber], false);
        }
    }    

    public Pose2d getPose() {
        return swerveOdometry.getPoseMeters();
    }

    public void resetOdometry(Pose2d pose) {
        swerveOdometry.resetPosition(getYaw(), getModulePositions(), pose);
    }

    public SwerveModuleState[] getModuleStates(){
        SwerveModuleState[] states = new SwerveModuleState[4];
        for(SwerveModule mod : mSwerveMods){
            states[mod.moduleNumber] = mod.getState();
        }
        return states;
    }

    public SwerveModulePosition[] getModulePositions(){
        SwerveModulePosition[] positions = new SwerveModulePosition[4];
        for(SwerveModule mod : mSwerveMods){
            positions[mod.moduleNumber] = mod.getPosition();
        }
        return positions;
    }

    public void zeroGyro(){
        gyro.reset();
        // gyro.setYaw(0);
    }

    public Rotation2d getYaw() {
        return (Constants.Swerve.invertGyro) ? Rotation2d.fromDegrees(360 - gyro.getYaw()) : Rotation2d.fromDegrees(gyro.getYaw());
    }

    @Override
    public void periodic(){
        swerveOdometry.update(getYaw(), getModulePositions());  
        getTab().putInDashboard("encoder LF", mSwerveMods[Constants.Swerve.FL].angleEncoder.getSelectedSensorPosition(), false);
        getTab().putInDashboard("encoder LB", mSwerveMods[Constants.Swerve.BL].angleEncoder.getSelectedSensorPosition(), false);
        getTab().putInDashboard("encoder RF", mSwerveMods[Constants.Swerve.FR].angleEncoder.getSelectedSensorPosition(), false);
        getTab().putInDashboard("encoder RB", mSwerveMods[Constants.Swerve.BR].angleEncoder.getSelectedSensorPosition(), false);
        getTab().putInDashboard("encoder RB getAngle", mSwerveMods[Constants.Swerve.BR].getAngle().getDegrees(), false);
        // getTab().putInDashboard("gyro ", this.getYaw().getDegrees(), false);
        // getTab().putInDashboard("encoder LF", gyro, false);
        for(SwerveModule mod : mSwerveMods){
            SmartDashboard.putNumber("Mod " + mod.moduleNumber + " Cancoder", mod.getCanCoder().getDegrees());
            SmartDashboard.putNumber("Mod " + mod.moduleNumber + " Integrated", mod.getPosition().angle.getDegrees());
            SmartDashboard.putNumber("Mod " + mod.moduleNumber + " Velocity", mod.getState().speedMetersPerSecond);   
            SmartDashboard.putNumber("Mod " + mod.moduleNumber + " lastAngle", mod.lastAngle.getDegrees());    
        }
    }
}