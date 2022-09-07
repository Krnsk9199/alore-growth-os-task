package com.alore.taskAssignment.repository;

import com.alore.taskAssignment.entity.HotelFacilityMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HotelFacilityRepository extends JpaRepository<HotelFacilityMaster,Long> {

}
