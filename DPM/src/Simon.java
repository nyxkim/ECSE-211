import java.io.File;
import java.util.Random;

import lejos.nxt.Button;
import lejos.nxt.ColorSensor;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.Color;


public class Simon {
	public static void main(String[] args) {
		int buttonChoice;
		

		
		boolean[][] walls7 = {{false,false,false,false,false,true,false,false},{false,false,false,false,false,false,false,true},{false,false,false,false,true,false,true,true}
		,{false,false,false,false,false,true,false,false},{false,true,true,true,false,false,false,false},{false,false,false,false,false,false,false,false},{false,false,true,false,false,true,false,false},
		{true,false,true,true,false,false,true,false}};
		
		boolean[][] walls1 = {{true,true,false,false,false,false,true,false,false,true,false,false},
				{true,true,false,true,false,false,false,false,false,false,false,false},
				{false,false,true,false,false,false,false,false,false,false,false,true},
				{false,false,false,false,false,true,false,false,false,false,true,false},
				{false,false,false,false,true,false,false,true,false,false,false,false},
				{true,false,true,false,false,false,false,false,false,false,false,false},
				{false,false,false,true,false,false,false,false,false,false,false,false},
				{false,false,false,false,false,false,false,true,false,false,false,false},
				{true,true,false,false,false,false,true,false,false,false,false,false},
				{false,false,false,false,true,false,false,true,false,true,false,false},
				{false,false,true,false,false,false,true,false,false,false,false,false},
				{false,true,false,false,false,false,false,false,false,false,false,false}};	
		
		boolean[][] walls2 = {{true,true,false,false,true,true,false,false,false,false,false,false},
				{true,true,false,false,false,false,false,false,false,false,false,false},
				{false,false,false,false,false,false,false,false,false,false,false,false},
				{false,false,true,false,false,true,true,false,false,true,false,false},
				{false,false,false,false,false,true,false,false,false,true,true,false},
				{false,false,false,false,false,true,false,false,false,false,false,false},
				{false,false,false,false,false,false,false,false,true,true,false,false},
				{true,false,false,false,false,false,false,false,false,false,false,false},
				{false,false,false,false,true,true,false,false,false,false,true,false},
				{false,true,false,false,false,false,false,false,false,false,false,true},
				{false,false,false,false,false,false,false,true,false,false,false,false},
				{false,false,true,false,false,true,false,false,true,false,false,false}};
		
		boolean[][] walls3 = {{true,true,false,true,false,false,false,false,true,false,false,false},
				{true,true,false,false,false,false,false,false,false,false,false,false},
				{false,false,false,true,false,false,true,false,false,false,false,false},
				{false,false,true,false,true,false,false,false,false,false,true,false},
				{false,true,false,false,false,true,true,true,false,false,false,false},
				{false,false,false,false,false,false,false,false,false,false,false,false},
				{false,false,false,false,false,false,true,false,true,false,false,false},
				{true,false,false,false,false,false,false,false,false,false,false,true},
				{false,false,false,false,false,false,false,false,false,false,false,false},
				{false,false,false,false,false,false,true,false,false,false,false,false},
				{true,false,false,true,true,false,false,false,false,true,false,false},
				{false,false,true,false,false,false,false,false,false,false,false,true}};
		
		//Other three maps.
		boolean[][] walls4 = {{false,false,false,false,false,false,false,true},{false,false,false,false,false,false,false,false},{false,false,false,true,false,false,true,false}
		,{false,false,true,true,true,false,true,false},{true,false,false,false,false,false,false,true},{true,false,false,false,false,true,false,false},{false,false,false,false,true,false,false,false},
		{true,false,false,false,true,false,true,false}};
		boolean[][] walls5 = {{false,false,false,false,false,false,false,true},{false,false,false,false,false,false,false,false},{false,false,false,true,false,false,true,false}
		,{false,false,true,true,true,false,true,false},{true,false,false,false,false,false,false,true},{true,false,false,false,false,true,false,false},{false,false,false,false,true,false,false,false},
		{true,false,false,false,true,false,true,false}};
		boolean[][] walls6 = {{false,false,false,false,false,false,false,true},{false,false,false,false,false,false,false,false},{false,false,false,true,false,false,true,false}
		,{false,false,true,true,true,false,true,false},{true,false,false,false,false,false,false,true},{true,false,false,false,false,true,false,false},{false,false,false,false,true,false,false,false},
		{true,false,false,false,true,false,true,false}};

		
		
		
		//Initialize EVERYTHING HERE DAMMIT
		
		//Motor/Driver initialization
		NXTRegulatedMotor leftMotor = Motor.A;
		NXTRegulatedMotor rightMotor = Motor.B;
		NXTRegulatedMotor clawMotor = Motor.C;
		WheelDriver wheels = new WheelDriver(leftMotor,rightMotor);
		ClawDriver claw = new ClawDriver(clawMotor);
		
		//LightSensor Initialization
		ColorSensor rightCS = new ColorSensor(SensorPort.S2);
		ColorSensor leftCS = new ColorSensor(SensorPort.S3);
		ColorSensor clawCS = new ColorSensor(SensorPort.S4);
		//clawCS.setFloodlight(true);
		
		Random rand = new Random();
		LightSensorPoller rightPoll = new LightSensorPoller(rightCS);
		LightSensorController rightCSControl = new LightSensorController(rightPoll);
		LightSensorPoller leftPoll = new LightSensorPoller(leftCS);
		LightSensorController leftCSControl = new LightSensorController(leftPoll);
		
		FindLine lineLeft = new FindLine(leftPoll);
		FindLine lineRight = new FindLine(rightPoll);
		
		
		//Odometer Initialization
		Odometer odometer = new Odometer();
		//Initialize map, localization, and navigation.
		Navigation nav = new Navigation(0,0,odometer, wheels,lineLeft,lineRight);
		CorrectionAngel correction = new CorrectionAngel(odometer, leftCSControl, rightCSControl,wheels, lineLeft, lineRight, nav);
		LCDDisplay display = new LCDDisplay(odometer);
		
		//Ultrasonic Initialization
		UltrasonicSensor us = new UltrasonicSensor(SensorPort.S1);
		UltrasonicPoller usPoller = new UltrasonicPoller(us);
		usPoller.start();
		UltrasonicController usController = new UltrasonicController(usPoller);
		

		

		int mapID = 1;
		
		//Start all the threads
		rightPoll.start();
		leftPoll.start();
		lineLeft.start();
		lineRight.start();
//		odometer.start();
//		nav.start();
		display.setDisplay(String.valueOf(mapID));
		display.start();
		while(true) {

			clawCS.setFloodlight(Color.RED);
			
			// clear the display
			LCD.clear();
			
			

			buttonChoice = Button.waitForAnyPress();
			if(buttonChoice == Button.ID_ENTER){
				display.setDisplay("NONE");
				break;
			}
			else if(buttonChoice == Button.ID_RIGHT){
				LCD.clear();
				mapID++;
				buttonChoice = Button.waitForAnyPress();
				display.setDisplay(String.valueOf(mapID));
			}
			else if(buttonChoice == Button.ID_RIGHT){
				LCD.clear();
				mapID--;
				buttonChoice = Button.waitForAnyPress();
				display.setDisplay(String.valueOf(mapID));
			}
		}

		if (buttonChoice == Button.ID_LEFT) {


		} 
		else if(buttonChoice == Button.ID_RIGHT) {

			
		}
		else{
			blockPickUp bp = new blockPickUp(claw,leftMotor,rightMotor, usController);
			odometer.start();
			//correction.start();
			nav.setAngel(correction);
			Map map = new Map(12,1);
			if(mapID == 1){
				clawCS.setFloodlight(Color.BLUE);
				display.setDisplay("ODOMETER");
				//map.addWalls(walls2);
				
			}
			else if(mapID == 2){
				map.addWalls(walls2);
			}
			else if(mapID == 3){
				map.addWalls(walls3);
			}
			else if(mapID == 4){
				map.addWalls(walls4);
			}
			else if(mapID == 5){
				map.addWalls(walls5);
			}
			else if(mapID == 6){
				map.addWalls(walls6);
				
			}
			
			//Memory tester
			else if(mapID == 7){
				display.setDisplay("NONE");
				LCD.clear();
				map = new Map(12,1);
				map.addWalls(walls7);
				map.populate();
				Pathfinder pf = new Pathfinder(map,map.getSquare(1, 2),map.getSquare(11, 11));
				pf.genPath();
				map = new Map(12,1);
				map.addWalls(walls7);
				map.populate();
				pf = new Pathfinder(map,map.getSquare(11, 11),map.getSquare(1, 2));
				pf.genPath();
				LCD.drawInt(File.freeMemory(), 0, 0);
				//LCD.clear();
				//LCD.drawInt(69, 0, 0);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.exit(69);
				
			}
			clawCS.setFloodlight(Color.BLUE);
			display.setDisplay("ODOMETER");
			map.addWalls(walls2);
			map.populate();
//			Path testPath = new Path();
//			testPath.addSquare(new GridSquare(map,0,0,false));
//			testPath.addSquare(new GridSquare(map,1,0,false));
//			testPath.addSquare(new GridSquare(map,0,0,false));
//			testPath.addSquare(new GridSquare(map,0,1,false));
//			testPath.addSquare(new GridSquare(map,0,0,false));
			display.setDisplay("ODOMETER");
			display.setDisplay("NONE");
			LCD.clear();
//			PathTravel pt = new PathTravel(0,0,"N",nav,testPath);
//			pt.travelPath();
			Localizer localizer = new Localizer(map,odometer,nav,usController);
			localizer.run();
			clawCS.setFloodlight(Color.GREEN);
			int x = localizer.getX();
			int y = localizer.getY();
			String o = localizer.getO();
			localizer = null;
			//LCD.clear();
			//LCD.drawInt(localizer.getX(), 0, 0);
			//LCD.drawInt(localizer.getY(), 0, 2);
			Pathfinder pf = new Pathfinder(map,map.getSquare(2, 1),map.getSquare(x, y));
			
			pf.genPath();
			clawCS.setFloodlight(Color.RED);
			PathTravel pt = new PathTravel(x,y,o,nav,pf.getPath());
			pt.travelPath();
			pt.faceWest();
			
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			bp.scanRange();

		}
		
		while (Button.waitForAnyPress() != Button.ID_ESCAPE);
		System.exit(0);
	}
}
