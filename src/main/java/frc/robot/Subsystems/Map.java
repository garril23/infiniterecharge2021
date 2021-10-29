package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * All Robot Objects declared here - no logic, just declarations
 */
public class Map {

    // Axis IDS

    public static final int X_AXIS = 0;
    public static final int Y_AXIS = 1;

    // Robot Objects

    private WPI_TalonSRX frontLeft;
    private WPI_TalonSRX frontRight;
    private WPI_TalonSRX backLeft;
    private WPI_TalonSRX backRight;

    private SpeedControllerGroup leftTrain;
    private SpeedControllerGroup rightTrain;

    private DifferentialDrive chassis;

    private VictorSPX intake;
    private VictorSPX rackAndPinion;
    private VictorSPX drawbridge;



    // Robot Object IDs

    private final int FRONT_LEFT_ID = 0;
    private final int FRONT_RIGHT_ID = 2;
    private final int BACK_LEFT_ID = 1;
    private final int BACK_RIGHT_ID = 3;


    private final int INTAKE_ID = 7;
    
    private final int DRAWBRIDGE_ID = 6;

    private final int RACK_AND_PINION_ID = 5;

    // Joystick Object and ID

    private XboxController controller;

    private final int CONTROLLER_ID = 0;

    // Ball counter

    private DigitalInput ballLimitSwitch;

    private final int BALL_SWTICH_ID = 0;

    // Spinner Arm Limit Switches

    private DigitalInput armUpperLimit;
    private DigitalInput armLowerLimit;

    private int ARM_UPPER_ID = 1;
    private int ARM_LOWER_ID = 2;

    // Drawbridge Limit Switches

    private DigitalInput drawbridgeUpperLimit;
    private DigitalInput drawbridgeLowerLimit;

    private int BRIDGE_UPPER_ID = 3;
    private int BRIDGE_READY_ID = 4;
    private int BRIDGE_LOWER_ID = 5;



    // Spinner

   // private Spinner spinner;

    public Map() {

        frontLeft = new WPI_TalonSRX(FRONT_LEFT_ID);
        frontRight = new WPI_TalonSRX(FRONT_RIGHT_ID);
        backLeft = new WPI_TalonSRX(BACK_LEFT_ID);
        backRight = new WPI_TalonSRX(BACK_RIGHT_ID);

        leftTrain = new SpeedControllerGroup(frontLeft, backLeft);
        rightTrain = new SpeedControllerGroup(frontRight, backRight);

        chassis = new DifferentialDrive(leftTrain, rightTrain);

        intake = new VictorSPX(INTAKE_ID);

        drawbridge = new VictorSPX(DRAWBRIDGE_ID);

        controller = new XboxController(CONTROLLER_ID);

        ballLimitSwitch = new DigitalInput(BALL_SWTICH_ID);

        rackAndPinion = new VictorSPX(RACK_AND_PINION_ID);

        //spinner = new Spinner();

        drawbridgeLowerLimit = new DigitalInput(BRIDGE_LOWER_ID);
        drawbridgeUpperLimit = new DigitalInput(BRIDGE_UPPER_ID);

        armUpperLimit = new DigitalInput(ARM_UPPER_ID);
        armLowerLimit = new DigitalInput(ARM_LOWER_ID);
    }

    public DifferentialDrive chassis() {
        return this.chassis;
    }

    public VictorSPX intake() {
        return this.intake;
    }

    public VictorSPX drawbridge() {
        return this.drawbridge;
    }

    public XboxController controller() {
        return this.controller;
    }

    public DigitalInput ballLimitSwitch() {
        return this.ballLimitSwitch;
    }

    //public Spinner spinner() {
    //    return this.spinner;
    //}

    public DigitalInput armLower() {
        return this.armLowerLimit;
    }

    public DigitalInput armUpper() {
        return this.armUpperLimit;
    }

    public DigitalInput bridgeLower() {
        return this.drawbridgeLowerLimit;
    }

    public DigitalInput bridgeUpper() {
        return this.drawbridgeUpperLimit;
    }

    public void rumbleController() {
        this.controller().setRumble(RumbleType.kLeftRumble, 0.25);
        this.controller().setRumble(RumbleType.kRightRumble, 0.25);
    }

    public void stopControllerRumble() {
        this.controller().setRumble(RumbleType.kLeftRumble, 0.0);
        this.controller().setRumble(RumbleType.kRightRumble, 0.0);
    }


}