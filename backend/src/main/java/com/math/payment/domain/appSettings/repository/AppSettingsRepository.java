package com.math.payment.domain.appSettings.repository;

import com.math.payment.domain.appSettings.entity.AppSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AppSettingsRepository extends JpaRepository<AppSettings, UUID> {

    Optional<AppSettings> findFirstBy();
}
