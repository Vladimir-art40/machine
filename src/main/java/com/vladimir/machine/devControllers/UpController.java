package com.vladimir.machine.devControllers;

public class UpController extends AbstractController {
    public UpController() {
        super("/dev/ttyUSB1");
    }

    public void park() {
        super.trustSendCommand("-1 -1 -1");
    }

    public void move(int x, int y, int z){
        super.trustSendCommand(x + " " + y + " " + z);
    }

    public void moveToPloshadka(){
        move(3000, 10000, 1000);
    }

    public void moveToAir(){
        move(18000, 26000, 1);
    }

    public void moveToRubbishLeft(){
        move(25000, 22000, -1000);
    }

    public void moveToRubbishRight(){
        move(1, 22000, -1000);
    }
}
