package ru.panifidkin.lvlproject.services;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Rollback;
import ru.panifidkin.lvlproject.annotation.IT;
import ru.panifidkin.lvlproject.dao.PlayerRepository;
import ru.panifidkin.lvlproject.entity.Account;
import ru.panifidkin.lvlproject.entity.Player;

import javax.transaction.Transactional;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@IT
@Transactional
@Rollback
@RequiredArgsConstructor
class FrontendPlayerServiceIT {

    private final FrontendPlayerService service;
    private final PlayerRepository repository;

    @Test
    void shouldFindPlayerById() {
        //given
        final Account account = Account.builder()
                .login("testLogin")
                .password("testPassword")
                .build();
        final Player player = Player.builder()
                .firstName("testFirstName")
                .lastName("testLastName")
                .birthDate(LocalDate.of(1999, 1, 1))
                .account(account)
                .build();
        final String rqBody = """
                {
                    "playerId" : "%s"
                }
                """.formatted(player.getPersistenceId());

        repository.saveOrUpdate(player);
        //when
        final String rsBody = service.findPlayerById(rqBody);
        //then
        assertThat(rsBody).isEqualTo("""
                {
                  "player": {
                    "firstName": "testFirstName",
                    "lastName": "testLastName",
                    "birthDate": "1999-01-01"
                  },
                  "status": {
                    "statusCode": "0"
                  }
                }""");
    }
}
