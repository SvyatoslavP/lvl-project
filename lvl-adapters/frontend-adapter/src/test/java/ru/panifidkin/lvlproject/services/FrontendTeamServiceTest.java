package ru.panifidkin.lvlproject.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.panifidkin.lvlproject.converters.TeamToTeamDtoConverter;
import ru.panifidkin.lvlproject.entity.Team;
import ru.panifidkin.lvlproject.rq.SrvGetTeamByIdRq;
import ru.panifidkin.lvlproject.rs.TeamDto;
import ru.panifidkin.lvlproject.serializers.FrontendTeamSerializer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FrontendTeamServiceTest {

    @InjectMocks
    private FrontendTeamService service;
    @Mock
    private FrontendTeamSerializer serializer;
    @Mock
    private TeamService playerService;
    @Mock
    private TeamToTeamDtoConverter converter;

    @Test
    void shouldCallServicesAndConvertersUponHasTeamId() {
        //given
        final String rqBody = "{rqBody}";
        final String teamId = "id";
        final SrvGetTeamByIdRq rq = SrvGetTeamByIdRq.builder()
                .teamId(teamId)
                .build();
        final Team team = Team.builder().build();
        final TeamDto teamDto = TeamDto.builder().build();
        final String rsBody = "{rs}";
        when(serializer.deserialize(rqBody)).thenReturn(rq);
        when(playerService.findById(teamId)).thenReturn(team);
        when(converter.convert(team)).thenReturn(teamDto);
        when(serializer.serialize(any())).thenReturn(rsBody);
        //when
        service.findTeamById(rqBody);
        //then
        final InOrder inOrder = inOrder(serializer, playerService, converter, serializer);
        inOrder.verify(serializer, times(1)).deserialize(rqBody);
        inOrder.verify(playerService, times(1)).findById(teamId);
        inOrder.verify(converter, times(1)).convert(team);
        inOrder.verify(serializer, times(1)).serialize(any());
    }

    @Test
    void shouldBeCorrectAnswerUponThrowException() {
        //given
        final String rqBody = "{rqBody}";
        given(serializer.deserialize(rqBody)).willAnswer(it -> {
            throw new Exception();
        });
        //when
        final Throwable throwable = catchThrowable(() -> service.findTeamById(rqBody));
        //then
        assertThat(throwable).isNull();
        verify(serializer, times(1)).serialize(any());
    }
}
