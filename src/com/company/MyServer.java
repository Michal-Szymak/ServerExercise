package com.company;

import com.company.commands.Command;
import com.company.commands.CurrentTime;
import com.company.commands.History;
import com.company.commands.TimeServerActive;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MyServer {

    public Map<String, Command> commandMap = new HashMap<>();

    public MyServer() {
        commandMap.put("current_time", new CurrentTime());
        commandMap.put("history", new History());
        commandMap.put("time_server_active", new TimeServerActive());
    }

    public void start() {
        try(ServerSocket serverSocket = new ServerSocket(9991)) {
            Socket connectionSocket = serverSocket.accept();

            InputStream inputToServer = connectionSocket.getInputStream();
            OutputStream outputFromServer = connectionSocket.getOutputStream();

            Scanner scanner = new Scanner(inputToServer, "UTF-8");
            PrintWriter serverPrintOut = new PrintWriter
                    (new OutputStreamWriter(outputFromServer, "UTF-8"), true);

            serverPrintOut.println("Hello!");

            Logger.createLog();
            boolean notDone = true;
            while(notDone){
                serverPrintOut.println("Please type in command (type \"out\" to exit)");
                String s = scanner.nextLine().toLowerCase();
                Logger.log(s);
                Command command;

                System.out.println(s);
                for(String availableCommand : commandMap.keySet()){
                    if(s.equals(availableCommand)){
                        command = commandMap.get(availableCommand);
                        System.out.println(command.execute());
                        serverPrintOut.println(command.execute());
                    }
                }

                if(s.equals("out")){
                    notDone = false;
                }
            }


        } catch (IOException e) {
            System.out.println("Encountered unexpected exception");
        }
    }
}
