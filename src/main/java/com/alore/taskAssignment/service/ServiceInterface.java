package com.alore.taskAssignment.service;

import com.alore.taskAssignment.dto.*;

public interface ServiceInterface {

    ResponseModel addReview(ReviewDTO reviewDTO);

    ResponseModel insertHotelDetails(HotelDTO hotelDTO);

    ResponseModel insertUserDetails(UserDTO userDTO);

    ResponseModel deleteReview(ReviewDTO reviewDTO);

    ResponseModel deleteHotelDetails(HotelDTO hotelDTO);

    ResponseModel deleteUserDetails(UserDTO userDTO);

    ResponseModel sortByRating();
    ResponseModel sortByStayCost();

    ResponseModel showRating(HotelDTO hotelDTO);
    ResponseModel searchHotels(SearchDTO searchDTO);
}
