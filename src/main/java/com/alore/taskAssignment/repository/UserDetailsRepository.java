package com.alore.taskAssignment.repository;

import com.alore.taskAssignment.dto.HotelDTO;
import com.alore.taskAssignment.dto.UserDTO;
import com.alore.taskAssignment.entity.HotelInformationMaster;
import com.alore.taskAssignment.entity.UserInformationMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserDetailsRepository extends JpaRepository<UserInformationMaster,Long> {

    Optional<UserInformationMaster> findByUserName(String userName);
    Optional<List<UserInformationMaster>> findByHotelInfo(HotelInformationMaster hotelInfo);
}
