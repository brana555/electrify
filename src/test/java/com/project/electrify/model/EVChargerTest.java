package com.project.electrify.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class EVChargerTest {
    @Test
    void testEVChargerSettersAndGetters() {
        // Create an instance of EVCharger
        EVCharger charger = new EVCharger();

        // Set values
        charger.setId("1");
        charger.setUuid("1234-5678-uuid");
        charger.setTitle("Main Street Charger");
        charger.setAddressLine1("123 Main St");
        charger.setTown("London");
        charger.setPostcode("E1 6AN");
        charger.setLatitude(51.5074);
        charger.setLongitude(-0.1278);
        charger.setDistance(1.2);
        charger.setUsageCost("Free");

        EVCharger.StatusType statusType = new EVCharger.StatusType();
        statusType.setTitle("Available");
        statusType.setOperational(true);
        charger.setStatusType(statusType);

        EVCharger.Connection connection = new EVCharger.Connection();
        connection.setReference("Ref-123");
        connection.setConnectionTypeTitle("Type 2");
        connection.setPowerKW(22.0);
        connection.setLevelTitle("Level 2");
        connection.setCurrentTypeTitle("AC");

        List<EVCharger.Connection> connections = new ArrayList<>();
        connections.add(connection);
        charger.setConnections(connections);

        // Validate values using getters
        assertEquals("1", charger.getId());
        assertEquals("1234-5678-uuid", charger.getUuid());
        assertEquals("Main Street Charger", charger.getTitle());
        assertEquals("123 Main St", charger.getAddressLine1());
        assertEquals("London", charger.getTown());
        assertEquals("E1 6AN", charger.getPostcode());
        assertEquals(51.5074, charger.getLatitude());
        assertEquals(-0.1278, charger.getLongitude());
        assertEquals(1.2, charger.getDistance());
        assertEquals("Free", charger.getUsageCost());

        // Validate nested StatusType
        assertNotNull(charger.getStatusType());
        assertEquals("Available", charger.getStatusType().getTitle());
        assertTrue(charger.getStatusType().isOperational());

        // Validate nested Connection
        assertNotNull(charger.getConnections());
        assertEquals(1, charger.getConnections().size());
        EVCharger.Connection fetchedConnection = charger.getConnections().get(0);
        assertEquals("Ref-123", fetchedConnection.getReference());
        assertEquals("Type 2", fetchedConnection.getConnectionTypeTitle());
        assertEquals(22.0, fetchedConnection.getPowerKW());
        assertEquals("Level 2", fetchedConnection.getLevelTitle());
        assertEquals("AC", fetchedConnection.getCurrentTypeTitle());
    }

}
