package com.project.electrify.service;

import com.project.electrify.model.City;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class OpenChargeMapService {
    private final OkHttpClient client = new OkHttpClient();
    private final CityService cityService;

    public String fetchChargingPointsByLatitudeLongitude(double latitude, double longitude, int distance, String apiKey) {
        City city = cityService.getByLatitudeLongitude(latitude, longitude);

        if (city != null) {
            return city.toString();
        } else {
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

    public String fetchChargingPointsByCity(String givenCity) {
        if (Strings.isBlank(givenCity)) {
            return null;
        }

        City city = cityService.getByCity(givenCity);

        if (city != null) {
            return city.toString();
        }

        return null;
    }

}
