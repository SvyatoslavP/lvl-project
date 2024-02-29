package ru.panifidkin.lvlproject.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Date;
import java.time.LocalDate;

import static ru.panifidkin.lvlproject.utils.Objects.applyIfNotNull;

@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDate localDate) {
        return applyIfNotNull(localDate, Date::valueOf);
    }

    @Override
    public LocalDate convertToEntityAttribute(Date date) {
        return applyIfNotNull(date, it -> date.toLocalDate());
    }
}
