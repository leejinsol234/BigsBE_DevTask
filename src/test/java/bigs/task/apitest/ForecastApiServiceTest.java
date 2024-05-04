package bigs.task.apitest;

import bigs.task.modules.application.sync.application.service.ForecastApiService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ForecastApiServiceTest {
    @Autowired
    private ForecastApiService apiService;

    @Test
    @DisplayName("API 요청 URL 테스트 - 매개변수가 없으면 현재 날짜 시간 기준으로 URL 생성")
    void getApiUrlTest() {
        String url = apiService.getApiUrl();
        System.out.println(url);
    }

    @Test
    @DisplayName("API 요청 데이터 테스트")
    void getDataTest() {
        apiService.getData();
    }
}
