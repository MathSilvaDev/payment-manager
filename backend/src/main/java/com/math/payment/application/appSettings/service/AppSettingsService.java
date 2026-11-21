package com.math.payment.application.appSettings.service;

import com.math.payment.application.appSettings.dto.request.EditAppSettingsRequest;
import com.math.payment.application.appSettings.dto.response.AppSettingsResponse;

public interface AppSettingsService {

    AppSettingsResponse find();

    void edit(EditAppSettingsRequest settingsRequest);
}
