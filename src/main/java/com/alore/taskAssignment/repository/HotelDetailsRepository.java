package com.alore.taskAssignment.repository;

import com.alore.taskAssignment.dto.HotelDTO;
import com.alore.taskAssignment.entity.HotelInformationMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface HotelDetailsRepository extends JpaRepository<HotelInformationMaster,Long> {

    Optional<HotelInformationMaster> findByHotelName(String hotelName);

    @Query(value = "select * from task.hotel_info order by rating desc",nativeQuery = true)
    Optional<List<HotelInformationMaster>> findByRatingSorted();

    @Query(value = "select * from task.hotel_info order by cost_of_stay desc",nativeQuery = true)
    Optional<List<HotelInformationMaster>> findByStayCostSorted();

    @Query(value = "select *from task.hotel_info where no_of_rooms >= ?1  and hotel_address = ?2 and availability_date > ?3 ",nativeQuery = true)
    Optional<List<HotelInformationMaster>> findByCityAndTime(Long noOfRoomsRequired, String city , Timestamp time);
}
