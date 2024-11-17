package com.project.electrify.service;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

@Service
public class OpenChargeMapService {
    private final OkHttpClient client = new OkHttpClient();

    public String fetchChargingPoints(double latitude, double longitude, int distance, String apiKey) {
        String apiUrl = String.format(
                "https://api.openchargemap.io/v3/poi/?output=json&latitude=%f&longitude=%f&distance=%d&distanceunit=KM&key=%s",
                latitude, longitude, distance, apiKey);

        Request request = new Request.Builder()
                .url(apiUrl)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                return response.body().string();
            } else {
                return "Request failed. HTTP Error code: " + response.code();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "An error occurred: " + e.getMessage();
        }
    }
}
