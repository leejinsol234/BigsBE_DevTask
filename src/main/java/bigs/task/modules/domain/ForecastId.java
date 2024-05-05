package bigs.task.modules.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ForecastId {
    private String baseDate;
    private String baseTime;
    private String category;
    private int nx;
    private int ny;
}
