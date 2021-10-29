/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import frc.robot.Subsystems.CameraServerGroup; // current sensing, pathweaver 
import frc.robot.Subsystems.Map; // https://blog.alexbeaver.com/wpilib-trajectory-guide/
import edu.wpi.first.wpilibj.util.Color; //  ^-- for pathweaver

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  // Other classes
  private Map map;
  private CameraServerGroup cameras;

  // Variables for this class
  private boolean isIntakeOn;
  private boolean intakedebounce;
  private boolean wasSwitchHitLastTime;
  private int ballsInHopper;
  private boolean isFindingColor;

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    map = new Map();

    // Startup cameras running
    cameras = new CameraServerGroup();

    isIntakeOn = false;
    intakedebounce = false;
    wasSwitchHitLastTime = false;
    ballsInHopper = -1; // Need to change
    isFindingColor = false;
  }

  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {
    map.chassis().arcadeDrive(0.4, 0.0);
  }

  @Override
  public void teleopInit() {
  }

  @Override
  public void teleopPeriodic() {

    boolean toggleIntake = map.controller().getXButtonPressed();
    System.out.println(toggleIntake);
    boolean drawbridgeUp = map.controller().getBumper(Hand.kLeft);
    boolean drawbridgeDown = map.controller().getBumper(Hand.kRight);
    boolean startSpinnerButton = map.controller().getYButtonPressed();
    boolean spinnerArmUp = map.controller().getAButton(); // Plan: toggle to go all the way up (true) and all the way
                                                          // down (false)
    boolean spinnerArmDown = map.controller().getBButton(); //
    boolean spinnerWheelLeft = map.controller().getBumperPressed(Hand.kLeft);
    boolean spinnerWheelRight = map.controller().getBumperPressed(Hand.kRight);

    // Drives the robot based on joystick
    map.chassis().arcadeDrive(-map.controller().getRawAxis(Map.Y_AXIS), map.controller().getRawAxis(Map.X_AXIS));

    // Toggle intake on and off if x button pressed
    if (toggleIntake) {
      if (!intakedebounce) {
        intakedebounce = true;
        if (isIntakeOn) { // If intake is currently on, turn off
          map.intake().set(ControlMode.PercentOutput, 0.0);
          isIntakeOn = false;
        } else { // If intake is currently off, turn on
          map.intake().set(ControlMode.PercentOutput, 0.25); // Speeds may need to change
          isIntakeOn = true;
        }
      }
    } else {
      intakedebounce = false;
    }

    // Move drawbridge up and down with left and right bumpers
    if (drawbridgeUp) {
      if (!map.bridgeUpper().get()) {
        map.drawbridge().set(ControlMode.PercentOutput,0.1);
      }
      System.out.println("up bridge");
      map.stopControllerRumble();
    } else if (drawbridgeDown) {
      if (!map.bridgeLower().get()) {
        map.drawbridge().set(ControlMode.PercentOutput, -0.1); // Speeds may need to change
      
      }
      map.stopControllerRumble();
    } else { // If no bumpers pressed, stop the drawbridge moving
      map.drawbridge().set(ControlMode.PercentOutput, 0.0);
      map.stopControllerRumble();
    }
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }

}
