package ru.panifidkin.lvlproject.serializers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.panifidkin.lvlproject.rq.SrvGetTeamByIdRq;
import ru.panifidkin.lvlproject.rq.SrvGetTeamByNameRq;
import ru.panifidkin.lvlproject.serialize.ObjectSerializer;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class FrontendTeamDeserializerTest {

    @InjectMocks
    private FrontendTeamSerializer frontendTeamSerializer;
    @Mock
    private ObjectSerializer<SrvGetTeamByIdRq> idDeserializer;
    @Mock
    private ObjectSerializer<SrvGetTeamByNameRq> nameDeserializer;

    @Test
    void shouldCallDeserializerUponIdDeserialize() {
        //given
        final String rs = "{rs}";
        //when
        frontendTeamSerializer.idDeserialize(rs);
        //then
        verify(idDeserializer, times(1)).deserialize(rs, SrvGetTeamByIdRq.class);
    }

    @Test
    void shouldCallDeserializerUponNameDeserialize() {
        //given
        final String rs = "{rs}";
        //when
        frontendTeamSerializer.nameDeserialize(rs);
        //then
        verify(nameDeserializer, times(1)).deserialize(rs, SrvGetTeamByNameRq.class);
    }
}
