package bigs.task.modules.application.inquire.application.controller;

import bigs.task.modules.application.inquire.application.domain.ForecastSearch;
import bigs.task.modules.application.inquire.application.domain.JSONData;
import bigs.task.modules.application.inquire.application.service.ForecastInquireService;
import bigs.task.modules.application.inquire.application.service.ForecastNoDataException;
import bigs.task.modules.domain.Forecast;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/forecast")
@RequiredArgsConstructor
public class ForecastApiInquireController {

    private final ForecastInquireService inquireService;

    /**
     * 데이터 조회
     *
     * @param search
     * @return
     */
    @GetMapping
    public JSONData search(ForecastSearch search) {
        List<Forecast> items = inquireService.search(search).orElseThrow(ForecastNoDataException::new);

        return new JSONData(items);
    }

    /**
     * 조회된 데이터가 없는 경우 조회된 데이터 없음 204(No Content) 응답코드
     *
     * @return
     */
    @ExceptionHandler(ForecastNoDataException.class)
    public ResponseEntity<Object> noDataHandler() {
        return ResponseEntity.noContent().build();
    }

    /**
     * 요청 데이터가 잘못된 형식인 경우 400(Bad Request) 응답 코드
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> badRequestHandler() {
        return ResponseEntity.badRequest().build();
    }
}
