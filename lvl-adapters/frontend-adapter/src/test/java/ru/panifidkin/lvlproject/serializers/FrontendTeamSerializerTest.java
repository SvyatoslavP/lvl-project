package ru.panifidkin.lvlproject.serializers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.panifidkin.lvlproject.rs.SrvGetTeamByIdRs;
import ru.panifidkin.lvlproject.rs.SrvGetTeamByNameRs;
import ru.panifidkin.lvlproject.serialize.ObjectSerializer;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class FrontendTeamSerializerTest {

    @InjectMocks
    private FrontendTeamSerializer frontendTeamSerializer;
    @Mock
    private ObjectSerializer<SrvGetTeamByIdRs> idSerializer;
    @Mock
    private ObjectSerializer<SrvGetTeamByNameRs> nameSerializer;

    @Test
    void shouldCallSerializerUponIdSerialize() {
        //given
        final SrvGetTeamByIdRs rs = SrvGetTeamByIdRs.builder().build();
        //when
        frontendTeamSerializer.idSerialize(rs);
        //then
        verify(idSerializer, times(1)).serialize(rs);
    }

    @Test
    void shouldCallSerializerUponNameSerialize() {
        //given
        final SrvGetTeamByNameRs rs = SrvGetTeamByNameRs.builder().build();
        //when
        frontendTeamSerializer.nameSerialize(rs);
        //then
        verify(nameSerializer, times(1)).serialize(rs);
    }
}
