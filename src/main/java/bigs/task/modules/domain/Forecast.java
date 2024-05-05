package bigs.task.modules.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@IdClass(ForecastId.class)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Forecast {

    @Id
    @Column(length=20)
    private String baseDate;
    @Id
    @Column(length=10)
    private String baseTime;
    @Id
    @Column(length=20)
    private String category;
    @Id
    private int nx;
    @Id
    private int ny;

    private double obsrValue;

    private LocalDateTime dateTime;
}