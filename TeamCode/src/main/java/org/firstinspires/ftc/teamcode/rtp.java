package org.firstinspires.ftc.teamcode;

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

@Autonomous(name="PointProven", group="runto")
public class rtp extends LinearOpMode {

    private hopefullyrunto robot = new hopefullyrunto();

    public rtp() {       //Called upon invocation of the class; defines variables and initializes Vuforia
        super();
    }

    public void runOpMode() throws InterruptedException {
        //fl bl fr br p

        robot.init(hardwareMap);

        waitForStart();

        driveRawMotor(1000, 1000, 1000, 1000, 0.1);

        while(robot.frontLeft.isBusy())
        {}
    }



    private void driveRawMotor(int frontL, int frontR, int backL, int backR, double power) throws InterruptedException {       //1 is forwards
        robot.frontLeft.setTargetPosition(robot.frontLeft.getCurrentPosition() + frontL);
        robot.frontRight.setTargetPosition(robot.frontRight.getCurrentPosition() + frontR);
        robot.backLeft.setTargetPosition(robot.backLeft.getCurrentPosition() + backL);
        robot.backRight.setTargetPosition(robot.backRight.getCurrentPosition() + backR);

        robot.frontLeft.setPower(power);
        robot.frontRight.setPower(power);
        robot.backLeft.setPower(power);
        robot.backRight.setPower(power);
    }

}
