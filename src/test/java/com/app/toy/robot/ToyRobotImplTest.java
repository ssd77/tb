package com.app.toy.robot;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.app.toy.robot.common.Direction;
import com.app.toy.robot.exception.InvalidCommandException;
import com.app.toy.robot.exception.InvalidDirectionException;
import com.app.toy.robot.exception.InvalidPositionException;
import org.junit.Assert;
import org.junit.Test;

public class ToyRobotImplTest {

    private Table table = new Table();
    private ToyRobotImpl toyRobotImpl = new ToyRobotImpl(table);


    @Test
    public void testPlace() throws Exception {
        toyRobotImpl.place(3,4, Direction.EAST);
        Assert.assertEquals(Direction.EAST.getValue(), toyRobotImpl.getDirection().getValue());
        Assert.assertEquals(3, toyRobotImpl.getPosX());
        Assert.assertEquals(4, toyRobotImpl.getPosY());
    }

    @Test
    public void testInvalidPosition() {
        assertThatThrownBy(() -> {
            toyRobotImpl.place(8,7, Direction.EAST);
        }).isInstanceOf(InvalidPositionException.class)
                .withFailMessage("X, Y should not be less than 0.");

        Assert.assertNull(toyRobotImpl.getDirection());
    }

    @Test
    public void testInvalidNegativePosition(){
        assertThatThrownBy(() -> {
            toyRobotImpl.place(-1,0, Direction.EAST);
        }).isInstanceOf(InvalidPositionException.class)
        .withFailMessage("X, Y should not be less than 0.");

        //Direction should not be set if position is invalid
        Assert.assertNull(toyRobotImpl.getDirection());
    }


    @Test
    public void testNullDirection() {
        assertThatThrownBy(() -> {
            toyRobotImpl.place(3, 4, null);
        }).isInstanceOf(InvalidDirectionException.class)
                .withFailMessage("Direction must be specified.");

        //Direction should not be set if position is invalid
        Assert.assertNull(toyRobotImpl.getDirection());
    }


    @Test
    public void testInvalidCommand() {
        assertThatThrownBy(() -> {
            toyRobotImpl.runCommand("PLACE 0,0,NORTHEAST");
        }).isInstanceOf(InvalidCommandException.class);

        //Direction should not be set if position is invalid
        Assert.assertNull(toyRobotImpl.getDirection());
    }

    @Test
    public void testMove() throws Exception {
        toyRobotImpl.place(3,4, Direction.EAST);
        Assert.assertEquals(Direction.EAST, toyRobotImpl.getDirection());
        Assert.assertEquals(1, toyRobotImpl.getDirection().getValue());
        toyRobotImpl.move();
        Assert.assertEquals(4, toyRobotImpl.getPosX());
        Assert.assertEquals(4, toyRobotImpl.getPosY());
    }

    @Test
    public void testRotateLeft() throws Exception {
        toyRobotImpl.place(3,3, Direction.EAST);
        toyRobotImpl.rotateLeft();
        Assert.assertEquals(Direction.NORTH.getValue(), toyRobotImpl.getDirection().getValue());
        toyRobotImpl.rotateLeft();
        Assert.assertEquals(Direction.WEST.getValue(), toyRobotImpl.getDirection().getValue());
        toyRobotImpl.rotateLeft();
        Assert.assertEquals(Direction.SOUTH.getValue(), toyRobotImpl.getDirection().getValue());
        toyRobotImpl.rotateLeft();
        Assert.assertEquals(Direction.EAST.getValue(), toyRobotImpl.getDirection().getValue());

    }

    @Test
    public void testRotateRight() throws Exception {
        toyRobotImpl.place(3,3, Direction.EAST);
        toyRobotImpl.rotateRight();
        Assert.assertEquals(Direction.SOUTH.getValue(), toyRobotImpl.getDirection().getValue());
        toyRobotImpl.rotateRight();
        Assert.assertEquals(Direction.WEST.getValue(), toyRobotImpl.getDirection().getValue());
        toyRobotImpl.rotateRight();
        Assert.assertEquals(Direction.NORTH.getValue(), toyRobotImpl.getDirection().getValue());
        toyRobotImpl.rotateRight();
        Assert.assertEquals(Direction.EAST.getValue(), toyRobotImpl.getDirection().getValue());

    }

    @Test
    public void testShowReport() throws Exception {
        toyRobotImpl.place(0,0, Direction.NORTH);
        toyRobotImpl.move();
        Assert.assertEquals(0, toyRobotImpl.getPosX());
        Assert.assertEquals(1, toyRobotImpl.getPosY());
        Assert.assertEquals(Direction.NORTH.getValue(), toyRobotImpl.getDirection().getValue());
        toyRobotImpl.showReport();

        toyRobotImpl.place(0,0, Direction.NORTH);
        toyRobotImpl.rotateLeft();
        Assert.assertEquals(0, toyRobotImpl.getPosX());
        Assert.assertEquals(0, toyRobotImpl.getPosY());
        Assert.assertEquals(Direction.WEST.getValue(), toyRobotImpl.getDirection().getValue());
        toyRobotImpl.showReport();

        toyRobotImpl.place(1,2, Direction.EAST);
        toyRobotImpl.move();
        toyRobotImpl.move();
        toyRobotImpl.rotateLeft();
        toyRobotImpl.move();
        Assert.assertEquals(3, toyRobotImpl.getPosX());
        Assert.assertEquals(3, toyRobotImpl.getPosY());
        Assert.assertEquals(Direction.NORTH.getValue(), toyRobotImpl.getDirection().getValue());
        toyRobotImpl.showReport();
    }

}
