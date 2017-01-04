package org.firstinspires.ftc.teamcode;

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

@Autonomous(name="VersionVuforia", group="Autonomous")
public class vuforitest extends LinearOpMode {

    private hopefullyrunto robot = new hopefullyrunto();
    private VuforiaLocalizer vuforia;
    private VuforiaTrackables beacons;

    public vuforitest() {       //Called upon invocation of the class; defines variables and initializes Vuforia
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
        waitForStart();
        driveToVuforia(0.07);
    }



    private void driveToVuforia(double speed){
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
}
