package com.vladimir.machine.controllers;

import com.vladimir.machine.Machine;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class ServerController {

    private final Machine machine;

    public ServerController(Machine machine) {
        this.machine = machine;
    }

    @GetMapping("")
    public String main(){
        return "main";
    }

    /*
    Light
     */

    @GetMapping("/light/lightOn")
    public @ResponseBody String lightOn(){
        machine.light.lightOn();
        return "OK";
    }

    @GetMapping("/light/lightOff")
    public @ResponseBody String lightOff(){
        machine.light.lightOff();
        return "OK";
    }

    @GetMapping("/light/airLight")
    public @ResponseBody String airLight(){
        machine.light.airLight();
        return "OK";
    }

    @GetMapping("/light/policeLight")
    public @ResponseBody String policeLight(){
        machine.light.policeLight();
        return "OK";
    }

    @GetMapping("/light/rubbishFoundLight")
    public @ResponseBody String rubbishFoundLight(){
        machine.light.rubbishFoundLight();
        return "OK";
    }

    @GetMapping("/light/rubbishLeftLight")
    public @ResponseBody String rubbishLeftLight(){
        machine.light.rubbishLeftLight();
        return "OK";
    }

    @GetMapping("/light/rubbishRightLight")
    public @ResponseBody String rubbishRightLight(){
        machine.light.rubbishRightLight();
        return "OK";
    }

    /*
    Motors
     */

    @GetMapping("/motors/park")
    public @ResponseBody String parkMotors(){
        machine.motors.park();
        return "OK";
    }

    @GetMapping("/motors/people")
    public @ResponseBody String people(){
        machine.motors.people();
        return "OK";
    }

    @GetMapping("/motors/police")
    public @ResponseBody String police(){
        machine.motors.police();
        return "OK";
    }

    @GetMapping("/motors/samosval")
    public @ResponseBody String samosval(){
        machine.motors.samosval();
        return "OK";
    }

    @GetMapping("/motors/startFan")
    public @ResponseBody String startFan(){
        machine.motors.startFan();
        return "OK";
    }

    @GetMapping("/motors/stopFan")
    public @ResponseBody String stopFan(){
        machine.motors.stopFan();
        return "OK";
    }

    /*
    Drone
     */

    @GetMapping("/drone/park")
    public @ResponseBody String parkDrone(){
        machine.drone.park();
        return "OK";
    }

    @GetMapping("/drone/moveToAir")
    public @ResponseBody String moveToAir(){
        machine.drone.moveToAir();
        return "OK";
    }

    @GetMapping("/drone/moveToPloshadka")
    public @ResponseBody String moveToPloshadka(){
        machine.drone.moveToPloshadka();
        return "OK";
    }

    @GetMapping("/drone/moveToRubbishLeft")
    public @ResponseBody String moveToRubbishLeft(){
        machine.drone.moveToRubbishLeft();
        return "OK";
    }

    @GetMapping("/drone/moveToRubbishRight")
    public @ResponseBody String moveToRubbishRight(){
        machine.drone.moveToRubbishRight();
        return "OK";
    }

    @GetMapping("/drone/move")
    public @ResponseBody String move(@RequestParam int x, @RequestParam int y, @RequestParam int z){
        machine.drone.move(x, y, z);
        return "OK";
    }
}
