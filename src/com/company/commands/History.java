package com.company.commands;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class History implements Command {

    private static final Path FILE_PATH = Path.of("logs", "server-log.txt");

    @Override
    public String execute() {
        List<String> stringList = new ArrayList<>();
        try {
            stringList = Files.readAllLines(FILE_PATH);
        } catch (IOException e) {
            System.out.println("Unexpected exception occured.");
        }

        StringBuilder sb = new StringBuilder();
        for (String s: stringList) {
            sb.append(s).append("\n");
        }

        return sb.toString();
    }
}
