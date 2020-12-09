package com.company;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.FileAttribute;
import java.sql.Timestamp;
import java.util.Date;

public class Logger {

    private static final Path DIR_PATH = Path.of("logs");
    private static final Path FILE_PATH = Path.of("logs", "server-log.txt");

    public static void log(String command){
        String log = getTimeStamp() + " - " + command + "\n";
        try {
            Files.writeString(FILE_PATH, log, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Unexpected IO exception");
        }
    }

    private static String getTimeStamp (){
        Date date= new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);
        System.out.println(ts);
        return ts.toString();
    }

    public static void createLog(){
        createDirectory();
        createFile();
    }

    private static void createFile() {
        try {
//            Files.delete(FILE_PATH);
            Files.createFile(FILE_PATH);
            System.out.println("File created");
        } catch (FileAlreadyExistsException e) {
            System.out.println("File already exists");
        } catch (IOException e) {
            System.out.println("Encountered unexpected exception");
        }
    }

    private static void createDirectory() {
        try {
            Files.createDirectory(DIR_PATH);
            System.out.println("Directory created");
        } catch (FileAlreadyExistsException e) {
            System.out.println("Directory already exists");
        } catch (IOException e) {
            System.out.println("Encountered unexpected exception");
        }
    }

}
