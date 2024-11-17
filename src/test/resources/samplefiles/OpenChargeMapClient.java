package samplefiles;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OpenChargeMapClient {

    private static final String API_KEY = "b68d6b86-ae22-452b-a116-d4007dc44481"; // Replace with your API key
    private static final OkHttpClient client = new OkHttpClient();

    public static void main(String[] args) {
        double latitude = 51.54660;  // Example coordinates for Uxbridge
        double longitude = -0.47803;
        int distance = 10;  // Example distance in KM

        String response = fetchChargingPoints(latitude, longitude, distance);
        System.out.println("Response from Open Charge Map API: " + response);
    }

    public static String fetchChargingPoints(double latitude, double longitude, int distance) {
        String apiUrl = String.format(
                "https://api.openchargemap.io/v3/poi/?output=json&latitude=%f&longitude=%f&distance=%d&distanceunit=KM&key=%s",
                latitude, longitude, distance, API_KEY);

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
