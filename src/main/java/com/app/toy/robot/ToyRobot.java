package com.app.toy.robot;

import com.app.toy.robot.exception.InvalidCommandException;
import com.app.toy.robot.exception.InvalidDirectionException;
import com.app.toy.robot.exception.InvalidPositionException;

public interface ToyRobot {
    void runCommand(String commandString) throws InvalidCommandException,InvalidPositionException,InvalidDirectionException;
}
