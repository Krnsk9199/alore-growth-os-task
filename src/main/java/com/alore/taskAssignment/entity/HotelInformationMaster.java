package com.alore.taskAssignment.entity;


import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "hotel_info", schema = "task")
@Getter
@Setter
public class HotelInformationMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_id")
    private Long hotelId;

    @Column(name = "hotel_name")
    private String hotelName;

    @Column(name = "hotel_address")
    private String hotelAddress;

    @Column(name = "rating")
    private Long rating;

    @Column(name = "comments")
    private String comments;

    @Column(name = "availability_date")
    private Timestamp dateOfAvailability;

    @Column(name = "no_of_rooms")
    private Long noOfRoomsRequired;

    @Column(name = "cost_of_stay")
    private Long stayCost;

    @OneToMany(mappedBy = "hotelInfo")
    private List<UserInformationMaster> userInfo;

    @OneToOne
    private HotelFacilityMaster hotelFacility;




}
