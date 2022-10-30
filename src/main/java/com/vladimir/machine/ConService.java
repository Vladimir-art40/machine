package com.vladimir.machine;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.net.Socket;

@Service
@EnableScheduling
public class ConService {

    private volatile boolean availableConnection = false;
    private volatile PrintWriter printWriter;

    @Scheduled(fixedDelay = 1000)
    public void connect(){
        if(AutoMode.doings.size() < 10)
            for (int i = 0; i < 10; i++) {
                AutoMode.doings.add("Пустое действие");
            }
        if(!availableConnection) {
            try {
                System.out.println("Попытка подключиться");
                Socket socket = new Socket("87.249.44.127", 999);
                printWriter = new PrintWriter(socket.getOutputStream());
                availableConnection = true;
            } catch (Exception e) {
                System.err.println("Попытка подключения провалена");
                availableConnection = false;
            }
        }
    }

    @Scheduled(fixedDelay = 500)
    public void send(){
        if(availableConnection) {
            try {
                printWriter.println("123");
                for (int i = AutoMode.doings.size() - 10; i < AutoMode.doings.size(); i++) {
                    printWriter.println(AutoMode.doings.get(i));
                }
                printWriter.flush();
            } catch (Exception e) {
                System.err.println("Попытка отправки провалена");
                availableConnection = false;
            }
        }
    }
}
