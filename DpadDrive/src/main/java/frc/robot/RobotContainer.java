// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.driveSystem;

public class RobotContainer {
  private CommandXboxController mDriverController;
  public RobotContainer() 
  {
    mDriverController = new CommandXboxController(DriveConstants.DRIVECONTROLLERPORT);
    configureBindings();
  }

  private void configureBindings() 
  {
    mDriverController.povUp().whileTrue(new InstantCommand(() -> driveSystem.dpadUP()));
    mDriverController.povDown().whileTrue(new InstantCommand(() -> driveSystem.dpadDOWN()));
    mDriverController.povLeft().whileTrue(new InstantCommand(() -> driveSystem.dpadLEFT()));
    mDriverController.povRight().whileTrue(new InstantCommand(() -> driveSystem.dpadRIGHT()));

    mDriverController.rightTrigger().onTrue(new InstantCommand(() -> driveSystem.RightTriggerBind()));
    mDriverController.leftTrigger().onTrue(new InstantCommand(() -> driveSystem.LeftTriggerBind()));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
