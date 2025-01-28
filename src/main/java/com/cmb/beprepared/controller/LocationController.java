package com.cmb.beprepared.controller;

import com.cmb.beprepared.dto.response.CityResponseDto;
import com.cmb.beprepared.dto.response.ProvinceResponseDto;
import com.cmb.beprepared.mapper.Mapper;
import com.cmb.beprepared.service.LocationService;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
//@AllArgsConstructor
//@NoArgsConstructor
@RequestMapping("/api/v1/locations")
public class LocationController {

    @Autowired
    private final Mapper mapper;
    @Autowired
    private final LocationService locationService;

    @GetMapping("/provinces")
    public ResponseEntity<List<ProvinceResponseDto>> getAllProvinces() {
        return ResponseEntity.ok(mapper.mapProvinceToResponseDtoList(
                locationService.getAllProvinces()
        ));
    }

    @GetMapping("/province")
    public ResponseEntity<ProvinceResponseDto> getProvinceById(@RequestParam Long id) {
        return ResponseEntity.ok(mapper.mapProvinceToResponseDto(
                locationService.getProvinceById(id)
        ));
    }

    @GetMapping("/cities")
    public ResponseEntity<List<CityResponseDto>> getAllCities() {
        return ResponseEntity.ok(mapper.mapCityToResponseDtoList(
                locationService.getAllCities()
        ));
    }

    @GetMapping("/cities/{provinceId}")
    public ResponseEntity<List<CityResponseDto>> getCityById(@PathVariable Long id) {
        return ResponseEntity.ok(mapper.mapCityToResponseDtoList(
                locationService.getAllCitiesProvinceId(id)
        ));
    }

    @GetMapping("/city")
    public ResponseEntity<CityResponseDto> getCityByProvinceId(@RequestParam Long id) {
        return ResponseEntity.ok(mapper.mapCityToResponseDto(
                locationService.getCityById(id)
        ));
    }

}
