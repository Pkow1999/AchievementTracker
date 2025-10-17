package pl.gsps.service;

import pl.gsps.models.PlayerAchievements;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AchievementsService {

    @GET("/ISteamUserStats/GetPlayerAchievements/v0001/")
    Call<PlayerAchievements> getPlayerAchievements(@Query("steamid") String steamid,
                                                   @Query("appid") String appid,
                                                   @Query("key") String API_KEY);
}
