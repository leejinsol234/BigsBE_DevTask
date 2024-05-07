package bigs.task.modules.repository;

import bigs.task.modules.domain.Forecast;
import bigs.task.modules.domain.ForecastId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ForecastRepository extends JpaRepository<Forecast, ForecastId>, QuerydslPredicateExecutor<Forecast> {

}