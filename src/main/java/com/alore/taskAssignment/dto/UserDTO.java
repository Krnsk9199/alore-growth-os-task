package com.alore.taskAssignment.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String userName;
    private String gender;
    private String city;
    private  Long rating;
    private String comments ;
    private Long hotelId;

}
