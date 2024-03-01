package ru.panifidkin.lvlproject.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static ru.panifidkin.lvlproject.utils.Objects.applyIfNotNull;

@Converter
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime localDateTime) {
        return applyIfNotNull(localDateTime, Timestamp::valueOf);
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp timestamp) {
        return applyIfNotNull(timestamp, it -> timestamp.toLocalDateTime());
    }
}
