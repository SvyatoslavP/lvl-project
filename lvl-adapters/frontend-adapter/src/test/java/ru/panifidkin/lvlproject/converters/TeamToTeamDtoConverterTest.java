package ru.panifidkin.lvlproject.converters;

import org.assertj.core.groups.Tuple;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.panifidkin.lvlproject.entity.Account;
import ru.panifidkin.lvlproject.entity.Player;
import ru.panifidkin.lvlproject.entity.Team;
import ru.panifidkin.lvlproject.rs.TeamDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TeamToTeamDtoConverterTest {

    @Mock
    private PlayerToPlayerDtoConverter playerConverter;
    @InjectMocks
    private TeamToTeamDtoConverter converter;

    @Test
    void convertUponDtoIsNotNull() {
        //given
        List<Player> players = Lists.newArrayList(Player.builder()
                .firstName("Petr")
                .lastName("Petrov")
                .birthDate(LocalDate.MIN)
                .account(new Account("dummy", "dummy"))
                .build());
        final Team team = Team.builder()
                .teamName("teamName")
                .foundingDate(LocalDate.MIN)
                .teamRating(BigDecimal.TEN)
                .players(players)
                .build();
        //when
        final TeamDto dto = converter.convert(team);
        //then
        assertThat(dto)
                .isNotNull()
                .extracting(it -> Tuple.tuple(
                        it.getTeamName(),
                        it.getFoundingDate(),
                        it.getTeamRating()
                )).isEqualTo(tuple(
                        "teamName",
                        LocalDate.MIN.toString(),
                        BigDecimal.TEN
                ));
        verify(playerConverter, times(1)).convert(players.get(0));
    }

    @Test
    void convertUponDtoIsNull() {
        //given
        //when
        final TeamDto dto = converter.convert(null);
        //then
        assertThat(dto).isNull();
    }

}
