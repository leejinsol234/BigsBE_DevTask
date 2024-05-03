package bigs.task.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class WeatherInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String baseDate;
    private String baseTime;
    private String TMP;
    private String fcstDate;
    private String fcstTime;
    private double fcstValue;
    private int nx;
    private int ny;

    public WeatherInfo(Long id, String baseDate, String baseTime, String TMP, String fcstDate, String fcstTime, double fcstValue, int nx, int ny){
        this.id = id;
        this.baseDate = baseDate;
        this.baseTime = baseTime;
        this.TMP = TMP;
        this.fcstDate = fcstDate;
        this.fcstTime = fcstTime;
        this.fcstValue = fcstValue;
        this.nx = nx;
        this.ny = ny;
    }
}
