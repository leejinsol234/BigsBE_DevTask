package bigs.task.modules.application.sync.application.controller;

import bigs.task.modules.application.sync.application.service.ForecastSyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/forecast/sync")
public class ForecastApiSyncController {

    private final ForecastSyncService syncService;

    @PostMapping
    public ResponseEntity<Object> sync() {

        syncService.update();

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}