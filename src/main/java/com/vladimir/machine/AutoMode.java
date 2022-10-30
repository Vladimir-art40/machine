package com.vladimir.machine;

import com.vladimir.machine.controllers.ScriptController;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@EnableScheduling
@EnableAsync
public class AutoMode {

    private final ScriptController scriptController;

    public static volatile boolean isEnabledAutoMode = false;
    public static volatile boolean isBusy = false;
    public static volatile ArrayList<String> doings = new ArrayList<>();

    public AutoMode(ScriptController scriptController) {
        this.scriptController = scriptController;
    }

    @Async
    @Scheduled(fixedRate = 500)
    public void autoModeSpin() {
        if (isEnabledAutoMode && !isBusy) {
            isBusy = true;
            System.out.println("New autotask");
            int decOfAction = (int) (Math.random() * 100 + 1);
            if (decOfAction <= 10) {
                boolean bad = Math.random() * 10 > 4;
                scriptController.aPolice(bad);
            } else if (decOfAction <= 20) {
                boolean bad = Math.random() * 10 > 4;
                scriptController.aAir(bad);
            } else if (decOfAction <= 30) {
                scriptController.aPeople();
            } else if (decOfAction <= 40) {
                boolean bad = Math.random() * 10 > 4;
                scriptController.aRubbish("left", bad);
            } else if (decOfAction <= 50) {
                scriptController.aRubbish("right", false);
            } else if (decOfAction <= 75) {
                scriptController.randomFly();
            } else if (decOfAction <= 100) {
                scriptController.randomFlyOnGround();
            }
            isBusy = false;
        }
    }

}
