package com.alore.taskAssignment.controller;


import com.alore.taskAssignment.dto.*;
import com.alore.taskAssignment.service.ServiceInterface;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking.com")

public class ControllerClass {

    @Autowired
    private ServiceInterface service;


    @PostMapping(value = "/add_hotel_info", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseModel addHotelInfo(@RequestBody HotelDTO hotelDto) throws Exception {
        return service.insertHotelDetails(hotelDto);

    }

    @PostMapping(value = "/add_user_info", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseModel addUserInfo(@RequestBody UserDTO userDTO){
         return  service.insertUserDetails(userDTO);
    }

    @PostMapping(value = "/add_hotel_review", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseModel addReviews(@RequestBody ReviewDTO reviewDTO){
       return service.addReview(reviewDTO);
    }

    @PostMapping(value = "/delete_hotel_info", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseModel deleteHotelInfo(@RequestBody HotelDTO hotelDTO){
        return service.deleteHotelDetails(hotelDTO);
    }


    @PostMapping(value = "/delete_user_info", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseModel deleteUserInfo(@RequestBody UserDTO userDTO){
        return service.deleteUserDetails(userDTO);
    }

    @PostMapping(value = "/delete_review", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseModel deleteReview(@RequestBody ReviewDTO reviewDTO){
        return service.deleteReview(reviewDTO);

    }

    @PostMapping(value = "/sort_by_rating", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseModel sortByRating(@RequestBody ReviewDTO reviewDTO){
        return service.sortByRating();
    }

    @PostMapping(value = "/sort_by_cost", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseModel sortByRating(){
        return service.sortByStayCost();
    }

    @PostMapping(value = "/show_rating", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseModel showRating(@RequestBody HotelDTO hotelDTO){
        return service.showRating(hotelDTO);
    }

    @PostMapping(value = "/search_hotels", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseModel searchHotels(@RequestBody SearchDTO searchDTO){
        return service.searchHotels(searchDTO);
    }








}
