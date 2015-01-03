package org.team3128;

import edu.wpi.first.wpilibj.IterativeRobot;

/*
 * THIS FILE SHOULD NOT BE MODIFIED
 * --------------------------------
 * It serves as a link to the Global class
 * Events triggered here will be forwarded to the Global class
 *
 * Do not call these functions under any circumstances. Do not modify this
 * class under any circumstances.
 *
 * AUTOGENERATED. DO NOT EDIT UNDER PENALTY OF 42.
 *
 * THIS FILE IS YOUR SOUL.
 */

public class RobotTemplate extends IterativeRobot 
{
	Global global;
	
    public void robotInit()
    {
        Log.info("Global", "Welcome to the FRC Team 3128 No-Longer-Event System version 3!");
        
        global = new Global();
        
        global.initializeRobot();
    }

    public void disabledInit()
    {
    	global.initializeDisabled();
    }

    // ARE YOU CHANGING THINGS?
   
    boolean autonomousHasBeenInit = false;
    public void autonomousInit()
    {
        if(!autonomousHasBeenInit) {
            global._listenerManager.removeAllListeners();
            global.initializeAuto();
            autonomousHasBeenInit = true;
            teleopHasBeenInit = false;
        }
    }
   
    // TURN BACK NOW.
    // YOUR CHANGES ARE NOT WANTED HERE.
   
    boolean teleopHasBeenInit = false;
    public void teleopInit() {
        if(!teleopHasBeenInit) {
            global._listenerManager.removeAllListeners();
            global.initializeTeleop();
            teleopHasBeenInit = true;
            autonomousHasBeenInit = false;
        }
    }
   
    public void disabledPeriodic()
    {
        try
		{
			Thread.sleep(100);
		}
        catch (InterruptedException e)
		{
			return;
		}
    }

    // YOU'D BETTER NOT CHANGE ANYTHING
   
    public void autonomousPeriodic()
    {   
        try
		{
			Thread.sleep(100);
		}
        catch (InterruptedException e)
		{
			return;
		}
    }

    // DO YOU REALLY WANT TO MODIFY YOUR SOUL?
   
    public void teleopPeriodic()
    {        
        global._listenerManager.tick();
    }
}

