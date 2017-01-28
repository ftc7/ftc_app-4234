package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
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

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import com.qualcomm.robotcore.hardware.ColorSensor;

@Autonomous(name="BrilliantBeacons-red", group="Autonomous")
public class drivetov extends LinearOpMode {

    private hopefullymeccanum robot = new hopefullymeccanum();
    private VuforiaLocalizer vuforia;
    private VuforiaTrackables beacons;

    public drivetov() {       //Called upon invocation of the class; defines variables and initializes Vuforia
        super();
        VuforiaLocalizer.Parameters params = new VuforiaLocalizer.Parameters(R.id.cameraMonitorViewId);
        params.cameraDirection = VuforiaLocalizer.CameraDirection.FRONT;
        params.vuforiaLicenseKey = "AaIK0f//////AAAAGTsgmHszM030skFBvcnAJlNSWaH7oKLZhxAZeCi7ToBGSKkO7T3EvzsRVYQdyDp2X+TFK6TQs+3WoCHkZXDYPQd87f77D6kvcBr8zbJ07Fb31UKiXdUBvX+ZQSV3kBhdAoxhfMa0WPgys7DYaeiOmM49CsNra7nVh05ls0th3h07wwHz3s/PBZnQwpbfr260CDgqBv4e9D79Wg5Ja5p+HAOJvyqg2r/Z5dOyRvVI3f/jPBRZHvDgDF9KTcuJAPoDHxfewmGFOFtiUamRLvcrkK9rw2Vygi7w23HYlzFO7yap+jUk1bv0uWNc0j5HPJDAjqa2ijBN9aVDrxzmFJml5WMA3GJJp8WOd9gkGhtI/BIo";
        params.cameraMonitorFeedback = AXES;

        vuforia = ClassFactory.createVuforiaLocalizer(params);

        beacons = vuforia.loadTrackablesFromAsset("FTC_2016-17");
        beacons.get(0).setName("Wheels");
        beacons.get(1).setName("Tools");
        beacons.get(2).setName("Legos");
        beacons.get(3).setName("Gears");
        beacons.activate();
    }

    public void runOpMode() throws InterruptedException {
        //fl bl fr br

        robot.init(hardwareMap);

        double speeed = 0.4;

        robot.frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.mounter.setPosition(0.7);

        waitForStart();

        originalAuto(0.4, 0.4);

        Thread.sleep(500);

        driveEncoder(1500, 1500, speeed, speeed, true);
        Thread.sleep(500);
        driveEncoder(1100, 0, speeed, 0, false);
        Thread.sleep(500);
        driveEncoder(2100, 2100, speeed, speeed, true);
        Thread.sleep(500);
        driveEncoder(0, 1500, 0, speeed, false);
        Thread.sleep(500);
        driveEncoder(400, 400, speeed, speeed, true);
        Thread.sleep(500);
        driveEncoder(0, 2500, 0, speeed, true);
        Thread.sleep(500);
        driveEncoder(300, 300, speeed, speeed, true);
        Thread.sleep(500);

        robot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        robot.frontLeft.setPower(0);
        robot.frontRight.setPower(0);
        robot.backLeft.setPower(0);
        robot.backRight.setPower(0);

        driveToVuforia(0.05, 100);

        robot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        pushButton(true);

        robot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        robot.frontLeft.setPower(0);
        robot.frontRight.setPower(0);
        robot.backLeft.setPower(0);
        robot.backRight.setPower(0);
        //Thread.sleep(10000);
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

    private void driveToVuforia(double speed, int position) {
        boolean running = true;

        while (running){
            telemetry.addData("Camera", vuforia.getCameraCalibration());

            for(VuforiaTrackable beac : beacons){
                OpenGLMatrix pose = ((VuforiaTrackableDefaultListener) beac.getListener()).getPose();

                if(pose != null){
                    VectorF translation = pose.getTranslation();

                    telemetry.addData(beac.getName() + "-X:", round(translation.get(0)));       //Positive is when the target is higher than the phone
                    telemetry.addData(beac.getName() + "-Y:", round(translation.get(1)));       //Positive is when the target is righter than the phone
                    telemetry.addData(beac.getName() + "-Z:", round(translation.get(2)));       //NEGATIVE is when the target is behind than the phone (visible with the back camera)

                    if (abs(translation.get(1) - position) < 23){
                        running = false;
                    }
                    else if (translation.get(1) > position) {
                        while(translation.get(1) > position + 25){
                            pose = ((VuforiaTrackableDefaultListener) beac.getListener()).getPose();
                            translation = pose.getTranslation();

                            robot.frontLeft.setPower(-speed);
                            robot.frontRight.setPower(-speed);
                            robot.backLeft.setPower(-speed);
                            robot.backRight.setPower(-speed);

                            telemetry.addData(beac.getName() + "-X:", round(translation.get(0)));       //Positive is when the target is higher than the phone
                            telemetry.addData(beac.getName() + "-Y:", round(translation.get(1)));       //Positive is when the target is righter than the phone
                            telemetry.addData(beac.getName() + "-Z:", round(translation.get(2)));       //NEGATIVE is when the target is behind than the phone (visible with the back camera)
                        }

                        robot.frontLeft.setPower(0);
                        robot.frontRight.setPower(0);
                        robot.backLeft.setPower(0);
                        robot.backRight.setPower(0);

                        running = false;
                    }
                    else if (translation.get(1) < position) {
                        while(translation.get(1) < position - 25){
                            pose = ((VuforiaTrackableDefaultListener) beac.getListener()).getPose();
                            translation = pose.getTranslation();

                            robot.frontLeft.setPower(speed);
                            robot.frontRight.setPower(speed);
                            robot.backLeft.setPower(speed);
                            robot.backRight.setPower(speed);

                            telemetry.addData(beac.getName() + "-X:", round(translation.get(0)));       //Positive is when the target is higher than the phone
                            telemetry.addData(beac.getName() + "-Y:", round(translation.get(1)));       //Positive is when the target is righter than the phone
                            telemetry.addData(beac.getName() + "-Z:", round(translation.get(2)));       //NEGATIVE is when the target is behind than the phone (visible with the back camera)
                        }

                        robot.frontLeft.setPower(0);
                        robot.frontRight.setPower(0);
                        robot.backLeft.setPower(0);
                        robot.backRight.setPower(0);

                        running = false;
                    }
                }
            }
            telemetry.update();
        }

        return;
    }

    private void driveCloserToVuforia(double speed) {
        boolean running = true;

        while (running){
            telemetry.addData("Camera", vuforia.getCameraCalibration());

            for(VuforiaTrackable beac : beacons){
                OpenGLMatrix pose = ((VuforiaTrackableDefaultListener) beac.getListener()).getPose();

                if(pose != null){
                    VectorF translation = pose.getTranslation();

                    telemetry.addData(beac.getName() + "-X:", round(translation.get(0)));       //Positive is when the target is higher than the phone
                    telemetry.addData(beac.getName() + "-Y:", round(translation.get(1)));       //Positive is when the target is righter than the phone
                    telemetry.addData(beac.getName() + "-Z:", round(translation.get(2)));       //NEGATIVE is when the target is behind than the phone (visible with the back camera)

                    robot.frontLeft.setPower(-speed);
                    robot.frontRight.setPower(speed);
                    robot.backLeft.setPower(speed);
                    robot.backRight.setPower(-speed);

                    while (abs(translation.get(2)) > 115){
                        running = false;
                    }

                    robot.frontLeft.setPower(0);
                    robot.frontRight.setPower(0);
                    robot.backLeft.setPower(0);
                    robot.backRight.setPower(0);

                    running = false;
                }
            }
            telemetry.update();
        }

        return;
    }

    private void driveRawMotor(double frontL, double frontR, double backL, double backR, long time) throws InterruptedException {       //1 is forwards
        robot.frontLeft.setPower(frontL);
        robot.frontRight.setPower(frontR);
        robot.backLeft.setPower(backL);
        robot.backRight.setPower(backR);

        Thread.sleep(time);

        robot.frontLeft.setPower(0);
        robot.frontRight.setPower(0);
        robot.backLeft.setPower(0);
        robot.backRight.setPower(0);
    }

    private void originalAuto(double speedL, double speedR) throws InterruptedException {
        robot.flinger.setPower(1);

        Thread.sleep(2500);

        robot.mounter.setPosition(0);
        robot.flinger.setPower(0);

        Thread.sleep(2000);

        robot.flinger.setPower(1);
        Thread.sleep(3300);
        robot.flinger.setPower(0);
    }

    private int getColor() throws InterruptedException {
        int i = 0;

        float hsvValues[] = {0F,0F,0F};
        final float values[] = hsvValues;
        //final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(R.id.RelativeLayout);
        ColorSensor csensor = hardwareMap.colorSensor.get("colorSense");
        csensor.enableLed(false);

        while(i < 100) {
            Color.RGBToHSV(csensor.red(), csensor.green(), csensor.blue(), hsvValues);
            telemetry.addData("Clear", csensor.alpha());
            telemetry.addData("Red  ", csensor.red());
            telemetry.addData("Green", csensor.green());
            telemetry.addData("Blue ", csensor.blue());
            telemetry.addData("Hue  ", hsvValues[0]);
            /*relativeLayout.post(new Runnable() {
              public void run() {
                relativeLayout.setBackgroundColor(Color.HSVToColor(0xff, values));
              }
            });*/
            telemetry.update();
            idle();
            i++;
        }

        return (int)(hsvValues[0]);
    }

    private boolean getButtonColor() throws InterruptedException {
        if(getColor() > 300) {
            return(true);           //red
        } else {
            return(false);          //blue
        }
    }

    private void pushButton(boolean onRedSide) throws InterruptedException {        //true is red
        if(onRedSide ^ getButtonColor()) {
            telemetry.addData("our color is on the", "right");
            driveEncoder(-256, -256, 0.2, 0.2, true);
            robot.buttonPress.setPower(1);
            Thread.sleep(2500);
            robot.buttonPress.setPower(0);
        } else {
            telemetry.addData("our color is on the", "left");
            driveEncoder(-480, -480, 0.2, 0.2, true);
            robot.buttonPress.setPower(1);
            Thread.sleep(2500);
            robot.buttonPress.setPower(0);
        }
    }
}
