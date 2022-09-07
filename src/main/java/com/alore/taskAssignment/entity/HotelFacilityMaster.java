package com.alore.taskAssignment.entity;


import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "hotel_facility", schema = "task")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class HotelFacilityMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_facility_id")
    private Long hotelFacilityId;

    @OneToOne(mappedBy = "hotelFacility")
    private HotelInformationMaster hotelInformation;

    @Column(name = "wifi")
    private boolean wifi ;

    @Column(name = "restaurant_available")
    private boolean restaurantAvailable ;

    @Column(name = "air_conditioning")
    private boolean airConditioning ;

    @Column(name = "meals")
    private boolean meals ;

}
