package com.app.toy.robot;

import java.util.Scanner;

public class App {

    public static void main( String[] args ) {
        String command;
        Table table = new Table();
        ToyRobot toyRobot = table.createToyRobot();

        Scanner in = new Scanner(System.in);

        //Ideally should use one of the logger, just for the demo purpose keeping following statement
        System.out.println("Toy Robot Simulator: Control the Toy Robot using any of the following commands: 'PLACE X,Y,NORTH', 'MOVE', 'LEFT', 'RIGHT', 'REPORT' OR 'EXIT' to end simulation.");

        while(true){
            command = in.nextLine();
            if(command.equalsIgnoreCase("exit")){
                break;
            }

            try {
                toyRobot.runCommand(command);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        }

    }
}
