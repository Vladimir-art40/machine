package com.vladimir.machine.controllers;

import com.vladimir.machine.AutoMode;
import com.vladimir.machine.Machine;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/script")
public class ScriptController {
    private final Machine machine;

    public ScriptController(Machine machine) {
        this.machine = machine;
    }

    @SneakyThrows
    @GetMapping("/aPolice")
    public @ResponseBody String aPolice(@RequestParam boolean bad){
        machine.drone.moveToPloshadka();
        AutoMode.doings.add("Проверяю детскую площадку.");
        machine.light.ploshadkaLight();
        Thread.sleep(4000);
        if(bad){
            AutoMode.doings.add("Обнаружено нарушение. Вызван наряд полиции.");
            machine.light.policeLight();
            machine.motors.police();
            AutoMode.doings.add("Доклад наряда полиции: Нарушение устранено.");
        }else{
            AutoMode.doings.add("Нарушений нет.");
        }
        machine.light.flyLight();
        return "OK";
    }

    @GetMapping("/aPeople")
    public @ResponseBody String aPeople(){
        machine.motors.people();
        AutoMode.doings.add("Проверяю работу системы безопасности пешеходного перехода. Система работает.");
        return "OK";
    }

    @SneakyThrows
    @GetMapping("/aRubbish")
    public @ResponseBody String aRubbish(@RequestParam String c, @RequestParam boolean find){
        if(c.equals("left")){
            machine.drone.moveToRubbishLeft();
            AutoMode.doings.add("Проверяю мусорные баки.");
            machine.light.rubbishLeftLight();
            Thread.sleep(2000);
            if(find){
                AutoMode.doings.add("Мусорные контейнеры переполнены. Вызван мусоровоз.");
                machine.light.rubbishFoundLight();
                machine.motors.samosval();
                AutoMode.doings.add("Доклад мусоровоза: контейнеры очищены.");
            }else{
                AutoMode.doings.add("Всё чисто.");
            }
        }else if(c.equals("right")){
            machine.drone.moveToRubbishRight();
            AutoMode.doings.add("Проверяю мусорные баки.");
            machine.light.rubbishRightLight();
            Thread.sleep(2000);
            AutoMode.doings.add("Всё чисто.");
        }
        machine.light.flyLight();
        return "OK";
    }

    @SneakyThrows
    @GetMapping("/aAir")
    public @ResponseBody String aAir(@RequestParam boolean state){
        machine.drone.moveToAir();
        AutoMode.doings.add("Проверяю загрязненность воздуха.");
        machine.light.airLight();
        Thread.sleep(6000);
        if(state){
            machine.motors.stopFan();
            AutoMode.doings.add("Воздух в пределах нормы. Установка очистки воздуха отключена.");
        }else{
            machine.light.airBadLight();
            Thread.sleep(2000);
            machine.motors.startFan();
            AutoMode.doings.add("Воздух загрязнен. Включена установка очистки воздуха.");
        }
        machine.light.flyLight();
        return "OK";
    }

    @GetMapping("/randomFly")
    public @ResponseBody String randomFly(){
        machine.drone.moveToRandomOnAir();
        return "OK";
    }

    @GetMapping("/randomFlyOnGround")
    public @ResponseBody String randomFlyOnGround(){
        machine.drone.moveToRandomOnGround();
        return "OK";
    }
}
