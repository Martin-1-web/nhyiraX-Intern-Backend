package com.martin.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import static java.nio.file.Files.getFileStore;
import static java.nio.file.Files.readAllLines;

public class UserManager {

    private  static final String USERS_FILE = "users/users.txt";

    public static boolean userExists(String userName) {

        try {
            List<String> lines = readAllLines(Paths.get(USERS_FILE));
            for (String line : lines) {
                String[] parts = line.split(":");

                if (parts[0].equals(userName)) {
                    return true;
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return  false;
    }
    public static boolean registerUser(String userName, String password) {

        if (userExists(userName)) {return false;} // User already exists

        try {
            Files.createDirectories(Paths.get("users"));
            Files.write(Paths.get(USERS_FILE), (userName + ":" + password + "\n").getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            return  true;
        } catch (IOException e) {
            return false;
        }
    }

    public static boolean validateLogin(String userName, String password) {

        try {
            List<String> lines = Files.readAllLines(Paths.get(USERS_FILE));
            for (String line : lines) {
                String[] parts = line.split(":");
                if (parts[0].equals(userName) && parts[1].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            return false;
        }
        return  false;
    }
}
