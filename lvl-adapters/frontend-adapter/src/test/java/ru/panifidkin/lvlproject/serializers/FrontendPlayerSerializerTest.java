package ru.panifidkin.lvlproject.serializers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.panifidkin.lvlproject.rs.SrvGetPlayerByIdRs;
import ru.panifidkin.lvlproject.serialize.ObjectSerializer;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class FrontendPlayerSerializerTest {

    @InjectMocks
    private FrontendPlayerSerializer frontendPlayerSerializer;
    @Mock
    private ObjectSerializer<SrvGetPlayerByIdRs> serializer;

    @Test
    void shouldCallSerializerUponSerialize() {
        //given
        final SrvGetPlayerByIdRs rs = SrvGetPlayerByIdRs.builder().build();
        //when
        frontendPlayerSerializer.serialize(rs);
        //then
        verify(serializer, times(1)).serialize(rs);
    }
}