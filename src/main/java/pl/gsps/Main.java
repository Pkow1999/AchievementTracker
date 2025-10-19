package pl.gsps;

import pl.gsps.utility.ConfigurationReader;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args)  {
        int time = Integer.parseInt(ConfigurationReader.readConfigurationFile("TIME_IN_SECONDS"));
        AchievementTracker controller = new AchievementTracker();
        ScheduledWriter scheduledWriter = new ScheduledWriter(controller, "out.txt");
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(scheduledWriter, 0,time, TimeUnit.SECONDS);
    }
}