package com.alore.taskAssignment.dto;


import lombok.Data;
import lombok.ToString;

import java.sql.Timestamp;

@Data
@ToString
public class SearchDTO {

    private String time;
    private String city ;
    private Long noOfRequiredRooms;
}
