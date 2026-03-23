package frc.robot.commands.TurretMath;

public class TurretMathHelper {
    public static double calculateTurretAngle(double targetX, double targetY) {
        // Calculate the angle to the target using atan2
        double angle = Math.atan2(targetY, targetX);
        
        return Math.toDegrees(angle); // Convert to degrees if needed
    }

}
