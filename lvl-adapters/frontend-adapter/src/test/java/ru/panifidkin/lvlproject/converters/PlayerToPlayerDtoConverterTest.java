package ru.panifidkin.lvlproject.converters;

import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import ru.panifidkin.lvlproject.entity.Player;
import ru.panifidkin.lvlproject.rs.PlayerDto;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

class PlayerToPlayerDtoConverterTest {

    private final PlayerToPlayerDtoConverter converter = new PlayerToPlayerDtoConverter();

    @Test
    void convertUponDtoIsNotNull() {
        //given
        final Player player = Player.builder()
                .firstName("firstName")
                .lastName("lastName")
                .birthDate(LocalDate.MIN)
                .build();
        //when
        final PlayerDto dto = converter.convert(player);
        //then
        assertThat(dto)
                .isNotNull()
                .extracting(it -> Tuple.tuple(
                        it.getFirstName(),
                        it.getLastName(),
                        it.getBirthDate())
                ).isEqualTo(tuple(
                        "firstName",
                        "lastName",
                        LocalDate.MIN.toString()
                ));
    }

    @Test
    void convertUponDtoIsNull() {
        //given
        //when
        final PlayerDto dto = converter.convert(null);
        //then
        assertThat(dto).isNull();
    }
}