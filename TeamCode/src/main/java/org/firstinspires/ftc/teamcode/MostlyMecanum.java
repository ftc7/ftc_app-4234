package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="MostlyMecanum", group="Mecanum")
public class MostlyMecanum extends OpMode{

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

        telemetry.addData("Robot", "ready");
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
        double Ch1,Ch3,Ch4,FrontLeft,BackLeft,FrontRight,BackRight;

        //Mecanum drive
        Ch1 = gamepad1.right_stick_x;
        Ch3 = -gamepad1.left_stick_y;
        Ch4 = gamepad1.left_stick_x;

        FrontLeft = Ch3 + Ch1 + Ch4;
        BackLeft = Ch3 + Ch1 - Ch4;
        FrontRight = Ch3 - Ch1 - Ch4;
        BackRight = Ch3 - Ch1 + Ch4;

        robot.frontLeft.setPower(Range.clip(si(FrontLeft, 1.5), -1, 1));
        robot.frontRight.setPower(Range.clip(si(FrontRight, 1.5), -1, 1));
        robot.backLeft.setPower(Range.clip(si(BackLeft, 1.5), -1, 1));
        robot.backRight.setPower(Range.clip(si(BackRight, 1.5), -1, 1));

        if(gamepad2.left_bumper || gamepad1.left_bumper){
            robot.elevator.setPower(-1);
        }else if(gamepad2.back || gamepad1.back){
            robot.elevator.setPower(1);
        }
        else{
            robot.elevator.setPower(0);
        }
        if(gamepad2.right_bumper || gamepad1.right_bumper){
            robot.flinger.setPower(1);
        }
        else{
            robot.flinger.setPower(0);
        }

        if(gamepad2.dpad_up || gamepad1.dpad_up){
            robot.mounter.setPosition(0.7);
        }
        else if(gamepad2.dpad_down || gamepad1.dpad_down){
            robot.mounter.setPosition(0);
        }
        if(gamepad2.x || gamepad1.x){
            robot.buttonPress.setPosition(0.2);
        }
        else if(gamepad2.b || gamepad1.b){
            robot.buttonPress.setPosition(0.8);
        }else{
            robot.buttonPress.setPosition(0.5);
        }

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
