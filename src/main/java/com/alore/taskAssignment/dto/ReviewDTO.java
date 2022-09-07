package com.alore.taskAssignment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {

    private Long rating;
    private String comments;
    private String hotelName;
    private Long hotelId;


}
