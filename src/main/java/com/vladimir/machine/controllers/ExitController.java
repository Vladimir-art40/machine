package com.vladimir.machine.controllers;

import com.vladimir.machine.AutoMode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
@RequestMapping("/")
public class ExitController {
    private final AutoMode autoMode;

    public ExitController(AutoMode autoMode) {
        this.autoMode = autoMode;
    }

    @GetMapping("")
    public String red(){
        return "redirect:/manual/";
    }

    @GetMapping("cont")
    public @ResponseBody ArrayList<String> getActions(){
        return autoMode.doings;
    }
}
