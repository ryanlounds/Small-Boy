package org.usfirst.frc.team1337.robot.subsystems;

import org.usfirst.frc.team1337.robot.Robot;
import org.usfirst.frc.team1337.robot.RobotMap;
import org.usfirst.frc.team1337.robot.commands.JoystickDrive;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *
 */
public class DriveSubsystem extends Subsystem {
	Compressor comp = new Compressor();
	final double MAX_SPEED = 0.8;
	VictorSP rightMaster, leftMaster;
	SpeedControllerGroup leftDrive, rightDrive;
	DifferentialDrive myDrive;
	PIDController encoderPID;
	double speed;
	// needs to be tuned
	public double reverse = 1;

	public DriveSubsystem() {
		rightMaster = new VictorSP(RobotMap.RIGHT_DRIVE_MASTER);
		leftMaster = new VictorSP(RobotMap.LEFT_DRIVE_MASTER);


		leftDrive = new SpeedControllerGroup(leftMaster);
		rightDrive = new SpeedControllerGroup(rightMaster);

		myDrive = new DifferentialDrive(leftDrive, rightDrive);
		rightDrive.setInverted(false);
		leftDrive.setInverted(true);

		
	}

	public void joystickDrive(double x, double y) {
				
		/*if (Robot.oi.joystick1.getRawButtonPressed(10))
			reverse *= -1;*/
		if(Robot.oi.joystick2.getRawButton(6)) { //trigger off board compressor on/off
			comp.start();
		}else {
			comp.stop();
		}
		myDrive.curvatureDrive(y * reverse, x, true);
		

	}

	public void initDefaultCommand() {
		setDefaultCommand(new JoystickDrive());
	}

	public void arcadeDrive(double speed, double rotate) {
		myDrive.arcadeDrive(speed, rotate);
	}
	
	public void stopMotors() {
		arcadeDrive(0,0);
	}
	
	public void Setpoint(double ticks) {
		encoderPID.setSetpoint(ticks);
	}

	public double scaleSpeedPID() {
		return Math.max(Math.min(MAX_SPEED, speed), -MAX_SPEED);
	}

	public void resetEncPID() {
		encoderPID.reset();
	}

	public boolean encOnTarget() {
		return encoderPID.onTarget();
	}

	public void disableEncPID() {
		encoderPID.disable();
	}


	public void enableEncPID() {
		encoderPID.enable();
	}

}
