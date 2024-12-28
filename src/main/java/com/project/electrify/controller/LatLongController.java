package com.project.electrify.controller;


import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class LatLongController {

    private static final String NOMINATIM_URL = "https://nominatim.openstreetmap.org/search?format=json&q=";

    public static void main(String[] args) {
        try {
            // Taking address as input
            String address = "se2 9lp";  // Sample address
            String encodedAddress = encodeAddress(address);

            // Send request to Nominatim API
            String response = sendRequestToNominatim(encodedAddress);

            // Parse the response and extract lat/long
            parseAndPrintLatLong(response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Encode the address string to be URL-safe
    private static String encodeAddress(String address) throws UnsupportedEncodingException {
        return URLEncoder.encode(address, "UTF-8");
    }

    // Send the HTTP GET request to Nominatim API and get the response
    private static String sendRequestToNominatim(String encodedAddress) throws Exception {
        String urlString = NOMINATIM_URL + encodedAddress;
        URL url = new URL(urlString);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", "Java AddressToLatLong");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

    // Parse the JSON response and print the latitude and longitude
    private static void parseAndPrintLatLong(String response) {
        // The response is a JSON array, so we need to parse it
        if (response.startsWith("[")) {
            JSONObject jsonObject = new JSONObject(response.substring(1, response.length() - 1));
            if (jsonObject.length() > 0) {
                String lat = jsonObject.getString("lat");
                String lon = jsonObject.getString("lon");
                System.out.println("Latitude: " + lat);
                System.out.println("Longitude: " + lon);
            } else {
                System.out.println("No results found.");
            }
        } else {
            System.out.println("Error: Invalid JSON response.");
        }
    }
}
