package bigs.task.modules.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QForecast is a Querydsl query type for Forecast
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QForecast extends EntityPathBase<Forecast> {

    private static final long serialVersionUID = 2005812944L;

    public static final QForecast forecast = new QForecast("forecast");

    public final StringPath baseDate = createString("baseDate");

    public final StringPath baseTime = createString("baseTime");

    public final StringPath category = createString("category");

    public final DateTimePath<java.time.LocalDateTime> dateTime = createDateTime("dateTime", java.time.LocalDateTime.class);

    public final NumberPath<Integer> nx = createNumber("nx", Integer.class);

    public final NumberPath<Integer> ny = createNumber("ny", Integer.class);

    public final NumberPath<Double> obsrValue = createNumber("obsrValue", Double.class);

    public QForecast(String variable) {
        super(Forecast.class, forVariable(variable));
    }

    public QForecast(Path<? extends Forecast> path) {
        super(path.getType(), path.getMetadata());
    }

    public QForecast(PathMetadata metadata) {
        super(Forecast.class, metadata);
    }

}

