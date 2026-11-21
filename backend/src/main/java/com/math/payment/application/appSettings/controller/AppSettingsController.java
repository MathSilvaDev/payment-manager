package com.math.payment.application.appSettings.controller;

import com.math.payment.application.appSettings.dto.request.EditAppSettingsRequest;
import com.math.payment.application.appSettings.dto.response.AppSettingsResponse;
import com.math.payment.application.appSettings.service.AppSettingsServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app-settings")
@RequiredArgsConstructor
public class AppSettingsController {

    private final AppSettingsServiceImpl appSettingsService;

    @GetMapping(version = "1.0")
    public ResponseEntity<AppSettingsResponse> find(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(appSettingsService.find());
    }

    @PatchMapping(version = "1.0")
    public ResponseEntity<Void> edit(@Valid @RequestBody EditAppSettingsRequest request){
        appSettingsService.edit(request);

        return ResponseEntity.noContent().build();
    }
}
