package com.app.toy.robot;

import com.app.toy.robot.common.Command;
import com.app.toy.robot.common.Direction;
import com.app.toy.robot.exception.InvalidCommandException;
import com.app.toy.robot.exception.InvalidDirectionException;
import com.app.toy.robot.exception.InvalidPositionException;

public class ToyRobotImpl implements ToyRobot{
    private static final String COMMAND_SAPARATOR = " ";
    private static final String PARAM_SAPARATOR = ",";
    private int posX = 0;
    private int posY = 0;
    private Table table;
    private Direction direction;


    ToyRobotImpl(Table table) {
        this.table = table;
    }

    public void runCommand(String commandString) throws InvalidCommandException,InvalidDirectionException,InvalidPositionException {

        String commandStr = commandString;

        if(commandStr.contains(COMMAND_SAPARATOR)) {
            commandStr = commandStr.substring(0, commandStr.indexOf(COMMAND_SAPARATOR));
        }

        Command command;
        try {
            command = Command.valueOf(commandStr.toUpperCase());
        }catch (Exception ex){
            throw new InvalidCommandException(String.format("Ignored: Invalid Command [%1$s]", commandStr));
        }

        switch (command){
            case PLACE:

                String paramString = commandString.substring(commandString.indexOf(COMMAND_SAPARATOR) + 1);
                String[] paramArray = paramString.split(PARAM_SAPARATOR);

                int receivedPosX;
                int receivedPosY;
                Direction receivedDirection;
                try {
                    receivedPosX = Integer.parseInt(paramArray[0].trim());
                    receivedPosY = Integer.parseInt(paramArray[1].trim());
                    receivedDirection = Direction.valueOf(paramArray[2].trim().toUpperCase());
                }catch (Exception ex){
                    throw new InvalidCommandException(String.format("Command Ignored: Invalid parameter values [%1$s]."
                            + "\n Try 'PLACE X,Y,FACING' where X and Y are a valid numbers(int) and FACING is valid direction (EAST,WEST,NORTH,SOUTH)", paramString));
                }
                this.place(receivedPosX, receivedPosY, receivedDirection);
                break;
            case MOVE:
                this.move();
                break;
            case LEFT:
                this.rotateLeft();
                break;
            case RIGHT:
                this.rotateRight();
                break;
            case REPORT:
                this.showReport();
                break;
        }

    }

    void place(int posX, int posY, Direction direction) throws InvalidDirectionException, InvalidPositionException{

        if(direction == null){
            throw new InvalidDirectionException("Direction must be specified.");
        }

        if(posX < 0 || posY < 0){
            throw new InvalidPositionException("X, Y should not be less than 0.");
        }

        this.table.validatePosition(posX, posY);

        this.posX = posX;
        this.posY = posY;
        this.direction = direction;
    }


    void move() throws InvalidPositionException {
        if(isNotPlaced()){
            throw new InvalidPositionException("MOVE ignored. ToyRobot must be 'PLACE'd on the table first.");
        }

        int xMoveIndex = 0;
        int yMoveIndex = 0;

        switch (this.direction.getValue()){
            case 0: //NORTH
                yMoveIndex = 1;
                break;
            case 1: //EAST
                xMoveIndex = 1;
                break;
            case 2: //SOUTH
                yMoveIndex = -1;
                break;
            case 3: //WEST
                xMoveIndex = -1;
                break;
            default:
                break;
        }

        this.table.validatePosition(this.posX + xMoveIndex, this.posY + yMoveIndex);

        this.posX = this.posX + xMoveIndex;
        this.posY = this.posY + yMoveIndex;

    }


    void rotateLeft() throws InvalidPositionException {
        if(isNotPlaced()){
            throw new InvalidPositionException("LEFT ignored. ToyRobot must be 'PLACE'd on the table first.");
        }
        setDirection(-1);

    }


    void rotateRight() throws InvalidPositionException {
        if(isNotPlaced()){
            throw new InvalidPositionException("RIGHT ignored. ToyRobot must be 'PLACE'd on the table first.");
        }

        setDirection(1);

    }

    void showReport() throws InvalidPositionException {
        if(isNotPlaced()){
            throw new InvalidPositionException("REPORT ignored. ToyRobot must be 'PLACE'd on the table first.");

        }
        System.out.println(String.format("%1$s,%2$s,%3$s", this.posX, this.posY, this.direction));
    }

    private boolean isNotPlaced(){
        return this.direction == null;
    }

    private void setDirection(int dirIndex){

        int newDirection = this.direction.getValue() + dirIndex;
        if(newDirection < 0){
            newDirection = 3; //Set to WEST when rotated left from NORTH
        }else if(newDirection > 3){
            newDirection = 0; //Set to NORTH when rotated right from WEST
        }

        this.direction = Direction.fromValue(newDirection);
    }

    int getPosX() {
        return posX;
    }

    int getPosY() {
        return posY;
    }

    Direction getDirection() {
        return direction;
    }
}
