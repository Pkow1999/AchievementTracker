package org.company;

import org.company.controller.LayoutsController;
import org.company.models.LayoutsPayload;
import org.company.utility.ConfigurationReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

public class ScheduledWriter implements Runnable {
    private final AchievementTracker tracker;
    private LayoutsController layoutsController;
    private final Path filePath;
    private boolean useReplicant = false;
    public ScheduledWriter(AchievementTracker achievementTracker, String filename) {
        this.tracker = achievementTracker;
        this.filePath = Paths.get(filename);
        this.useReplicant = Boolean.parseBoolean(ConfigurationReader.readConfigurationFile("USE_GENERIC_REPLICANT"));

        if(useReplicant)
            this.layoutsController = new LayoutsController(ConfigurationReader.readConfigurationFile("LAYOUTS_URL"));

        try {
            if(!Files.exists(this.filePath)) {
                Files.createFile(this.filePath);
            }
        } catch (IOException e) {
            System.out.println("Could not create file due to exception: " + e);
        }

    }
    @Override
    public void run() {
        tracker.sendRequestAndSetValues();
        sendAchievementsToFile();
        if(useReplicant)
            sendAchievementsToReplicant();
    }
    private void sendAchievementsToFile() {
        String textToFile = String.format("%d/%d\nOstatnio zdobyte: %s",
                tracker.getNumberOfCurrentAchievements(),
                tracker.getNumberOfAllAchievements(),
                tracker.getNameOfLastAchievement());
        try {
            Files.write(this.filePath, textToFile.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.printf("\nNapisałem: \n\"%s\" \no godzinie: %s\n", textToFile, new Date());
    }
    private void sendAchievementsToReplicant() {
        LayoutsPayload payload = LayoutsPayload.builder()
                .lastAchieved(tracker.getNameOfLastAchievement())
                .number(String.format("%d/%d", tracker.getNumberOfCurrentAchievements(), tracker.getNumberOfAllAchievements()))
                .build();
        layoutsController.sendAchievements(payload);
    }
}
