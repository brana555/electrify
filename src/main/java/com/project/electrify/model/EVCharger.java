package com.project.electrify.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Setter
@Getter
@Document(collection = "chargers")
public class EVCharger {
    // Getters and Setters for EVCharger
    @Id
    private String id;
    private String uuid;
    private String title; // From AddressInfo Title
    private String addressLine1; // From AddressInfo AddressLine1
    private String town; // From AddressInfo Town
    private String postcode; // From AddressInfo Postcode
    private double latitude; // From AddressInfo Latitude
    private double longitude; // From AddressInfo Longitude
    private double distance; // From AddressInfo Distance
    private String usageCost; // From UsageCost
    private StatusType statusType; // From StatusType
    private List<Connection> connections; // From Connections

    // Nested Classes
    public static class StatusType {
        private boolean isOperational;
        @Setter
        @Getter
        private String title;

        // Getters and Setters
        public boolean isOperational() {
            return isOperational;
        }

        public void setOperational(boolean operational) {
            isOperational = operational;
        }

    }

    @Setter
    @Getter
    public static class Connection {
        // Getters and Setters
        private String reference;
        private String connectionTypeTitle; // From ConnectionType Title
        private double powerKW; // From PowerKW
        private String levelTitle; // From Level Title
        private String currentTypeTitle; // From CurrentType Title

    }

}
