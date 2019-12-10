package com.app.toy.robot.common;

public enum  Direction {

    NORTH(0),
    EAST(1),
    SOUTH(2),
    WEST(3);

    int value;

    Direction(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Direction fromValue(int value) {
        switch(value) {
            case 0:
                return NORTH;
            case 1:
                return EAST;
            case 2:
                return SOUTH;
            case 3:
                return WEST;
            default:
                return null;
        }
    }
}
