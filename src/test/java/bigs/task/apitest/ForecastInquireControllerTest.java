package bigs.task.apitest;

import bigs.task.modules.application.sync.application.service.ForecastSyncService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.IntStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = "spring.profiles.active=test")
public class ForecastInquireControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ForecastSyncService syncService;

    private String requestUrl = "/api/v1/forecast";

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
    @DisplayName("검색 조건이 없는 상태로 조회시 오늘 날짜 및 시간 기준 데이터 조회 테스트")
    void searchTest1() throws Exception {
        mockMvc.perform(get(requestUrl))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("검색 조건이 있는 조회 테스트")
    void searchTest2() throws Exception {
        LocalDateTime eDt = LocalDateTime.now();
        LocalDateTime sDt = eDt.minusHours(5);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHH");

        String sDateTime = formatter.format(sDt);
        String eDateTime = formatter.format(eDt);

        String url = String.format("%s?sDateTime=%s&eDateTime=%s&category=RN1&category=WSD", requestUrl, sDateTime, eDateTime);

        mockMvc.perform(get(url))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("검색 데이터 없는 경우 204 응답 코드 테스트")
    void searchTest3() throws Exception {
        LocalDateTime eDt = LocalDateTime.now().minusDays(10);
        LocalDateTime sDt = eDt.minusHours(5);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHH");

        String sDateTime = formatter.format(sDt);
        String eDateTime = formatter.format(eDt);

        String url = String.format("%s?sDateTime=%s&eDateTime=%s&category=RN1&category=WSD", requestUrl, sDateTime, eDateTime);

        mockMvc.perform(get(url))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}