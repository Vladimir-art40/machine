package com.vladimir.machine.devControllers;

public class MotorController extends AbstractController {

    public MotorController() {
        super("/dev/ttyUSB0");
    }

    public void park(){
        super.trustSendCommand("-1");
    }

    public void startFan(){
        super.trustSendCommand("1");
    }

    public void stopFan(){
        super.trustSendCommand("2");
    }

    public void people(){
        super.trustSendCommand("3");
    }

    public void samosval(){
        super.trustSendCommand("4");
    }

    public void police(){
        super.trustSendCommand("5");
    }
}
