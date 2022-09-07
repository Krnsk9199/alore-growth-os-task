package com.alore.taskAssignment.service.impl;

import com.alore.taskAssignment.dto.*;
import com.alore.taskAssignment.entity.HotelFacilityMaster;
import com.alore.taskAssignment.entity.HotelInformationMaster;
import com.alore.taskAssignment.entity.UserInformationMaster;
import com.alore.taskAssignment.repository.HotelDetailsRepository;
import com.alore.taskAssignment.repository.HotelFacilityRepository;
import com.alore.taskAssignment.repository.UserDetailsRepository;
import com.alore.taskAssignment.service.ServiceInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

@Log4j2
@RequiredArgsConstructor
@Service
public class ServiceImpl implements ServiceInterface {

    private final HotelDetailsRepository hotelDetailsRepository;
    private final UserDetailsRepository userDetailsRepository;

    private final HotelFacilityRepository hotelFacilityRepository;

    public ResponseModel addReview(ReviewDTO reviewDTO) {
        try{
            Optional<HotelInformationMaster> hotelDTOOptional = hotelDetailsRepository.findByHotelName(reviewDTO.getHotelName());
            if(hotelDTOOptional.isPresent()){
                HotelInformationMaster hotelInformationMaster = hotelDTOOptional.get();
                hotelInformationMaster.setComments(reviewDTO.getComments());
                hotelInformationMaster.setRating(reviewDTO.getRating());
                hotelDetailsRepository.save(hotelInformationMaster);
                return new ResponseModel("Api executed successfully", "SUCCESS", "Review details added successfully");
            }
            return new ResponseModel("Api Executed successfully ","SUCCESS", "Hotel name not found");
        }
        catch (Exception e){
            return new ResponseModel("Api not executed", "Failure", e.getMessage());
        }

    }

    public ResponseModel insertHotelDetails(HotelDTO hotelDTO) {
        try {
            Optional<HotelInformationMaster> hotelDTOOptional = hotelDetailsRepository.findByHotelName(hotelDTO.getHotelName());
            if(!hotelDTOOptional.isPresent()){
                HotelInformationMaster hotelInformationMaster = new HotelInformationMaster();
                hotelInformationMaster.setHotelAddress(hotelDTO.getHotelAddress());
                hotelInformationMaster.setHotelName(hotelDTO.getHotelName());
                hotelInformationMaster.setComments(hotelDTO.getComments());
                hotelInformationMaster.setRating(hotelDTO.getRating());
                hotelInformationMaster.setNoOfRoomsRequired(hotelDTO.getNoOfRooms());
                hotelInformationMaster.setStayCost(hotelDTO.getStayCost());

                HotelFacilityMaster hotelFacilityMaster = new HotelFacilityMaster();
                hotelFacilityMaster.setMeals((Boolean) hotelDTO.getHotelFacility().get("meals"));
                hotelFacilityMaster.setWifi((Boolean) hotelDTO.getHotelFacility().get("wifi"));
                hotelFacilityMaster.setAirConditioning((Boolean) hotelDTO.getHotelFacility().get("airConditioning"));
                hotelFacilityMaster.setRestaurantAvailable((Boolean) hotelDTO.getHotelFacility().get("restaurantAvailable"));
                hotelFacilityRepository.save(hotelFacilityMaster);
                hotelInformationMaster.setHotelFacility(hotelFacilityMaster);
                hotelDetailsRepository.save(hotelInformationMaster);
                return new ResponseModel("Api executed successfully", "SUCCESS", "Hotel details added successfully");
            }
            return new ResponseModel("Api Executed successfully ","SUCCESS", "Hotel details already exists");
        }
        catch (Exception e){
            return new ResponseModel("Api not executed", "Failure", e.getMessage());
        }

    }


    public ResponseModel insertUserDetails(UserDTO userDTO) {
        try {
            Optional<UserInformationMaster> userDTOOptional = userDetailsRepository.findByUserName(userDTO.getUserName());
            if(!userDTOOptional.isPresent()){
                UserInformationMaster userInformationMaster = new UserInformationMaster();
                userInformationMaster.setUserName(userDTO.getUserName());
                userInformationMaster.setGender(userDTO.getGender());
                userInformationMaster.setComments(userDTO.getComments());
                userInformationMaster.setUserRating(userDTO.getRating());
                userInformationMaster.setResCity(userDTO.getCity());

                HotelInformationMaster hotelInformationMaster = new HotelInformationMaster();
                hotelInformationMaster.setHotelId(userDTO.getHotelId());
                userInformationMaster.setHotelInfo(hotelInformationMaster);
                userDetailsRepository.save(userInformationMaster);
                return new ResponseModel("Api executed successfully", "SUCCESS", "User details added successfully");
            }
            return new ResponseModel("Api Executed successfully ","SUCCESS", "User details already exists");
        }

          catch (Exception e){
            return new ResponseModel("Api not executed", "Failure", "Enter Valid HotelID");
        }

    }

    public ResponseModel deleteUserDetails(UserDTO userDTO){
        try{
            Optional<UserInformationMaster> userDTOOptional = userDetailsRepository.findByUserName(userDTO.getUserName());
            if(userDTOOptional.isPresent()){
                UserInformationMaster userInformationMaster = userDTOOptional.get();
                userDetailsRepository.delete(userInformationMaster);
                return new ResponseModel("Api executed successfully", "SUCCESS", "User details deleted successfully");
            }
            return new ResponseModel("Api executed successfully", "SUCCESS", "User details not found");
        }
        catch (Exception e){
            return new ResponseModel("Api not executed", "Failure", e.getMessage());
        }
    }

    public ResponseModel deleteHotelDetails(HotelDTO hotelDTO){
        try{
            Optional<HotelInformationMaster> hotelDTOOptional = hotelDetailsRepository.findByHotelName(hotelDTO.getHotelName());
            if(hotelDTOOptional.isPresent()){
                HotelInformationMaster hotelInformationMaster = hotelDTOOptional.get();
                Optional<List<UserInformationMaster>> userOptional = userDetailsRepository.findByHotelInfo(hotelInformationMaster);
                if(userOptional.isPresent()){
                    List<UserInformationMaster> list = userOptional.get();
                    for(UserInformationMaster u : list){
                        userDetailsRepository.delete(u);
                    }
                }
                hotelDetailsRepository.delete(hotelInformationMaster);
                return new ResponseModel("Api executed successfully", "SUCCESS", "Hotel details and related user details deleted successfully");
            }
            return new ResponseModel("Api executed successfully", "SUCCESS", "Hotel details not found");
        }
        catch (Exception e){
            return new ResponseModel("Api not executed", "Failure", e.getMessage());
        }
    }

    public ResponseModel deleteReview(ReviewDTO reviewDTO){
        try{

            Optional<HotelInformationMaster> hotelDTOOptional = hotelDetailsRepository.findByHotelName(reviewDTO.getHotelName());
            if(hotelDTOOptional.isPresent()){
                HotelInformationMaster hotelInformationMaster = hotelDTOOptional.get();
                hotelInformationMaster.setRating(null);
                hotelInformationMaster.setComments(null);
                hotelDetailsRepository.save(hotelInformationMaster);
                return new ResponseModel("Api executed successfully", "SUCCESS", "Review details deleted successfully");
            }
            return new ResponseModel("Api executed successfully", "SUCCESS", "Review details not found");
        }
        catch (Exception e){
            return new ResponseModel("Api not executed", "Failure", e.getMessage());
        }
    }

  public ResponseModel sortByRating()
  {
        try{
            Optional<List<HotelInformationMaster>> hotelDTOOptional = hotelDetailsRepository.findByRatingSorted();
            if(hotelDTOOptional.isPresent()){
                List<HotelDTO> list = new ArrayList<>();
                for(HotelInformationMaster h : hotelDTOOptional.get()){
                    HotelDTO hotelDTO = new HotelDTO();
                    hotelDTO.setHotelId(h.getHotelId());
                    hotelDTO.setAvailabilityDate(h.getDateOfAvailability());
                    hotelDTO.setRating(h.getRating());
                    hotelDTO.setComments(h.getComments());
                    hotelDTO.setHotelAddress(h.getHotelAddress());
                    hotelDTO.setHotelName(h.getHotelName());
                    hotelDTO.setNoOfRooms(h.getNoOfRoomsRequired());
                    hotelDTO.setStayCost(h.getStayCost());
                    list.add(hotelDTO);
                }
                return new ResponseModel("Api executed successfully", "SUCCESS", list);
            }
            return new ResponseModel("Api executed successfully", "SUCCESS", "Data not found ");
        }
        catch (Exception e){
            return new ResponseModel("Api not executed", "Failure", e.getMessage());
        }

  }


    public ResponseModel sortByStayCost()
    {
        try{
            Optional<List<HotelInformationMaster>> hotelDTOOptional = hotelDetailsRepository.findByStayCostSorted();
            if(hotelDTOOptional.isPresent()){
                List<HotelDTO> list = new ArrayList<>();
                for(HotelInformationMaster h : hotelDTOOptional.get()){
                    HotelDTO hotelDTO = new HotelDTO();
                    hotelDTO.setHotelId(h.getHotelId());
                    hotelDTO.setAvailabilityDate(h.getDateOfAvailability());
                    hotelDTO.setRating(h.getRating());
                    hotelDTO.setComments(h.getComments());
                    hotelDTO.setHotelAddress(h.getHotelAddress());
                    hotelDTO.setHotelName(h.getHotelName());
                    hotelDTO.setNoOfRooms(h.getNoOfRoomsRequired());
                    hotelDTO.setStayCost(h.getStayCost());
                    list.add(hotelDTO);
                }
                return new ResponseModel("Api executed successfully", "SUCCESS", list);
            }
            return new ResponseModel("Api executed successfully", "SUCCESS", "Data not found ");
        }
        catch (Exception e){
            return new ResponseModel("Api not executed", "Failure", e.getMessage());
        }

    }

    public ResponseModel showRating(HotelDTO hotelDTO){
        try{
            Optional<HotelInformationMaster> hotelInformationMasterOptional = hotelDetailsRepository.findByHotelName(hotelDTO.getHotelName());
            if(hotelInformationMasterOptional.isPresent()) {
                HotelInformationMaster hotelInformationMaster = hotelInformationMasterOptional.get();
                ReviewDTO reviewDTO = new ReviewDTO();
                reviewDTO.setHotelId(hotelInformationMaster.getHotelId());
                reviewDTO.setHotelName(hotelInformationMaster.getHotelName());
                reviewDTO.setRating(hotelInformationMaster.getRating());
                reviewDTO.setComments(hotelInformationMaster.getComments());
                return new ResponseModel("Api executed successfully", "SUCCESS", reviewDTO);
            }
            return new ResponseModel("Api executed successfully", "SUCCESS", "Hotel Details not found ");
        }
        catch (Exception e){
            return new ResponseModel("Api not executed", "Failure", e.getMessage());
        }
    }

    public ResponseModel searchHotels(SearchDTO searchDTO){
        try{
            String city = searchDTO.getCity();
            Long rooms  = searchDTO.getNoOfRequiredRooms();
            Timestamp time = Timestamp.valueOf(searchDTO.getTime());
            Optional<List<HotelInformationMaster>> hotelInformationMasterOptional = hotelDetailsRepository.findByCityAndTime(rooms,city,time);
            if(hotelInformationMasterOptional.isPresent()) {
                List<HotelDTO> list = new ArrayList<>();
                for(HotelInformationMaster h : hotelInformationMasterOptional.get())
                {
                    HotelDTO hotelDTO = new HotelDTO();
                    hotelDTO.setHotelId(h.getHotelId());
                    hotelDTO.setAvailabilityDate(h.getDateOfAvailability());
                    hotelDTO.setRating(h.getRating());
                    hotelDTO.setComments(h.getComments());
                    hotelDTO.setHotelAddress(h.getHotelAddress());
                    hotelDTO.setHotelName(h.getHotelName());
                    hotelDTO.setNoOfRooms(h.getNoOfRoomsRequired());
                    hotelDTO.setStayCost(h.getStayCost());
                    list.add(hotelDTO);
                }
                return new ResponseModel("Api executed successfully", "SUCCESS", list);
            }
            return new ResponseModel("Api executed successfully", "SUCCESS", "Hotel Details not found ");
        }
        catch (Exception e){
            return new ResponseModel("Api not executed", "Failure", e.getMessage());
        }




    }


}
