package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import android.view.View;
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
/**     VUFORIA UNIT SCALE:
        40in:900
        20in:450
        10in:225
        in ≈ 22.5 units
 */
/*import android.view.View;*/

@Autonomous(name="ThrowTwo", group="Autonomous")
public class flingauto extends LinearOpMode {

    private hopefullyrunto robot = new hopefullyrunto();

    public flingauto() {       //Called upon invocation of the class; defines variables and initializes Vuforia
        super();
    }

    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);

        robot.mounter.setPosition(0.7);

        waitForStart();

        robot.flinger.setPower(100);

        Thread.sleep(3000);

        robot.flinger.setPower(0);

        Thread.sleep(100);

        robot.mounter.setPosition(0);

        Thread.sleep(0);

        robot.flinger.setPower(100);

        Thread.sleep(5000);

        robot.flinger.setPower(0);

        robot.frontLeft.setPower(-100);
        robot.frontRight.setPower(-100);
        robot.backLeft.setPower(-100);
        robot.backRight.setPower(-100);

        Thread.sleep(2250);

        robot.frontLeft.setPower(0);
        robot.frontRight.setPower(0);
        robot.backLeft.setPower(0);
        robot.backRight.setPower(0);

        telemetry.addData("Autonomous", "complete.");
        telemetry.update();
    }
}
