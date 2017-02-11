package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Disabled
@Autonomous(name="SimplySideways", group="Drive")
public class sideways extends LinearOpMode {

    private hopefullymeccanum robot = new hopefullymeccanum();

    public sideways() {       //Called upon invocation of the class
        super();
    }

    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        waitForStart();

        robot.frontLeft.setPower(-1);
        robot.frontRight.setPower(1);
        robot.backLeft.setPower(1);
        robot.backRight.setPower(-1);

        Thread.sleep(30000);

        robot.frontLeft.setPower(0);
        robot.frontRight.setPower(0);
        robot.backLeft.setPower(0);
        robot.backRight.setPower(0);


        telemetry.addData("Autonomous", "complete.");
        telemetry.update();
    }
}
