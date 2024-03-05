package ru.panifidkin.lvlproject.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.panifidkin.lvlproject.converters.PlayerToPlayerDtoConverter;
import ru.panifidkin.lvlproject.entity.Player;
import ru.panifidkin.lvlproject.rq.SrvGetPlayerByIdRq;
import ru.panifidkin.lvlproject.rs.PlayerDto;
import ru.panifidkin.lvlproject.serializers.FrontendPlayerSerializer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FrontendPlayerServiceTest {

    @InjectMocks
    private FrontendPlayerService service;
    @Mock
    private FrontendPlayerSerializer serializer;
    @Mock
    private PlayerService playerService;
    @Mock
    private PlayerToPlayerDtoConverter converter;

    @Test
    void shouldCallServicesAndConvertersUponHasPlayerId() {
        //given
        final String rqBody = "{rqBody}";
        final String playerId = "id";
        final SrvGetPlayerByIdRq rq = SrvGetPlayerByIdRq.builder()
                .playerId(playerId)
                .build();
        final Player player = Player.builder().build();
        final PlayerDto playerDto = PlayerDto.builder().build();
        final String rsBody = "{rs}";
        when(serializer.deserialize(rqBody)).thenReturn(rq);
        when(playerService.findById(playerId)).thenReturn(player);
        when(converter.convert(player)).thenReturn(playerDto);
        when(serializer.serialize(any())).thenReturn(rsBody);
        //when
        service.findPlayerById(rqBody);
        //then
        final InOrder inOrder = inOrder(serializer, playerService, converter, serializer);
        inOrder.verify(serializer, times(1)).deserialize(rqBody);
        inOrder.verify(playerService, times(1)).findById(playerId);
        inOrder.verify(converter, times(1)).convert(player);
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
        final Throwable throwable = catchThrowable(() -> service.findPlayerById(rqBody));
        //then
        assertThat(throwable).isNull();
        verify(serializer, times(1)).serialize(any());
    }
}