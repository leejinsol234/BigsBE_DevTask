package bigs.task.modules.application.inquire.application.service;

import bigs.task.modules.domain.Forecast;
import bigs.task.modules.repository.ForecastRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Forecast> search() {

        return null;
    }
}
