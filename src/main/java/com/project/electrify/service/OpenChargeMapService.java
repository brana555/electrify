package com.project.electrify.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.project.electrify.dto.ChargingPointsResponse;
import com.project.electrify.model.EVCharger;
import com.project.electrify.repository.EVChargerRepository;
import com.project.electrify.repository.RawOpenChargeMapApiResponseRepository;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OpenChargeMapService {
    @Autowired
    private RawOpenChargeMapApiResponseRepository rawOpenChargeMapApiResponseRepository;
    @Autowired
    private EVChargerRepository evChargerRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    private final Gson gson = new Gson();

    private final OkHttpClient client = new OkHttpClient();


    public ChargingPointsResponse fetchAndSaveChargingPoints(double latitude, double longitude, int distance, String apiKey) {
        String apiUrl = String.format(
                "https://api.openchargemap.io/v3/poi/?output=json&latitude=%f&longitude=%f&distance=%d&distanceunit=KM&key=%s",
                latitude, longitude, distance, apiKey);

        Request request = new Request.Builder()
                .url(apiUrl)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String jsonResponse = response.body().string();
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode rootNode = objectMapper.readTree(jsonResponse);

                List<EVCharger> allChargers = new ArrayList<>();
                List<EVCharger> savedChargers = new ArrayList<>();
                int totalNoOfChargers = 0;

                for (JsonNode node : rootNode) {
                    totalNoOfChargers++;
                    EVCharger charger = new EVCharger();

                    charger.setUuid(getSafeText(node, "UUID"));

                    // Check if the UUID already exists in the database
                    Query query = new Query(Criteria.where("uuid").is(charger.getUuid()));
                    EVCharger existingCharger = mongoTemplate.findOne(query, EVCharger.class);

                    if (existingCharger != null) {
                        // Add existing charger to the response but skip saving
                        allChargers.add(existingCharger);
                        continue;
                    }

                    // Map new charger details
                    JsonNode addressInfo = node.get("AddressInfo");
                    if (addressInfo != null) {
                        charger.setTitle(getSafeText(addressInfo, "Title"));
                        charger.setAddressLine1(getSafeText(addressInfo, "AddressLine1"));
                        charger.setTown(getSafeText(addressInfo, "Town"));
                        charger.setPostcode(getSafeText(addressInfo, "Postcode"));
                        charger.setLatitude(getSafeDouble(addressInfo, "Latitude"));
                        charger.setLongitude(getSafeDouble(addressInfo, "Longitude"));
                        charger.setDistance(getSafeDouble(addressInfo, "Distance"));
                    }

                    charger.setUsageCost(getSafeText(node, "UsageCost"));

                    JsonNode statusType = node.get("StatusType");
                    if (statusType != null) {
                        EVCharger.StatusType status = new EVCharger.StatusType();
                        status.setTitle(getSafeText(statusType, "Title"));
                        status.setOperational(getSafeBoolean(statusType, "IsOperational"));
                        charger.setStatusType(status);
                    }

                    JsonNode connectionsNode = node.get("Connections");
                    if (connectionsNode != null) {
                        List<EVCharger.Connection> connections = new ArrayList<>();
                        for (JsonNode connectionNode : connectionsNode) {
                            EVCharger.Connection connection = new EVCharger.Connection();
                            connection.setReference(getSafeText(connectionNode, "Reference"));
                            JsonNode connectionType = connectionNode.get("ConnectionType");
                            if (connectionType != null) {
                                connection.setConnectionTypeTitle(getSafeText(connectionType, "Title"));
                            }
                            connection.setPowerKW(getSafeDouble(connectionNode, "PowerKW"));
                            JsonNode level = connectionNode.get("Level");
                            if (level != null) {
                                connection.setLevelTitle(getSafeText(level, "Title"));
                            }
                            JsonNode currentType = connectionNode.get("CurrentType");
                            if (currentType != null) {
                                connection.setCurrentTypeTitle(getSafeText(currentType, "Title"));
                            }
                            connections.add(connection);
                        }
                        charger.setConnections(connections);
                    }

                    savedChargers.add(charger);
                    allChargers.add(charger);
                }

                // Save only new chargers to MongoDB
                if (!savedChargers.isEmpty()) {
                    mongoTemplate.insertAll(savedChargers);
                }

                return new ChargingPointsResponse(
                        "Data fetched successfully. New chargers saved to the database.",
                        savedChargers.size(),
                        totalNoOfChargers,
                        allChargers
                );
            } else {
                throw new RuntimeException("Request failed. HTTP Error code: " + response.code());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("An error occurred: " + e.getMessage());
        }
    }


    // Helper Methods
    private String getSafeText(JsonNode node, String fieldName) {
        return node.has(fieldName) && !node.get(fieldName).isNull() ? node.get(fieldName).asText() : null;
    }

    private double getSafeDouble(JsonNode node, String fieldName) {
        return node.has(fieldName) && !node.get(fieldName).isNull() ? node.get(fieldName).asDouble() : 0.0;
    }

    private boolean getSafeBoolean(JsonNode node, String fieldName) {
        return node.has(fieldName) && !node.get(fieldName).isNull() && node.get(fieldName).asBoolean();
    }
}
