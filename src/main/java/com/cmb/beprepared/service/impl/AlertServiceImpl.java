package com.cmb.beprepared.service.impl;

import com.cmb.beprepared.exception.EntityNotFoundException;
import com.cmb.beprepared.model.Alert;
import com.cmb.beprepared.model.City;
import com.cmb.beprepared.model.Province;
import com.cmb.beprepared.repository.AlertRepository;
import com.cmb.beprepared.service.AlertService;
import com.cmb.beprepared.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlertServiceImpl implements AlertService {

    private final AlertRepository alertRepository;
    private final LocationService locationService;
    @Override
    @Transactional
    public String createAlert(Alert alert, Long cityId, Long provinceId) {
        City city = locationService.getCityById(cityId);
        Province province = locationService.getProvinceById(provinceId);
        alert.setActive(false);
        alert.setCity(city);
        alert.setProvince(province);
        alertRepository.save(alert);
        return "Alert created successfully!";
    }

    @Override
    public List<Alert> getAllAlerts() {
        return alertRepository.findAll();
    }

    @Override
    public List<Alert> getAllActiveAlerts() {
        return alertRepository.findAllByActive(true);
    }

    @Override
    public List<Alert> getAllActiveAlertsByCityId(Long cityId) {
        return alertRepository.findAllByActiveAndCityId(true, cityId);
    }

    @Override
    public List<Alert> getAllActiveAlertsByProvinceId(Long provinceId) {
        return alertRepository.findAllByActiveAndProvinceId(true, provinceId);
    }

    @Override
    public Alert getAlertById(Long alertId) {
        return alertRepository.findById(alertId).orElseThrow(() ->
                new EntityNotFoundException("Alert not found!"));
    }

    @Override
    public String activeAlert(Long alertId) {
        Alert alert = getAlertById(alertId);
        alert.setActive(true);
        alertRepository.save(alert);
        return "Danger!!! Protect yourself.";
    }
}
