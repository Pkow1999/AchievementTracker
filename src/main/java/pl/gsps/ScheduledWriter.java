package pl.gsps;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

public class ScheduledWriter implements Runnable {
    private final AchievementTracker tracker;
    Path filePath;
    public ScheduledWriter(AchievementTracker controller, String filename) {
        this.tracker = controller;
        this.filePath = Paths.get(filename);
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
        String textToFile = String.format("%d/%d\nOstatnio zdobyty: %s",
                tracker.getNumberOfCurrentAchievements(),
                tracker.getNumberOfAllAchievements(),
                tracker.getNameOfLastAchievement());
        System.out.printf("\nNapisałem: \n\"%s\" \no godzinie: %s\n", textToFile, new Date());
        try {
            Files.write(this.filePath, textToFile.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
