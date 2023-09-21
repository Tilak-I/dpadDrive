// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class driveSystem extends SubsystemBase {
  private WPI_TalonSRX BackLeftTalonSRX;
  private WPI_TalonSRX BackRightTalonSRX;
  private WPI_TalonSRX FrontLeftTalonSRX;
  private WPI_TalonSRX FrontRightTalonSRX;
  private DifferentialDrive POVDrive;
  
  private static double mSpeed;
  private static double mRotation;
  /** Creates a new driveSystem. */
  public driveSystem() 
  {
    BackLeftTalonSRX = new WPI_TalonSRX(DriveConstants.BACKLEFTCTREID);
    BackRightTalonSRX = new WPI_TalonSRX(DriveConstants.BACKRIGHTCTREID);
    FrontLeftTalonSRX = new WPI_TalonSRX(DriveConstants.FRONTLEFTCTREID);
    FrontRightTalonSRX = new WPI_TalonSRX(DriveConstants.FRONTRIGHTCTREID);
  
    BackLeftTalonSRX.follow(FrontLeftTalonSRX);
    BackRightTalonSRX.follow(FrontRightTalonSRX);

    BackLeftTalonSRX.setInverted(true);
    FrontLeftTalonSRX.setInverted(true);
    POVDrive = new DifferentialDrive(FrontLeftTalonSRX, FrontRightTalonSRX);
    SmartDashboard.putNumber("Rotation", mRotation);
    SmartDashboard.putNumber("Speed", mSpeed);
  }

  public static void dpadUP()
  {
    mSpeed += DriveConstants.POVSPEEDINCREMENT;
    if (mSpeed > DriveConstants.SPEEDLIMIT) {mSpeed = DriveConstants.SPEEDLIMIT;}
  }

  public static void dpadDOWN()
  {
    mSpeed -= DriveConstants.POVSPEEDINCREMENT;
    if (mSpeed < DriveConstants.SPEEDLIMIT) {mSpeed = -DriveConstants.SPEEDLIMIT;}
  }

  public static void dpadLEFT()
  {
    mRotation -= DriveConstants.POVROTATIONINCREMENT;
  }
  public static void dpadRIGHT()
  {
    mRotation += DriveConstants.POVROTATIONINCREMENT;
  }

  public static void RightTriggerBind()
  {
    mSpeed = DriveConstants.TRIGGERSPEED;
  }
  public static void LeftTriggerBind()
  {
    mSpeed = -DriveConstants.TRIGGERSPEED;
  }
  public void povArcadeDrive()
  {
    POVDrive.arcadeDrive(mSpeed, mRotation);
  }

  @Override
  public void periodic() {
    // Tis method will be called once per scheduler run

  }
}
