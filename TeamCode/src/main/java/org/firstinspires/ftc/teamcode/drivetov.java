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

@Autonomous(name="BrilliantBeacons", group="Autonomous")
public class drivetov extends LinearOpMode {

    private hopefullyrunto robot = new hopefullyrunto();
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
        robot.init(hardwareMap);

        robot.mounter.setPosition(0.7);

        waitForStart();

        originalAuto(0.4, 0.4);

        Thread.sleep(500);

        driveRawMotor(1, 1, 1, 1, 100);
        driveRawMotor(1, 0, 1, 0, 500);
        driveRawMotor(1, 1, 1, 1, 2000);
        driveRawMotor(0, 1, 0, 1, 500);

        driveToVuforia(0.05);

        pushButton(true);
    }



    private void driveToVuforia(double speed) {
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

                    if (abs(translation.get(0)) < 23){
                        running = false;
                    }
                    else if (translation.get(0) > 0) {
                        while(translation.get(0) > 0){
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
                    else if (translation.get(0) < 0) {
                        while(translation.get(0) < 0){
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

        Thread.sleep(3000);

        robot.mounter.setPosition(0);
        robot.flinger.setPower(0);

        Thread.sleep(2000);

        robot.flinger.setPower(1);
        Thread.sleep(2500);
        robot.flinger.setPower(0);
        Thread.sleep(1000);

        robot.flinger.setPower(1);
        Thread.sleep(2500);
        robot.flinger.setPower(0);
        Thread.sleep(1000);

        robot.flinger.setPower(0);

        /*robot.frontLeft.setPower(-speedL);
        robot.frontRight.setPower(-speedR);
        robot.backLeft.setPower(-speedL);
        robot.backRight.setPower(-speedR);

        Thread.sleep(2000);

        robot.frontLeft.setPower(0);
        robot.frontRight.setPower(0);
        robot.backLeft.setPower(0);
        robot.backRight.setPower(0);*/
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
            robot.buttonPress.setPosition(0.2);
        } else {
            robot.buttonPress.setPosition(0.7);
        }
    }
}
