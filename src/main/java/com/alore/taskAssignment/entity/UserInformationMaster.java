package com.alore.taskAssignment.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "user_info", schema = "task")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserInformationMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_info_id")
    private Long userInfoId;
    @ManyToOne
    private HotelInformationMaster hotelInfo;

    @Column(name = "gender")
    private String gender ;

    @Column(name = "user_name")
    private String userName ;

    @Column(name = "residential_city")
    private String resCity ;

    @Column(name = "user_rating")
    private Long userRating ;

    @Column(name = "comments")
    private String comments ;




}
