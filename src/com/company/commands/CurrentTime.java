package com.company.commands;

import java.time.LocalTime;

public class CurrentTime implements Command {
    @Override
    public String execute() {
        return LocalTime.now().toString();
    }
}
