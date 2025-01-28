package com.cmb.beprepared.service.impl;

import com.cmb.beprepared.exception.EntityNotFoundException;
import com.cmb.beprepared.model.City;
import com.cmb.beprepared.model.Province;
import com.cmb.beprepared.repository.CityRepository;
import com.cmb.beprepared.repository.ProvinceRepository;
import com.cmb.beprepared.service.LocationService;
//import jakarta.persistence.EntityNotFoundException;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
//@AllArgsConstructor
//@NoArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final CityRepository cityRepository;
    @Autowired
    private final ProvinceRepository provinceRepository;

    @Override
    public List<Province> getAllProvinces() {
        return provinceRepository.findAll();
    }

    @Override
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    @Override
    public List<City> getAllCitiesProvinceId(Long provinceId) {
        return cityRepository.findAllByProvinceId(provinceId);
    }

    @Override
    public Province getProvinceById(Long provinceId) {
        return provinceRepository.findById(provinceId).orElseThrow(() ->
                new EntityNotFoundException("Province not found!"));
    }

    @Override
    public City getCityById(Long cityId) {
        return cityRepository.findById(cityId).orElseThrow(() ->
                new EntityNotFoundException("City not found!"));
    }
}
