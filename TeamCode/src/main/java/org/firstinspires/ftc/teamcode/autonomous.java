package org.firstinspires.ftc.teamcode;
/**     VUFORIA UNIT SCALE:
        40in:900
        20in:450
        10in:225
        in ≈ 22.5 units
 */
import android.app.Activity;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.vuforia.HINT;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import static java.lang.Math.round;
import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.Parameters.CameraMonitorFeedback.AXES
        ;
@Autonomous(name="AlphaAutonomous", group="Autonomous")
public class autonomous extends LinearOpMode {

    ColorSensor csensor;

    hopefullymeccanum robot = new hopefullymeccanum();

    //robot.init(hardwareMap);

    public void runOpMode() throws InterruptedException {
        float hsvValues[] = {0F,0F,0F};
        final float values[] = hsvValues;
        final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(com.qualcomm.ftcrobotcontroller.R.id.RelativeLayout);
        csensor = hardwareMap.colorSensor.get("colorSense");
        csensor.enableLed(false);

        VuforiaLocalizer.Parameters params = new VuforiaLocalizer.Parameters(R.id.cameraMonitorViewId);
        params.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        params.vuforiaLicenseKey = "AaIK0f//////AAAAGTsgmHszM030skFBvcnAJlNSWaH7oKLZhxAZeCi7ToBGSKkO7T3EvzsRVYQdyDp2X+TFK6TQs+3WoCHkZXDYPQd87f77D6kvcBr8zbJ07Fb31UKiXdUBvX+ZQSV3kBhdAoxhfMa0WPgys7DYaeiOmM49CsNra7nVh05ls0th3h07wwHz3s/PBZnQwpbfr260CDgqBv4e9D79Wg5Ja5p+HAOJvyqg2r/Z5dOyRvVI3f/jPBRZHvDgDF9KTcuJAPoDHxfewmGFOFtiUamRLvcrkK9rw2Vygi7w23HYlzFO7yap+jUk1bv0uWNc0j5HPJDAjqa2ijBN9aVDrxzmFJml5WMA3GJJp8WOd9gkGhtI/BIo";
        params.cameraMonitorFeedback = AXES;

        VuforiaLocalizer vuforia = ClassFactory.createVuforiaLocalizer(params);
        Vuforia.setHint(HINT.HINT_MAX_SIMULTANEOUS_IMAGE_TARGETS, 4);

        VuforiaTrackables beacons = vuforia.loadTrackablesFromAsset("FTC_2016-17");
        beacons.get(0).setName("Wheels");
        beacons.get(1).setName("Tools");
        beacons.get(2).setName("Legos");
        beacons.get(3).setName("Gears");

        robot.init(hardwareMap);

        waitForStart();

        beacons.activate();

        robot.frontLeft.setPower(-0.1);
        robot.frontRight.setPower(0.1);
        robot.backLeft.setPower(-0.1);
        robot.backRight.setPower(0.1);

        boolean running = true;

        while (running){
            telemetry.addData("Camera", vuforia.getCameraCalibration());

            for(VuforiaTrackable beac : beacons){
                OpenGLMatrix pose = ((VuforiaTrackableDefaultListener) beac.getListener()).getPose();

                if(pose != null){
                    VectorF translation = pose.getTranslation();

                    telemetry.addData(beac.getName() + "-Translation", translation);

                    telemetry.addData(beac.getName() + "-X:", round(translation.get(0)));       //Positive is when the target is higher than the phone
                    telemetry.addData(beac.getName() + "-Y:", round(translation.get(1)));       //Positive is when the target is righter than the phone
                    telemetry.addData(beac.getName() + "-Z:", round(translation.get(2)));       //NEGATIVE is when the target is behind than the phone (visible with the back camera)
                    if (translation.get(1) < 0){
                        running = false;
                    }
                }
            }
            telemetry.update();
        }

        robot.frontLeft.setPower(0);
        robot.frontRight.setPower(0);
        robot.backLeft.setPower(0);
        robot.backRight.setPower(0);


        /*while(opModeIsActive()){
            Color.RGBToHSV(csensor.red(), csensor.green(), csensor.blue(), hsvValues);
            telemetry.addData("Hue", hsvValues[0]);
            telemetry.update();

            relativeLayout.post(new Runnable() {
                public void run() {
                    relativeLayout.setBackgroundColor(Color.HSVToColor(0xff, values));
                }
            });
        }*/



        /*robot.frontLeft.setPower(-0.1);
        robot.frontRight.setPower(0.1);
        robot.backLeft.setPower(-0.1);
        robot.backRight.setPower(0.1);*/
    }

    void move(short direction, long distance, long speed) throws InterruptedException{
        speed /= 100;

        startMove(direction, speed);

        sleep(distance/speed);

        stopMove();
    }

    void startMove(short direction, long speed) {

        switch (direction) {
            case (0): {             //Forwards
                robot.frontLeft.setPower(-speed);
                robot.frontRight.setPower(speed);
                robot.backLeft.setPower(-speed);
                robot.backRight.setPower(speed);
            }
            case(1): {              //Left
                robot.frontLeft.setPower(speed);
                robot.frontRight.setPower(speed);
                robot.backLeft.setPower(-speed);
                robot.backRight.setPower(-speed);
            }
            case(2): {              //Backwards
                robot.frontLeft.setPower(speed);
                robot.frontRight.setPower(-speed);
                robot.backLeft.setPower(speed);
                robot.backRight.setPower(-speed);
            }
            case(3): {              //Right
                robot.frontLeft.setPower(-speed);
                robot.frontRight.setPower(-speed);
                robot.backLeft.setPower(speed);
                robot.backRight.setPower(speed);
            }
        }
    }

    void stopMove() {
        robot.frontLeft.setPower(0);
        robot.frontRight.setPower(0);
        robot.backLeft.setPower(0);
        robot.backRight.setPower(0);
    }

}
