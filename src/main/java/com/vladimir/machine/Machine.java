package com.vladimir.machine;

import com.vladimir.machine.devControllers.LightController;
import com.vladimir.machine.devControllers.MotorController;
import com.vladimir.machine.devControllers.UpController;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class Machine {
    public MotorController motors;
    public LightController light;
    public UpController drone;

    @Bean
    public void initDevControllers(){
        System.out.println("Init devControllers started");
        motors = new MotorController();
        light = new LightController();
        drone = new UpController();
        System.out.println("Init devControllers finished");
    }
}
