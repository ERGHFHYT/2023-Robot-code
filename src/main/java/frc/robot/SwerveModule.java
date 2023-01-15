package frc.robot;

import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;

import frc.lib.math.Conversions;
import frc.lib.util.CTREModuleState;
import frc.lib.util.SwerveModuleConstants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.sensors.CANCoder;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxAlternateEncoder;
import com.revrobotics.SparkMaxPIDController;

public class SwerveModule {
    public int moduleNumber;
    private Rotation2d angleOffset;
    public Rotation2d lastAngle;

    private CANSparkMax mAngleMotor;
    private RelativeEncoder integratedAngleEncoder;
    private final SparkMaxPIDController angleController;

    private TalonFX mDriveMotor;
    public TalonSRX angleEncoder;
    // private RelativeEncoder angleEncoder;


    SimpleMotorFeedforward feedforward = new SimpleMotorFeedforward(Constants.Swerve.driveKS, Constants.Swerve.driveKV, Constants.Swerve.driveKA);

    public SwerveModule(int moduleNumber, SwerveModuleConstants moduleConstants){
        this.moduleNumber = moduleNumber;
        this.angleOffset = moduleConstants.angleOffset;
            // System.out.println(""+moduleNumber +" check:"+ swerveModuleStates[mod.moduleNumber].angle);

        
        /* Angle Encoder Config */
        angleEncoder = new TalonSRX(moduleConstants.cancoderID);
        configAngleEncoder(moduleConstants.canCoderInvert);

        /* Angle Motor Config */
        mAngleMotor = new CANSparkMax(moduleConstants.angleMotorID, MotorType.kBrushless);
          /* Angle Motor Config */
        integratedAngleEncoder = mAngleMotor.getEncoder();
        angleController = mAngleMotor.getPIDController();
        configAngleMotor();
        // angleEncoder = mAngleMotor.getAlternateEncoder(1);

        /* Drive Motor Config */
        mDriveMotor = new TalonFX(moduleConstants.driveMotorID);
        // mDriveMotor.getSelectedSensorPosition (1);
        configDriveMotor();

        lastAngle = getState().angle;
    }

    public void setDesiredState(SwerveModuleState desiredState, boolean isOpenLoop){
        /* This is a custom optimize function, since default WPILib optimize assumes continuous controller which CTRE and Rev onboard is not */
        desiredState = CTREModuleState.optimize(desiredState, getState().angle);
        // System.out.println("DDD desiredState: " + desiredState + " getState().angle: " + getState().angle + " isOpenLoop: " + isOpenLoop);
        setAngle(desiredState);
        setSpeed(desiredState, isOpenLoop);
    }

    private void setSpeed(SwerveModuleState desiredState, boolean isOpenLoop){
        if(isOpenLoop){
            double percentOutput = desiredState.speedMetersPerSecond / Constants.Swerve.maxSpeed;
            mDriveMotor.set(ControlMode.PercentOutput, percentOutput);
        }
        else {
            double velocity = Conversions.MPSToFalcon(desiredState.speedMetersPerSecond, Constants.Swerve.wheelCircumference, Constants.Swerve.driveGearRatio);
            mDriveMotor.set(ControlMode.Velocity, velocity, DemandType.ArbitraryFeedForward, feedforward.calculate(desiredState.speedMetersPerSecond));
        }
    }

    private void setAngle(SwerveModuleState desiredState){
        Rotation2d angle = (Math.abs(desiredState.speedMetersPerSecond) <= (Constants.Swerve.maxSpeed * 0.01)) ? lastAngle : desiredState.angle; //Prevent rotating module if speed is less then 1%. Prevents Jittering.
        // if(moduleNumber == 3){
            // Conversions.degreesToFalcon(angle.getDegrees(), Constants.Swerve.angleGearRatio);
            
        // }else
        // System.out.println("CCC1 angle: " + angle);
        // System.out.println("CCC2 desiredState.speedMetersPerSecond: " + desiredState.speedMetersPerSecond);
        // System.out.println("CCC3 setting to " + Conversions.degreesToFalcon(angle.getDegrees(), Constants.Swerve.angleGearRatio));
        // System.out.println("CCC4 get returns " + mAngleMotor.get());
        // mAngleMotor.set(Conversions.degreesToFalcon(angle.getDegrees(), Constants.Swerve.angleGearRatio));
            System.out.println("CCC5 setting " + angle.getDegrees());
            System.out.println("CCC6 get angle " + getAngle().getDegrees());
        angleController.setReference(angle.getDegrees(), ControlType.kPosition);
    
        lastAngle = angle;
    }

    

    public Rotation2d getAngle(){ //TODO: make private
        return Rotation2d.fromDegrees(Conversions.falconToDegrees(angleEncoder.getSelectedSensorPosition(), Constants.Swerve.angleGearRatio));
    }
 
    public Rotation2d getCanCoder(){
        return Rotation2d.fromDegrees(angleEncoder.getSelectedSensorPosition());
    }

    private void resetToAbsolute(){
        double absolutePosition = 0; // Conversions.degreesToFalcon(getCanCoder().getDegrees() - angleOffset.getDegrees(), Constants.Swerve.angleGearRatio);
        angleEncoder.setSelectedSensorPosition(absolutePosition);
        System.out.println(moduleNumber);
        // integratedAngleEncoder.setPosition(absolutePosition);
        // angleEncoder.setSelectedSensorPosition(0);
    }

    private void configAngleEncoder(boolean inverted){        
        angleEncoder.configFactoryDefault();
        angleEncoder.configAllSettings(Robot.ctreConfigs.swerveCanCoderConfig);

    }

    private void configAngleMotor(){
        // mAngleMotor.configFactoryDefault();
        // mAngleMotor.configAllSettings(Robot.ctreConfigs.swerveAngleFXConfig);
        mAngleMotor.setInverted(Constants.Swerve.angleMotorInvert);
        mAngleMotor.setIdleMode(Constants.Swerve.angleNeutralMode);
        integratedAngleEncoder.setPositionConversionFactor(Constants.Swerve.angleConversionFactor);
        // integratedAngleEncoder.setInverted(true);
        angleController.setP(Constants.Swerve.angleKP);
        angleController.setI(Constants.Swerve.angleKI);
        angleController.setD(Constants.Swerve.angleKD);
        angleController.setFF(Constants.Swerve.angleKF);
        resetToAbsolute();
        
    }

    private void configDriveMotor(){        
        mDriveMotor.configFactoryDefault();
        mDriveMotor.configAllSettings(Robot.ctreConfigs.swerveDriveFXConfig);
        mDriveMotor.setInverted(Constants.Swerve.driveMotorInvert);
        mDriveMotor.setNeutralMode(Constants.Swerve.driveNeutralMode);
        mDriveMotor.setSelectedSensorPosition(0);
    }

    public SwerveModuleState getState(){
        return new SwerveModuleState(
            Conversions.falconToMPS(mDriveMotor.getSelectedSensorVelocity(), Constants.Swerve.wheelCircumference, Constants.Swerve.driveGearRatio), 
            getAngle()
        ); 
    }

    public SwerveModulePosition getPosition(){
        return new SwerveModulePosition(
            Conversions.falconToMeters(mDriveMotor.getSelectedSensorPosition(), Constants.Swerve.wheelCircumference, Constants.Swerve.driveGearRatio), 
            getAngle()
        );
    }
}