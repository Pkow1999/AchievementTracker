package org.company.service;

import org.company.models.PlayerAchievements;
import retrofit2.Call;
import retrofit2.http.GET;

public interface AchievementsService {

    @GET("/ISteamUserStats/GetPlayerAchievements/v0001/")
    Call<PlayerAchievements> getPlayerAchievements();
}
