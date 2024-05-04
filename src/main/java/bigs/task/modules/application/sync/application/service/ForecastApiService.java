package bigs.task.modules.application.sync.application.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Service
public class ForecastApiService {

    @Value("${openApi.serviceKey}")
    private String serviceKey;

    /**
     * 기상 데이터 조회
     *
     * @return
     */
    public String getData(LocalDateTime dateTime) {

        RestTemplate restTemplate = new RestTemplate();
        String data = restTemplate.getForObject(getApiUrl(dateTime),  String.class);
        System.out.println(data);
        return null;
    }

    public String getData() {
        return getData(null);
    }

    /**
     * 날짜, 시간별 기상 데이터 조회 API 요청 URL
     *
     * @param dateTime : 날짜, 시간
     * @return 요청 URL
     */
    public String getApiUrl(LocalDateTime dateTime) {
        dateTime = Objects.requireNonNullElse(dateTime, LocalDateTime.now()); // dateTime이 null이면 현재 날짜, 시간 기준으로 조회

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH");
        String date = dateFormatter.format(dateTime);
        String time = timeFormatter.format(dateTime);

        return String.format("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst?serviceKey=%s&pageNo=1&numOfRows=1000&dataType=JSON&nx=37&ny=127&base_date=%s&base_time=%s00", serviceKey, date, time);
    }

    public String getApiUrl() {
        return getApiUrl(null);
    }
}
