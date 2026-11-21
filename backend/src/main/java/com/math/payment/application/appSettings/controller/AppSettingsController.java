package com.math.payment.application.appSettings.controller;

import com.math.payment.application.appSettings.service.AppSettingsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app-settings")
@RequiredArgsConstructor
public class AppSettingsController {

    private final AppSettingsServiceImpl appSettingsService;
}
