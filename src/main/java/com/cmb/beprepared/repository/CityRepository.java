package com.cmb.beprepared.repository;

import com.cmb.beprepared.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository <City, Long> {

    List<City> findAllByProvinceId(Long provinceId);
}
