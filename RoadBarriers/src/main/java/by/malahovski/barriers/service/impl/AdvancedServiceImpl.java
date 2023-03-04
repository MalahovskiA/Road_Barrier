package by.malahovski.barriers.service.impl;



import by.malahovski.barriers.service.UserDetailsImpl;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;

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
                System.err.println(e);
            }
        }
        try (BufferedWriter bw = Files.newBufferedWriter(newFilePath, StandardCharsets.UTF_8)) {
            Date date =  new Date();
            bw.write(date.toString());
            bw.write(userDetails.getUsername());
            bw.write(userDetails.getEmail());
            bw.write(String.valueOf(userDetails.isEnabled()));
            bw.write(String.valueOf(userDetails.isAccountNonExpired()));
            System.out.println("File written");
            try {
                Files.writeString(newFilePath, userDetails.toString(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
