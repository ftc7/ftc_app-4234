package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name="ThrowTime", group="Throw")
public class flingautodelay extends LinearOpMode {

    private hopefullyrunto robot = new hopefullyrunto();

    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);

        robot.mounter.setPosition(0);

        waitForStart();

        Thread.sleep(20000);

        driveEncoder(1000, 1000, 0.3, 0.3, true);

        Thread.sleep(1000);

        robot.flinger.setPower(1);

        Thread.sleep(1000);

        robot.flinger.setPower(0);

        Thread.sleep(0);

        robot.mounter.setPosition(0.5);

        Thread.sleep(2000);

        robot.flinger.setPower(1);

        Thread.sleep(1000);

        robot.flinger.setPower(0);

        driveEncoder(2000, 2000, 0.3, 0.3, true);

        telemetry.addData("Autonomous", "complete.");
        telemetry.update();
    }

    private void driveEncoder(int posL, int posR, double powerL, double powerR, boolean left) throws InterruptedException {       //1 is forwards
        robot.frontLeft.setTargetPosition(robot.frontLeft.getCurrentPosition() + posL);
        robot.frontRight.setTargetPosition(robot.frontRight.getCurrentPosition() + posR);
        robot.backLeft.setTargetPosition(robot.backLeft.getCurrentPosition() + posL);
        robot.backRight.setTargetPosition(robot.backRight.getCurrentPosition() + posR);

        robot.frontLeft.setPower(powerL);
        robot.frontRight.setPower(powerR);
        robot.backLeft.setPower(powerL);
        robot.backRight.setPower(powerR);

        if (left) {
            while(robot.frontLeft.isBusy()){}
        } else {
            while(robot.frontRight.isBusy()){}
        }
    }
}
