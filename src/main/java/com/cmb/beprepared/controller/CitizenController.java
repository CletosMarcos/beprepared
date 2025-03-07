package com.cmb.beprepared.controller;

import com.cmb.beprepared.dto.request.CitizenRequestDto;
import com.cmb.beprepared.dto.response.CitizenResponseDto;
import com.cmb.beprepared.mapper.Mapper;
import com.cmb.beprepared.service.CitizenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/citizens")
public class CitizenController {

    private final Mapper mapper;
    private final CitizenService citizenService;

    @PostMapping("/")
    public ResponseEntity<String> createCitizen(@Valid @RequestBody CitizenRequestDto citizenRequestDto) {
        return new ResponseEntity<>(citizenService.createCitizen(
                mapper.mapCitizenRequestToModel(citizenRequestDto),
                citizenRequestDto.getCityId()),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<CitizenResponseDto>> getAllCitizens() {
        return ResponseEntity.ok(mapper.mapCitizenToResponseDtoList(
                citizenService.getAllCitizens()
        ));
    }

    @GetMapping("/province")
    public ResponseEntity<List<CitizenResponseDto>> getAllCitizensByProvince(@RequestParam Long id) {
        return ResponseEntity.ok(mapper.mapCitizenToResponseDtoList(
                citizenService.getAllCitizensByProvinceId(id)
        ));
    }

    @GetMapping("/city")
    public ResponseEntity<List<CitizenResponseDto>> getAllCitizensByCity(@RequestParam Long id) {
        return ResponseEntity.ok(mapper.mapCitizenToResponseDtoList(
                citizenService.getAllCitizensByCityId(id)
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CitizenResponseDto> getCitizenById(@PathVariable Long id) {
        return ResponseEntity.ok(mapper.mapCitizenToResponseDto(
                citizenService.getCitizenById(id)
        ));
    }

    @PutMapping("/verify-account")
    public ResponseEntity<String> verifyAccount(@RequestParam String otp) {
        return ResponseEntity.ok(citizenService.verifyAccount(otp));
    }
}
