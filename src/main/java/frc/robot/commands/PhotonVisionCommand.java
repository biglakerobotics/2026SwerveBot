package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Vision;
import frc.robot.VisionConsumer;

import static frc.robot.Constants.VisionConstants.CAMERA_NAMES;
import static frc.robot.Constants.VisionConstants.ROBOT_TO_CAMERA_TRANSFORMS;

import java.util.Arrays;

public class PhotonVisionCommand extends Command {
    private final Vision[] visions;
    private final VisionConsumer visionConsumer;

    public PhotonVisionCommand(VisionConsumer consumer) {
        visions = new Vision[CAMERA_NAMES.length];
        for (int i = 0; i < CAMERA_NAMES.length; i++) {
            visions[i] = new Vision(CAMERA_NAMES[i], ROBOT_TO_CAMERA_TRANSFORMS[i]);
        }
        this.visionConsumer = consumer;
        
    }

    @Override
    public void execute() {
      Arrays.stream(visions).forEach(vision -> {
          
        var visionEst = vision.getEstimatedGlobalPose();
        visionEst.ifPresent(est ->{

            var estStdDevs = vision.getEstimationStdDevs();
            visionConsumer.addVisionMeasurement(est.estimatedPose.toPose2d(), est.timestampSeconds, estStdDevs);
            });
        }); 
    }

    @Override
    public boolean runsWhenDisabled() {
        return true;
    }

    
}
