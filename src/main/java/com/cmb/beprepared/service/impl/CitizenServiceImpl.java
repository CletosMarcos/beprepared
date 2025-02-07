package com.cmb.beprepared.service.impl;

import com.cmb.beprepared.exception.BadRequestException;
import com.cmb.beprepared.exception.EntityNotFoundException;
import com.cmb.beprepared.model.Citizen;
import com.cmb.beprepared.model.City;
import com.cmb.beprepared.repository.CitizenRepository;
import com.cmb.beprepared.service.CitizenService;
import com.cmb.beprepared.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CitizenServiceImpl implements CitizenService {

    private final LocationService locationService;
    private final CitizenRepository citizenRepository;

    @Override
    public String createCitizen(Citizen citizen, Long cityId) {
        if (citizenRepository.existsByPhone(citizen.getPhone())) {
            throw new BadRequestException("Phone number is owned by another citizen!");
        }
        City city = locationService.getCityById(cityId);
        citizen.setCity(city);
        citizen.setVerified(false);
        citizen.setOtp(generateOtp(6));
        var savedCitizen = citizenRepository.save(citizen);
        return "User created successfully! your OTP is: " + savedCitizen.getOtp();
    }

    @Override
    public List<Citizen> getAllCitizens() {
        return citizenRepository.findAll();
    }

    @Override
    public List<Citizen> getAllCitizensByCityId(Long cityId) {
        return citizenRepository.findAllByCityId(cityId);
    }

    @Override
    public List<Citizen> getAllCitizensByProvinceId(Long provinceId) {
        return citizenRepository.findAllByCityProvinceId(provinceId);
    }

    @Override
    public Citizen getCitizenById(Long id) {
        return citizenRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Citizen not found!"));
    }

    @Override
    public String verifyAccount(String otp) {
        Citizen citizen = citizenRepository.findByOtp(otp).orElseThrow(() ->
                new EntityNotFoundException("Citizen not found!"));
        citizen.setVerified(true);
        citizen.setOtp(null);
        citizenRepository.save(citizen);
        return "Account successfully verified";
    }

    private static String generateOtp(int length) {
        String otp = "";
        int x;
        char[] chars = new char[length];

        for (int i = 0; i < length; i++) {
            Random random = new Random();
            x = random.nextInt(9);
            chars[i] = Integer.toString(x).toCharArray()[0];
        }

        otp = new String(chars);
        return otp.trim();
    }
}
