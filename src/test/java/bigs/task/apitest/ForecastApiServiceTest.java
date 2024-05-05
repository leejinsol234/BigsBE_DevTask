package bigs.task.apitest;

import bigs.task.modules.application.sync.application.service.ForecastSyncService;
import bigs.task.modules.domain.Forecast;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@SpringBootTest
@TestPropertySource(properties = "spring.profiles.active=test")
public class ForecastApiServiceTest {

    @Autowired
    private ForecastSyncService syncService;

    @Test
    @DisplayName("API 요청 URL 테스트 - 매개변수가 없으면 현재 날짜 시간 기준으로 URL 생성")
    void getApiUrlTest() {
        String url = syncService.getApiUrl();
        System.out.println(url);
    }

    @Test
    @DisplayName("API 요청 데이터 테스트")
    void getDataTest() {

        List<Forecast> items = syncService.getData();
        items.forEach(System.out::println);
    }

    @Test
    @DisplayName("API 요청 후 데이터저장 테스트")
    void updateTest() {
        syncService.update(LocalDateTime.of(LocalDate.of(2024,5,5), LocalTime.of(10, 0)));
    }
}
