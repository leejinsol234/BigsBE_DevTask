package bigs.task.modules.application.sync.application.service;

import bigs.task.modules.domain.Forecast;
import bigs.task.modules.repository.ForecastRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
@RequiredArgsConstructor
public class ForecastSyncService {
    @Value("${openApi.serviceKey}")
    private String serviceKey;

    private final ForecastRepository repository;

    private final String TAG = "FORECAST API";

    /**
     * API 조회 데이터 데이터베이스에 저장 처리
     * @param dateTime
     */
    public void update(LocalDateTime dateTime) {
        try {
            List<Forecast> items = getData(dateTime);
            if (items == null) { // 조회된 데이터가 없는 경우 저장 처리 진행하지 않음
                log.info(TAG+" {}", "No Data Searched");
                return;
            }

            // baseDate와 baseTime은 LocalDateTime dateTime으로 변경 저장 처리
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd HH00");
            items.forEach(item -> item.setDateTime(LocalDateTime.parse(item.getBaseDate() + " " + item.getBaseTime(), formatter)));

            // 데이터베이스에 영구 저장 처리
            repository.saveAllAndFlush(items);
        } catch (Exception e) {
            // 데이터 중복 방지를 위해 baseDate, baseTime, category, nx, ny 등을 기본키로 설정하였고 예외가 발생하면 중복키가 있다는 것으로 판단
            log.error(TAG, e);
        }
    }

    public void update() {
        update(null);
    }

    /**
     * 기상 데이터 조회
     *
     * @param  dateTime : 조회 날짜 시간 지정, 시간은 정시 기준
     * @return List<Forecast>
     */
    public List<Forecast> getData(LocalDateTime dateTime) {

        RestTemplate restTemplate = new RestTemplate();
        String str = restTemplate.getForObject(getApiUrl(dateTime),  String.class);
        Pattern pattern = Pattern.compile("\"item\":(\\[[^]]+\\])");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            ObjectMapper om = new ObjectMapper();
            try {
                List<Forecast> items = om.readValue(matcher.group(1), new TypeReference<>() {});
                return items;
            } catch (JsonProcessingException e) {
                log.error(TAG, e);
            }
        }


        return null;
    }

    public List<Forecast> getData() {
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
