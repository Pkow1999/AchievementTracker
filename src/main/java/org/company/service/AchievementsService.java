package org.company.service;

import org.company.models.PlayerAchievements;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface AchievementsService {

    @Headers("Cache-Control: no-cache")
    @GET("/ISteamUserStats/GetPlayerAchievements/v0001/")
    Call<PlayerAchievements> getPlayerAchievements();
}
