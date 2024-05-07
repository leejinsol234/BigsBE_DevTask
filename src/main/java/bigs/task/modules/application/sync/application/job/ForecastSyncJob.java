package bigs.task.modules.application.sync.application.job;

import bigs.task.modules.application.sync.application.service.ForecastSyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class ForecastSyncJob {

    private final ForecastSyncService syncService;

    /**
     * 1시간에 한번씩 데이터베이스에 업데이트
     */
    @Scheduled(fixedRate=1L, timeUnit = TimeUnit.HOURS)
    public void every1Hour() {
        syncService.update();
    }
}
