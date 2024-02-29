package ru.panifidkin.lvlproject.converter;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

class LocalDateTimeConverterTest {

    private final LocalDateTimeConverter converter = new LocalDateTimeConverter();

    @Test
    void shouldConvertToDatabaseColumn() {
        //given
        final LocalDateTime date = LocalDateTime.of(2024, 1, 10, 2, 45);
        //when
        final Timestamp result = converter.convertToDatabaseColumn(date);
        //then
        assertThat(result)
                .isNotNull()
                .hasToString("2024-01-10 02:45:00.0");
    }

    @Test
    void shouldConvertToEntityAttribute() {
        //given
        final Timestamp date = new Timestamp(1024);
        //when
        final LocalDateTime result = converter.convertToEntityAttribute(date);
        //then
        assertThat(result)
                .isNotNull()
                .isEqualTo(LocalDateTime.of(1970, Month.JANUARY, 1, 7, 0, 1, 24000000));
    }

}