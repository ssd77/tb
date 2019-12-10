package com.app.toy.robot;

import com.app.toy.robot.exception.InvalidPositionException;
import com.app.toy.robot.exception.InvalidTableSizeException;

public class Table {

    private static final int MIN_SIZE = 1;
    private int sizeX = 5;
    private int sizeY = 5;

    Table() {
    }

    public Table(int sizeX, int sizeY) throws InvalidTableSizeException {
        this.validateSize(sizeX, sizeY);
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    public Table(int size) throws InvalidTableSizeException {
        this.validateSize(size, size);
        this.sizeX = size;
        this.sizeY = size;
    }

    ToyRobot createToyRobot(){
        return new ToyRobotImpl(this);
    }

    private void validateSize(int sizeX, int sizeY) throws InvalidTableSizeException {
        if(sizeX < MIN_SIZE || sizeY < MIN_SIZE){
            throw new InvalidTableSizeException(String.format("The minimum Table size is '%1$s X %1$s'", MIN_SIZE) );
        }

    }

    void validatePosition(int posX, int posY) throws InvalidPositionException {

        if(!(posX >= 0 && posX < this.sizeX && posY >= 0 && posY < sizeY)){
            throw new InvalidPositionException(String.format("Invalid position: The robot will fall off the %1$s X %2$s Table.", this.sizeX, this.sizeY));
        }
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }
}
