package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name="ThrowTraveless", group="Throw")
public class flingautodrive extends LinearOpMode {

    private hopefullymeccanum robot = new hopefullymeccanum();

    /*public flingautodrive() {       //Called upon invocation of the class; defines variables and initializes Vuforia
        super();
    }*/

    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);

        robot.mounter.setPosition(0.7);

        waitForStart();

        robot.flinger.setPower(1);

        Thread.sleep(3000);

        robot.flinger.setPower(0);

        Thread.sleep(100);

        robot.mounter.setPosition(0);

        Thread.sleep(0);

        robot.flinger.setPower(1);

        Thread.sleep(5000);

        robot.flinger.setPower(0);

        /*robot.frontLeft.setPower(1);
        robot.frontRight.setPower(1);
        robot.backLeft.setPower(1);
        robot.backRight.setPower(1);

        Thread.sleep(2250);

        robot.frontLeft.setPower(0);
        robot.frontRight.setPower(0);
        robot.backLeft.setPower(0);
        robot.backRight.setPower(0);*/

        telemetry.addData("Autonomous", "complete.");
        telemetry.update();
    }
}
