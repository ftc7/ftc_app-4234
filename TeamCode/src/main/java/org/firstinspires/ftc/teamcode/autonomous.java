package org.firstinspires.ftc.teamcode;
/**     VUFORIA UNIT SCALE:
        40in:900
        20in:450
        10in:225
        in ≈ 22.5 units
 */
/*import android.app.Activity;
import android.view.View;*/

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
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

import static java.lang.Math.round;
import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.Parameters.CameraMonitorFeedback.AXES;

@Autonomous(name="AlphaAutonomous", group="Autonomous")
public class autonomous extends LinearOpMode {

    private hopefullyrunto robot = new hopefullyrunto();
    private VuforiaLocalizer vuforia;
    private VuforiaTrackables beacons;

    //robot.init(hardwareMap);
    public autonomous() {       //Called upon invocation of the class; defines variables and initializes Vuforia
        super();
        /*VuforiaLocalizer.Parameters params = new VuforiaLocalizer.Parameters(R.id.cameraMonitorViewId);
        params.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        params.vuforiaLicenseKey = "AaIK0f//////AAAAGTsgmHszM030skFBvcnAJlNSWaH7oKLZhxAZeCi7ToBGSKkO7T3EvzsRVYQdyDp2X+TFK6TQs+3WoCHkZXDYPQd87f77D6kvcBr8zbJ07Fb31UKiXdUBvX+ZQSV3kBhdAoxhfMa0WPgys7DYaeiOmM49CsNra7nVh05ls0th3h07wwHz3s/PBZnQwpbfr260CDgqBv4e9D79Wg5Ja5p+HAOJvyqg2r/Z5dOyRvVI3f/jPBRZHvDgDF9KTcuJAPoDHxfewmGFOFtiUamRLvcrkK9rw2Vygi7w23HYlzFO7yap+jUk1bv0uWNc0j5HPJDAjqa2ijBN9aVDrxzmFJml5WMA3GJJp8WOd9gkGhtI/BIo";
        params.cameraMonitorFeedback = AXES;

        vuforia = ClassFactory.createVuforiaLocalizer(params);

        beacons = vuforia.loadTrackablesFromAsset("FTC_2016-17");
        beacons.get(0).setName("Wheels");
        beacons.get(1).setName("Tools");
        beacons.get(2).setName("Legos");
        beacons.get(3).setName("Gears");
        beacons.activate();*/
    }

    public void runOpMode() throws InterruptedException {
        ColorSensor csensor;
        //float hsvValues[] = {0F,0F,0F};
        //final float values[] = hsvValues;
        //final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(com.qualcomm.ftcrobotcontroller.R.id.RelativeLayout);
        csensor = hardwareMap.colorSensor.get("colorSense");
        csensor.enableLed(false);

        //Vuforia.setHint(HINT.HINT_MAX_SIMULTANEOUS_IMAGE_TARGETS, 4);

        robot.init(hardwareMap);

        waitForStart();

        startMove(0, 10, 10000);

        int i = 0;

        while(i<5000){
            i++;
            Thread.sleep(5);
            telemetry.addData("frontLeft", robot.frontLeft.getCurrentPosition());
            telemetry.addData("backLeft", robot.backLeft.getCurrentPosition());
            telemetry.addData("frontRight", robot.frontRight.getCurrentPosition());
            telemetry.addData("backRight", robot.backRight.getCurrentPosition());
            telemetry.addData("frontLeft", robot.frontLeft.isBusy());
            telemetry.addData("backLeft", robot.backLeft.isBusy());
            telemetry.addData("frontRight", robot.frontRight.isBusy());
            telemetry.addData("backRight", robot.backRight.isBusy());
            telemetry.update();
        }

        //Thread.sleep(50000);

        telemetry.addData("move 10000", "complete");

        //driveToVuforia(0, 0, 0.1);

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
    }

    /*private int driveToVuforia(int direction, long xpos, double speed){
        startMove(direction, speed);

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
                    if (translation.get(1) < xpos){
                        running = false;
                    }
                }
            }
            telemetry.update();
        }

        stopMove();

        return 0;
    }*/

    /*private void move(int direction, double distance, double speed) throws InterruptedException{
        speed /= 100;

        startMove(direction, speed);

        sleep((long) (distance/speed));

        stopMove();
    }*/

    private void startMove(int direction, int speed, int distance) {

        robot.frontLeft.setTargetPosition(distance);
        robot.backLeft.setTargetPosition(distance);
        robot.frontRight.setTargetPosition(distance);
        robot.backRight.setTargetPosition(distance);

        switch (direction) {
            case(0): {             //Forwards is positive
                robot.frontLeft.setPower(speed);
                robot.frontRight.setPower(speed);
                robot.backLeft.setPower(speed);
                robot.backRight.setPower(speed);
            }
            case(1): {              //Left is positive
                robot.frontLeft.setPower(speed);
                robot.frontRight.setPower(-speed);
                robot.backLeft.setPower(-speed);
                robot.backRight.setPower(speed);
            }
            case(2): {                //Forwards and left is positive
                robot.backLeft.setPower(speed);
                robot.frontRight.setPower(speed);
                robot.backRight.setPower(0);
                robot.frontLeft.setPower(0);
            }
            case(3): {                //Forwards and right is positive
                robot.backLeft.setPower(0);
                robot.frontRight.setPower(0);
                robot.backRight.setPower(speed);
                robot.frontLeft.setPower(speed);
            }

            /*while(robot.frontRight.getCurrentPosition() < robot.frontRight.getTargetPosition()){

            }

            robot.frontLeft.setPower(0);
            robot.frontRight.setPower(0);
            robot.backLeft.setPower(0);
            robot.backRight.setPower(0);*/
        }
    }
    /*private void stopMove() {
        robot.frontLeft.setPower(0);
        robot.frontRight.setPower(0);
        robot.backLeft.setPower(0);
        robot.backRight.setPower(0);
    }*/

    /*long vuforiaInches(double vuforiaIn) {
        return (long) (vuforiaIn / 22.5);
    }*/

}
