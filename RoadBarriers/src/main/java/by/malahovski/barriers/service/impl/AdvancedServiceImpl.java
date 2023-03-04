package by.malahovski.barriers.service.impl;


import by.malahovski.barriers.service.UserDetailsImpl;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class AdvancedServiceImpl implements Runnable {

    private final UserDetailsImpl userDetails;

    public AdvancedServiceImpl(UserDetailsImpl userDetails) {
        this.userDetails = userDetails;
    }

    @Override
    public void run() {
        System.out.println("thread named " + Thread.currentThread().getName() + " started");
        Path newFilePath = Paths.get("D:", "LOG.txt");
        if (!Files.exists(newFilePath)) {
            try {
                Files.createFile(newFilePath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try (BufferedWriter bw = Files.newBufferedWriter(newFilePath, StandardCharsets.UTF_8,StandardOpenOption.APPEND)) {
            Date date = new Date();
            bw.write("User: " + userDetails.getUsername() + " ");
            bw.write("with email: " + userDetails.getEmail() + " ");
            bw.write("login: " + date + " ");
            bw.write("account is Enabled: " + userDetails.isEnabled() + " ");
            bw.write("account is NonExpired : " +userDetails.isAccountNonExpired() + "\n");
            System.out.println("File written");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
