package ru.panifidkin.lvlproject.services;

import lombok.RequiredArgsConstructor;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import ru.panifidkin.lvlproject.annotation.IT;
import ru.panifidkin.lvlproject.dao.TeamRepository;
import ru.panifidkin.lvlproject.entity.Account;
import ru.panifidkin.lvlproject.entity.Player;
import ru.panifidkin.lvlproject.entity.Team;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@IT
@RequiredArgsConstructor
class FrontendTeamServiceIT {

    private final FrontendTeamService service;
    private final TeamRepository repository;

    @Test
    void shouldFindTeamById() {
        //given
        final List<Player> players = Lists.newArrayList(Player.builder()
                .firstName("testFirstName")
                .lastName("testLastName")
                .birthDate(LocalDate.of(1999, 1, 1))
                .account(Account.builder()
                        .login("testLogin")
                        .password("testPassword")
                        .build())
                .build());
        final Team team = Team.builder()
                .teamRating(BigDecimal.ONE)
                .foundingDate(LocalDate.of(1999, 1, 1))
                .players(players)
                .teamName("testTeamName")
                .build();
        final String rqBody = """
                {
                    "teamId" : "%s"
                }
                """.formatted(team.getPersistenceId());

        repository.saveOrUpdate(team);
        //when
        final String rsBody = service.findTeamById(rqBody);
        //then
        assertThat(rsBody).isEqualTo("""
                {
                  "team": {
                    "teamName": "testTeamName",
                    "teamRating": 1,
                    "foundingDate": "1999-01-01",
                    "players": [
                      {
                        "firstName": "testFirstName",
                        "lastName": "testLastName",
                        "birthDate": "1999-01-01"
                      }
                    ]
                  },
                  "status": {
                    "statusCode": "0"
                  }
                }""");
    }

}