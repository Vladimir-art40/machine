package com.vladimir.machine.devControllers;

import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Scanner;

public class AbstractController {
    private PrintWriter out;
    private Scanner in;
    private final String address;
    private volatile boolean ok = true;

    public AbstractController(String address) {
        this.address = address;
        try {
            out = new PrintWriter(address);
            in = new Scanner(new FileInputStream(address));
        }catch (IOException e){
            e.printStackTrace();
            System.err.println("Init controller error");
            System.exit(1);
        }

        Thread listenDev = new Thread(() -> {
            while (true){
                String u = in.nextLine();
                System.out.println("[" + Calendar.getInstance().getTime() + "] " +
                        "(dev: " + address + "): " + u);
                if(u.equals("OK")){
                    ok = true;
                }
            }
        }, "Listen dev on " + address);
        listenDev.start();
    }
    public void sendCommand(String command){
        out.print(command);
        out.flush();
    }


    @SneakyThrows
    public void trustSendCommand(String command) {
        System.out.println("[" + Calendar.getInstance().getTime() + "] " +
                "(dev: " + address + ") Sending: " + command);
        ok = false;
        out.print(command);
        out.flush();
        int w = 0;
        while (!ok) {
            w++;
            Thread.sleep(10);
            if(w == 2000){
                break;
            }
        }
        ok = true;
    }
}
