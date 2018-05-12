package org.usfirst.frc.team254.robot;


import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public static XboxController joystick1 = new XboxController(0);

	public static boolean getQuickTurn(){
		return joystick1.getBumper(GenericHID.Hand.kLeft);
	}

	public static double getYSpeed(){
		return joystick1.getY(GenericHID.Hand.kLeft);
	}

	public static double getXSpeed(){
		return -joystick1.getX(GenericHID.Hand.kRight);
	}

	public OI(){
	}
	
}
