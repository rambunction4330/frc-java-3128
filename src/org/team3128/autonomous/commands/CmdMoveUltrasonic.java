package org.team3128.autonomous.commands;

import org.team3128.Log;
import org.team3128.autonomous.AutoUtils;
import org.team3128.drive.TankDrive;
import org.team3128.hardware.ultrasonic.IUltrasonic;
import org.team3128.util.RobotMath;

import edu.wpi.first.wpilibj.command.Command;

/*        _
 *       / \ 
 *      / _ \
 *     / [ ] \
 *    /  [_]  \
 *   /    _    \
 *  /    (_)    \
 * /_____________\
 * -----------------------------------------------------
 * UNTESTED CODE!
 * This class has never been tried on an actual robot.
 * It may be non or partially functional.
 * Do not make any assumptions as to its behavior!
 * And don't blink.  Not even for a second.
 * -----------------------------------------------------*/

/**
 * Command to move forward or backward to a certain ultrasonic distance.
 */
public class CmdMoveUltrasonic extends Command {

	double _cm;
	
	int _msec;
	
	double _threshold;
	
	long startTime;
	
	IUltrasonic ultrasonic;
	
	TankDrive drivetrain;
	
	double kP, kI;
	
	double errorSum;
	double lastError;
	/**
	 * @param cm how far on the ultrasonic to move.
	 * @param threshold acceptible threshold from desired distance in cm
	 * @param msec How long the move should take. If set to 0, do not time the move
	 */
    public CmdMoveUltrasonic(TankDrive drivetrain, IUltrasonic ultrasonic, double cm, double threshold, int msec, double kP, double kI)
    {
    	_cm = cm;
    	
    	if(cm < 0)
    	{
    		throw new IllegalArgumentException("Distance cannot be negative!");
    	}
    	
    	_msec = msec;
    	this.kP = kP;
    	this.kI = kI;
    	
    	this.drivetrain = drivetrain;
    	this.ultrasonic = ultrasonic;
    }

    protected void initialize()
    {
		drivetrain.clearEncoders();
		startTime = System.currentTimeMillis();
		
		errorSum = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute()
    {
		if(_msec != 0 && System.currentTimeMillis() - startTime >_msec)
		{
			drivetrain.stopMovement();
			AutoUtils.killRobot("Move Overtime");
		}
				
		double error = ultrasonic.getDistance() - _cm;
		errorSum += error;
		
		double output = RobotMath.clampDouble(error * kP + errorSum * kP, -.6, .6);
		
		lastError = error;
		Log.debug("CmdMoveUltrasonic", "Error: " + error);
		
		drivetrain.tankDrive(output, output);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished()
    {
        return false;//(lastError) < _threshold;
    }

    // Called once after isFinished returns true
    protected void end()
    {
		drivetrain.stopMovement();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted()
    {
    	
    }
}
