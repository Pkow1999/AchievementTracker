package org.company.controller;

import okhttp3.OkHttpClient;
import org.company.models.LayoutsPayload;
import org.company.service.LayoutsService;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class LayoutsController {
    private LayoutsService service;
    public LayoutsController(String LAYOUTS_URL) {
        setupService(LAYOUTS_URL);
    }

    public void sendAchievements(LayoutsPayload payload) {
        Call<Void> call = service.sendAchievementsToLayouts(payload);
        try {
            call.execute();
        } catch (Exception ex) {
            System.out.println("Couldn't send achievements due to exception: " + ex);
        }
    }

    private void setupService(String LAYOUTS_URL) {

        OkHttpClient client = new OkHttpClient.Builder()
                .cache(null)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LAYOUTS_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .client(client)
                .build();

        service = retrofit.create(LayoutsService.class);
    }
}
