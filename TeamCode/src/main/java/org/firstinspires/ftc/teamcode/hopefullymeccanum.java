package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;

/**
 * This is NOT an opmode.
 *
 * This class is the hardware map of the robot.
 */
public class hopefullymeccanum
{
    /* Public OpMode members. */
    public DcMotor frontLeft = null;
    public DcMotor frontRight = null;
    public DcMotor backLeft = null;
    public DcMotor backRight = null;
    public DcMotor elevator = null;
    public DcMotor flinger = null;
    public Servo mounter = null;
    public Servo liftShove = null;
    //public ColorSensor colorSense = null;
    public DcMotor lifter = null;
    public Servo liftdrop = null;

    /* local OpMode members. */
    HardwareMap hwMap           =  null;
    //private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public hopefullymeccanum(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        frontLeft   = ahwMap.dcMotor.get("frontLeft");
        frontRight  = ahwMap.dcMotor.get("frontRight");
        backLeft    = ahwMap.dcMotor.get("backLeft");
        backRight   = ahwMap.dcMotor.get("backRight");
        elevator    = ahwMap.dcMotor.get("elevator");
        flinger     = ahwMap.dcMotor.get("flinger");
        mounter     = ahwMap.servo.get("mounter");
        liftShove   = ahwMap.servo.get("liftShove");
        //colorSense  = ahwMap.colorSensor.get("colorSense");
        lifter      = ahwMap.dcMotor.get("lifter");
        liftdrop    = ahwMap.servo.get("liftdrop");

        /*frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);*/
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);

        // Set all motors to zero power
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
        elevator.setPower(0);
        flinger.setPower(0);

        // Set all motors to run using encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        elevator.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        flinger.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lifter.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lifter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //lifter.setTargetPosition(0);

        mounter.setPosition(0);
        liftdrop.setPosition(0);
        liftShove.setPosition(0);
    }

    /***
     *
     * waitForTick implements a periodic delay. However, this acts like a metronome with a regular
     * periodic tick.  This is used to compensate for varying processing times for each cycle.
     * The function looks at the elapsed cycle time, and sleeps for the remaining time interval.
     *
     * @param periodMs  Length of wait cycle in mSec.
     * @throws InterruptedException
     */
    /*public void waitForTick(long periodMs) throws InterruptedException {

        long  remaining = periodMs - (long)period.milliseconds();

        // sleep for the remaining portion of the regular cycle period.
        if (remaining > 0)
            Thread.sleep(remaining);

        // Reset the cycle clock for the next pass.
        period.reset();
    }*/
}
