package pl.gsps.service;

import pl.gsps.models.PlayerAchievements;
import retrofit2.Call;
import retrofit2.http.GET;

public interface AchievementsService {

    @GET("/ISteamUserStats/GetPlayerAchievements/v0001/")
    Call<PlayerAchievements> getPlayerAchievements();
}
