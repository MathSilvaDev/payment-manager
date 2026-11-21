package com.math.payment.application.appSettings.service;

import com.math.payment.application.appSettings.dto.request.EditAppSettingsRequest;
import com.math.payment.application.appSettings.dto.response.AppSettingsResponse;
import com.math.payment.domain.appSettings.entity.AppSettings;
import com.math.payment.domain.appSettings.repository.AppSettingsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.server.ResponseStatusException;

@Service
@ApplicationScope
@RequiredArgsConstructor
public class AppSettingsServiceImpl implements AppSettingsService{

    private final AppSettingsRepository appSettingsRepository;

    @Override
    public AppSettingsResponse find() {
        return toResponse(findFirstByOrThrow());
    }

    @Override
    @Transactional
    public void edit(EditAppSettingsRequest settingsRequest) {
        AppSettings appSettings = findFirstByOrThrow();
        appSettings.setInitialAmount(settingsRequest.initialAmount());
    }

    private AppSettingsResponse toResponse(AppSettings appSettings){
        return new AppSettingsResponse(
                appSettings.getInitialAmount()
        );
    }

    private AppSettings findFirstByOrThrow(){
        return appSettingsRepository.findFirstBy()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.INTERNAL_SERVER_ERROR, "UNEXPECTED ERROR"));
    }

}
