package ru.panifidkin.lvlproject.converter;

import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class LocalDateConverterTest {

    private final LocalDateConverter converter = new LocalDateConverter();

    @Test
    void shouldConvertToDatabaseColumn() {
        //given
        final LocalDate date = LocalDate.of(2024, 1, 10);
        //when
        final Date result = converter.convertToDatabaseColumn(date);
        //then
        assertThat(result)
                .isNotNull()
                .hasToString("2024-01-10");
    }

    @Test
    void shouldConvertToEntityAttribute() {
        //given
        final Date date = new Date(1024);
        //when
        final LocalDate result = converter.convertToEntityAttribute(date);
        //then
        assertThat(result)
                .isNotNull()
                .isEqualTo(LocalDate.of(1970, 1, 1));
    }
}