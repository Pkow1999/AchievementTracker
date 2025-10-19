package org.company;

import lombok.Getter;
import org.company.controller.PlayerAchievementsController;
import org.company.models.Achievement;

import java.util.Comparator;
import java.util.List;

public class AchievementTracker {
    private final PlayerAchievementsController controller;
    @Getter
    private final long numberOfAllAchievements;
    @Getter
    private String nameOfLastAchievement = "";
    @Getter
    private long numberOfCurrentAchievements;

    public AchievementTracker() {
        this.controller = new PlayerAchievementsController();
        numberOfAllAchievements = controller.getSteamAchievements().getPlayerstats().getAchievements().size();
        this.numberOfCurrentAchievements = 0;
    }

    private List<Achievement> getAchievements() {
        return controller.getSteamAchievements().getPlayerstats().getAchievements();
    }

    public void sendRequestAndSetValues() {
        List<Achievement> achievementList = getAchievements();
        this.numberOfCurrentAchievements = achievementList
                .stream()
                .filter(achievement -> achievement.getAchieved() > 0)
                .count();
        this.nameOfLastAchievement = achievementList
                .stream()
                .max(Comparator.comparing(Achievement::getUnlocktime))
                .filter(achievement -> achievement.getAchieved() > 0)
                .orElse(Achievement.builder().name("").build())
                .getName();
    }
}
