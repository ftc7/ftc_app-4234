package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="TwistyTank", group="Tank")
@Disabled
public class TwistyTank extends OpMode{

    /* Declare OpMode members. */
    private hopefullymeccanum robot = new hopefullymeccanum(); // use the class created to define a Mecanobot's hardware

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("IT", "LIVES");    //
        updateTelemetry(telemetry);
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        double Ch1,Ch3,FrontLeft,BackLeft,FrontRight,BackRight;

        //Joystick drive (I have no idea what you call this control method)
        Ch1 = gamepad1.left_stick_x;
        Ch3 = -gamepad1.left_stick_y;

        FrontLeft = Ch3 + Ch1;
        BackLeft = Ch3 + Ch1;
        FrontRight = Ch3 - Ch1;
        BackRight = Ch3 - Ch1;

        robot.frontLeft.setPower(Range.clip(si(FrontLeft, 1.5), -1, 1));
        robot.frontRight.setPower(Range.clip(si(FrontRight, 1.5), -1, 1));
        robot.backLeft.setPower(Range.clip(si(BackLeft, 1.5), -1, 1));
        robot.backRight.setPower(Range.clip(si(BackRight, 1.5), -1, 1));

        telemetry.addData("frontRight", FrontRight);
        telemetry.addData("frontLeft", FrontLeft);
        telemetry.addData("backRight", BackRight);
        telemetry.addData("backLeft", BackLeft);

        updateTelemetry(telemetry);
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

    private double si(double joyVal, double divBy) {
        /**
         * This function scales the input.
         * It makes it so that when you push the joystick forward, the robot doesn't respond in a linear way.
         * When you push it a little bit, you have more precision control over speed.
         * Pushing all the way overrides the math and jumps to 1.
         */
        if(joyVal>=1) return 1;
        else if(joyVal<=-1) return -1;
        else return -Math.log(-joyVal + 1) / divBy;
    }
}
