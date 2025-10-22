package org.company.controller;

import okhttp3.*;
import org.company.models.PlayerAchievements;
import org.company.service.AchievementsService;
import org.company.utility.ConfigurationReader;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class PlayerAchievementsController {
    private final String BASE_API_URL = ConfigurationReader.readConfigurationFile("BASE_API_URL");
    private final String API_KEY = ConfigurationReader.readConfigurationFile("API_KEY");
    private final String GAME_ID = ConfigurationReader.readConfigurationFile("GAME_ID");
    private final String PLAYER_ID = ConfigurationReader.readConfigurationFile("STEAM_ID");
    private AchievementsService service;
    public PlayerAchievementsController() {
        setupService();
    }

    public PlayerAchievements getSteamAchievements() {
        Call<PlayerAchievements> call = service.getPlayerAchievements();
        try {
            Response<PlayerAchievements> response = call.execute();
            return response.body();
        } catch (Exception ex) {
            System.out.println("Couldn't get achievements due to exception: " + ex);
        }
        return null;
    }
    private void setupService() {
        Interceptor clientInterceptor = setRepeatingQueryParameters();

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(clientInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_API_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .client(client)
                .build();

        service = retrofit.create(AchievementsService.class);
    }

    private Interceptor setRepeatingQueryParameters() {
        return chain -> {
            Request request = chain.request();
            HttpUrl url = request.url().newBuilder()
                    .addQueryParameter("key", API_KEY)
                    .addQueryParameter("appid", GAME_ID)
                    .addQueryParameter("steamid", PLAYER_ID)
                    .addQueryParameter("l", "en")
                    .build();
            request = request.newBuilder().url(url).build();
            return chain.proceed(request);
        };
    }
}
