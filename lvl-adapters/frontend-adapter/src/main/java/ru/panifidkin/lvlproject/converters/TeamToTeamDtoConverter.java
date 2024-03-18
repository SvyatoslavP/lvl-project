package ru.panifidkin.lvlproject.converters;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.panifidkin.lvlproject.entity.Team;
import ru.panifidkin.lvlproject.rs.TeamDto;
import ru.panifidkin.lvlproject.utils.Objects;

import javax.annotation.Nullable;

import static ru.panifidkin.lvlproject.utils.ConverterUtils.convertDateToString;

@Component
@AllArgsConstructor
public class TeamToTeamDtoConverter {

    private final PlayerToPlayerDtoConverter converter;

    @Nullable
    public TeamDto convert(@Nullable Team source) {
        if (source == null) {
            return null;
        }
        return TeamDto.builder()
                .teamName(source.getTeamName())
                .teamRating(source.getTeamRating())
                .foundingDate(convertDateToString(source.getFoundingDate()))
                .players(source.getPlayers().stream()
                        .map(converter::convert)
                        .filter(Objects::isNotNull)
                        .toList())
                .build();
    }
}
