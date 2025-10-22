package org.company.service;

import org.company.models.LayoutsPayload;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LayoutsService {
    @POST("/generic-replicant/")
    Call<Void> sendAchievementsToLayouts(@Body LayoutsPayload payload);
}
