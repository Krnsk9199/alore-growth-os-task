package com.alore.taskAssignment.dto;


import com.alore.taskAssignment.entity.HotelFacilityMaster;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HotelDTO {

    private Long hotelId;
    private String hotelName;
    private String hotelAddress;
    private String comments;
    private Long rating;
    private Timestamp availabilityDate;
    private Long noOfRooms;
    private Long stayCost;
    private Map<String,Object> hotelFacility;



}
