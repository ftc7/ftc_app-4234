package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.vuforia.HINT;
import com.vuforia.Vuforia;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import static java.lang.Math.abs;
import static java.lang.Math.round;
import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.Parameters.CameraMonitorFeedback.AXES;

@Disabled
@Autonomous(name="PointProven", group="runto")
public class rtp extends LinearOpMode {

    private hopefullyrunto robot = new hopefullyrunto();

    public rtp() {       //Called upon invocation of the class; defines variables and initializes Vuforia
        super();
    }

    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);

        telemetry.addData("flm", robot.frontLeft.getMaxSpeed());
        telemetry.update();

        waitForStart();

        driveEncoder(350, 350, 0.5, 0.5, true);

        Thread.sleep(1000);

        driveEncoder(1300, 0, 0.5, 0, false);
    }



    private void driveEncoder(int posL, int posR, double powerL, double powerR, boolean left) throws InterruptedException {       //1 is forwards
        robot.backRight.setTargetPosition(robot.backRight.getCurrentPosition() + posR);
        robot.backLeft.setTargetPosition(robot.backLeft.getCurrentPosition() + posL);
        robot.frontRight.setTargetPosition(robot.frontRight.getCurrentPosition() + posR);
        robot.frontLeft.setTargetPosition(robot.frontLeft.getCurrentPosition() + posL);

        robot.backRight.setPower(powerR);
        robot.backLeft.setPower(powerL);
        robot.frontRight.setPower(powerR);
        robot.frontLeft.setPower(powerL);

        if (left) {
            while(robot.frontRight.isBusy()){}
        } else {
            while(robot.frontLeft.isBusy()){}
        }
    }

}
