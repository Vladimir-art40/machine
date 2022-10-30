package com.vladimir.machine.devControllers;

public class LightController extends AbstractController {
    public LightController() {
        super("/dev/ttyUSB2");
    }

    public void lightOn(){
        super.trustSendCommand("100");
    }

    public void lightOff(){
        super.trustSendCommand("101");
    }

    public void flyLight(){
        super.trustSendCommand("1");
    }

    public void policeLight(){
        super.trustSendCommand("2");
    }

    public void airLight(){
        super.trustSendCommand("3");
    }

    public void airBadLight(){
        super.trustSendCommand("4");
    }

    public void ploshadkaLight(){
        super.trustSendCommand("5");
    }

    public void rubbishLeftLight(){
        super.trustSendCommand("6");
    }

    public void rubbishRightLight(){
        super.trustSendCommand("7");
    }

    public void rubbishFoundLight(){
        super.trustSendCommand("8");
    }
}
