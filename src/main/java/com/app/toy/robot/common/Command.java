package com.app.toy.robot.common;

public enum  Command {

    PLACE("PLACE"),
    MOVE("MOVE"),
    LEFT("LEFT"),
    RIGHT("RIGHT"),
    REPORT("REPORT");

    String value;

    Command(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
