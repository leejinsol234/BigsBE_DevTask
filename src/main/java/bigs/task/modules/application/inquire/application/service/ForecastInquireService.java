package bigs.task.modules.application.inquire.application.service;

import bigs.task.modules.application.inquire.application.domain.ForecastSearch;
import bigs.task.modules.domain.Forecast;
import bigs.task.modules.domain.QForecast;
import bigs.task.modules.repository.ForecastRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.Sort.Order.asc;

@Slf4j
@Service
@RequiredArgsConstructor
public class ForecastInquireService {

    private final ForecastRepository repository;

    /**
     * 기상 정보 조회
     *
     * @return
     */
    public Optional<List<Forecast>> search(ForecastSearch search) {
        LocalDateTime sDateTime = search.getSDateTime();
        LocalDateTime eDateTime = search.getEDateTime();
        List<String> category = search.getCategory();

        BooleanBuilder builder = new BooleanBuilder();
        QForecast forecast = QForecast.forecast;
        /* 발표일자, 시간 검색 S */

        // sDateTime이 없다면 현재 시간 기준으로 생성
        if (sDateTime == null) {
            sDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.of(LocalTime.now().getHour(), 0));
        }

        builder.and(forecast.dateTime.goe(sDateTime));


        if (eDateTime != null) {
            builder.and(forecast.dateTime.lt(eDateTime.plusHours(1)));
        }
        /* 발표일자, 시간 검색 E */

        /* 자료 구분 코드 검색 S */
        if (category != null && !category.isEmpty()) {
            builder.and(forecast.category.in(category));
        }
        /* 자료 구분 코드 검색 E */


        List<Forecast> items = (List<Forecast>) repository.findAll(builder, Sort.by(asc("dateTime")));

        return Optional.ofNullable(items.isEmpty() ? null : items);
    }
}