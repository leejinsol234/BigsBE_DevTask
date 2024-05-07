package bigs.task.modules.application.inquire.application.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ForecastSearch {
    @DateTimeFormat(pattern="yyyyMMddHH")
    private LocalDateTime sDateTime; // 발표일시 시작 범위

    @DateTimeFormat(pattern="yyyyMMddHH")
    private LocalDateTime eDateTime; // 발표일시 종료 범위

    private List<String> category; // 자료구분 코드 - RN1, T1H, UUU, VVV, WSD
}

