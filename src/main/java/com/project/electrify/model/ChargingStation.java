package com.project.electrify.model;

import lombok.Data;

@Data
public class ChargingStation {
    private String name;
    private Boolean isPublic;
    private String address1;
    private String address2;
    private String town;
    private String postCode;
    private double latitude;
    private double longitude;

}

class Connection {
    private String type;
    private Boolean isOperational;
    private String level;
    private String currentType;
    private String currentDescription;
}