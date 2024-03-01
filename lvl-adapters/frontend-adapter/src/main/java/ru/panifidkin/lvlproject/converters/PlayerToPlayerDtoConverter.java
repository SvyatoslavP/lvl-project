package ru.panifidkin.lvlproject.converters;

import org.springframework.stereotype.Component;
import ru.panifidkin.lvlproject.entity.Player;
import ru.panifidkin.lvlproject.rs.PlayerDto;

import javax.annotation.Nullable;

import static ru.panifidkin.lvlproject.utils.ConverterUtils.convertDateToString;

@Component
public class PlayerToPlayerDtoConverter {

    @Nullable
    public PlayerDto convert(@Nullable Player source) {
        if (source == null) {
            return null;
        }
        return PlayerDto.builder()
                .lastName(source.getLastName())
                .firstName(source.getFirstName())
                .birthDate(convertDateToString(source.getBirthDate()))
                .build();
    }
}
