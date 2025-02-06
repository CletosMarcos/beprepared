package com.cmb.beprepared.service;

import com.cmb.beprepared.model.Alert;

import java.util.List;

public interface AlertService {

    String createAlert(Alert alert, Long cityId, Long provinceId);

    List<Alert> getAllAlerts();

    List<Alert> getAllActiveAlerts();

    List<Alert> getAllAlertsByCityId(Long cityId);

    List<Alert> getAllActiveAlertsByProvinceId(Long provinceId);

    Alert getAlertById(Long alertId);

    String activeAlert(Long alertId);
}
