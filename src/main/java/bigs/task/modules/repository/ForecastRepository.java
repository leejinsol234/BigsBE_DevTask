package bigs.task.modules.repository;

import bigs.task.modules.domain.Forecast;
import bigs.task.modules.domain.ForecastId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForecastRepository extends JpaRepository<Forecast, ForecastId> {

}