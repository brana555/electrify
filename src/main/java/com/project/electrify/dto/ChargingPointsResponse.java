package com.project.electrify.dto;

import com.project.electrify.model.EVCharger;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ChargingPointsResponse {
    private String message;
    private int savedCount;
    private int totalNoOfChargers;
    private List<EVCharger> savedChargers;
}
