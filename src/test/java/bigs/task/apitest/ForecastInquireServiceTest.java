package bigs.task.apitest;

import bigs.task.modules.application.inquire.application.domain.ForecastSearch;
import bigs.task.modules.application.inquire.application.service.ForecastInquireService;
import bigs.task.modules.application.sync.application.service.ForecastSyncService;
import bigs.task.modules.domain.Forecast;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
@TestPropertySource(properties = "spring.profiles.active=test")
public class ForecastInquireServiceTest {

    @Autowired
    private ForecastInquireService inquireService;
    @Autowired
    private ForecastSyncService syncService;

    @BeforeEach
    void init() {
        /* 테스트 전 기상 데이터 생성 - 현재 시간 기준 5시간 전까지 1시간 단위 */
        LocalDateTime dateTime = LocalDateTime.now();
        IntStream.rangeClosed(0, 5).forEach(i -> {
            LocalDateTime dt = dateTime.minusHours(i);
            syncService.update(dt);
        });
    }

    @Test
    @DisplayName("조회 서비스 테스트 - 날짜시간 검색 조건이 없다면 현재 날짜 시간 기준 검색")
    void searchNoDateTimeTest() {
        ForecastSearch search = new ForecastSearch();
        search.setCategory(List.of("WSD", "RN1"));

        assertDoesNotThrow(() -> {
            Optional<List<Forecast>> result = inquireService.search(search);
            result.get().forEach(System.out::println);
        });
    }

    @Test
    @DisplayName("조회 서비스 테스트 - 날짜시간 검색 조건에 따른 검색")
    void searchDateTImeTest() {
        ForecastSearch search = new ForecastSearch();
        search.setCategory(List.of("WSD", "RN1"));
        LocalDateTime now = LocalDateTime.now();
        search.setEDateTime(now);
        search.setSDateTime(now.minusDays(3));

        assertDoesNotThrow(() -> {
            Optional<List<Forecast>> result = inquireService.search(search);
            result.get().forEach(System.out::println);
        });
    }
}
