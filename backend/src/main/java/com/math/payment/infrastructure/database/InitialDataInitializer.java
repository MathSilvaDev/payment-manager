package com.math.payment.infrastructure.database;

import com.math.payment.domain.appSettings.entity.AppSettings;
import com.math.payment.domain.appSettings.repository.AppSettingsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class InitialDataInitializer implements CommandLineRunner {

    private final AppSettingsRepository appSettingsRepository;

    @Override
    public void run(String... args) throws Exception {
        generateAppSettingsEntity();
    }

    private void generateAppSettingsEntity(){
        appSettingsRepository.findFirstBy()
                .orElseGet(() -> new AppSettings(BigDecimal.ZERO));
    }
}
