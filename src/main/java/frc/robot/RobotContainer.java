// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static edu.wpi.first.units.Units.*;

import org.photonvision.proto.Photon;

import com.ctre.phoenix6.swerve.SwerveModule.DriveRequestType;
import com.ctre.phoenix6.swerve.SwerveModule.SteerRequestType;
import com.ctre.phoenix6.swerve.SwerveRequest;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.Command.InterruptionBehavior;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.RobotModeTriggers;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine.Direction;
import frc.robot.Constants.SHOOTING_CONSTANTS;
import frc.robot.commands.PhotonVisionCommand;
import frc.robot.commands.TeleOpCommands.IntakeFuelCommand;
import frc.robot.commands.TeleOpCommands.RetractIntakeCommand;
import frc.robot.commands.TeleOpCommands.ShootAtTargetCommand;
import frc.robot.generated.TunerConstants;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Spindexer;
import frc.robot.subsystems.Turret;
import frc.robot.subsystems.Kicker;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.CommandSwerveDrivetrain;
import frc.robot.subsystems.Intake;

import com.pathplanner.lib.commands.PathPlannerAuto;
import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;


public class RobotContainer {


    private double MaxSpeed = 1.0 * TunerConstants.kSpeedAt12Volts.in(MetersPerSecond); // kSpeedAt12Volts desired top speed
    private double MaxAngularRate = RotationsPerSecond.of(0.75).in(RadiansPerSecond); // 3/4 of a rotation per second max angular velocity

    /* Setting up bindings for necessary control of the swerve drive platform */
    private final SwerveRequest.FieldCentric drive = new SwerveRequest.FieldCentric()
            .withDeadband(MaxSpeed * 0.1).withRotationalDeadband(MaxAngularRate * 0.1) // Add a 10% deadband
            .withDriveRequestType(DriveRequestType.Velocity)
            .withSteerRequestType(SteerRequestType.MotionMagicExpo);
    private final SwerveRequest.SwerveDriveBrake brake = new SwerveRequest.SwerveDriveBrake();
    private final SwerveRequest.PointWheelsAt point = new SwerveRequest.PointWheelsAt();

    private final Telemetry logger = new Telemetry(MaxSpeed);

    private final CommandXboxController driveXboxController = new CommandXboxController(0);
    private final CommandXboxController operatorXboxController = new CommandXboxController(1);

    public final CommandSwerveDrivetrain drivetrain = TunerConstants.createDrivetrain();
    public final Climber m_climber = new Climber();
    public final Shooter m_Shooter = new Shooter();
    public final Turret m_Turret = new Turret();
    public final Kicker m_Kicker = new Kicker();
    public final Spindexer m_Spindexer = new Spindexer();
    public final Intake m_intake = new Intake();
    // public final Intake m_intake = new Intake();
    private final PhotonVisionCommand visionCommand = new PhotonVisionCommand(drivetrain::addVisionMeasurement);

    private final SendableChooser<Command> autoChooser;

    private final ShootAtTargetCommand mAutoShoot = new ShootAtTargetCommand(drivetrain, m_Turret, m_Shooter, m_Kicker, m_Spindexer, SHOOTING_CONSTANTS.HUB_RED, SHOOTING_CONSTANTS.HUB_BLUE, SHOOTING_CONSTANTS.SHOOT_LOOKUP_TABLE);
    private final IntakeFuelCommand mAutoIntake = new IntakeFuelCommand(m_intake);
    private final RetractIntakeCommand mAutoRetractIntake = new RetractIntakeCommand(m_intake);

    public RobotContainer() {


        NamedCommands.registerCommand("AutoShoot", mAutoShoot);

        visionCommand.schedule(); // Schedule the vision command to run continuously, even when disabled
        configureBindings();




        // autoChooser.addOption("Just Shoot", new PathPlannerAuto("JustShoot"));
        autoChooser = AutoBuilder.buildAutoChooser(); // populates auto based on Path Planner Auto Folder

        SmartDashboard.putData("autoChooser", autoChooser);
    }

    

    private void configureBindings() {
        // Note that X is defined as forward according to WPILib convention,
        // and Y is defined as to the left according to WPILib convention.
        drivetrain.setDefaultCommand(
            // Drivetrain will execute this command periodically
            drivetrain.applyRequest(() ->
                drive.withVelocityX(-driveXboxController.getLeftY() * MaxSpeed) // Drive forward with negative Y (forward)
                    .withVelocityY(-driveXboxController.getLeftX() * MaxSpeed) // Drive left with negative X (left)
                    .withRotationalRate(-driveXboxController.getRightX() * MaxAngularRate) // Drive counterclockwise with negative X (left)
            )
        );

        // Idle while the robot is disabled. This ensures the configured
        // neutral mode is applied to the drive motors while disabled.
        final var idle = new SwerveRequest.Idle();
        RobotModeTriggers.disabled().whileTrue(
            drivetrain.applyRequest(() -> idle).ignoringDisable(true)
        );

        // Reset the field-centric heading on left bumper press.
        driveXboxController.back().onTrue(drivetrain.runOnce(drivetrain::seedFieldCentric));

        drivetrain.registerTelemetry(logger::telemeterize);


        // Configure Operator Bindings
        driveXboxController.rightTrigger().whileTrue(mAutoShoot);
        driveXboxController.leftTrigger().whileTrue(mAutoIntake);
        driveXboxController.leftBumper().whileTrue(mAutoRetractIntake);
    }

    public Command getAutonomousCommand() {
        // Auto chooser
        return autoChooser.getSelected();
    }
}

