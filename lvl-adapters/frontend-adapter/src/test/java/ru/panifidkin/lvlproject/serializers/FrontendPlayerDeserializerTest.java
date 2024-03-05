package ru.panifidkin.lvlproject.serializers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.panifidkin.lvlproject.rq.SrvGetPlayerByIdRq;
import ru.panifidkin.lvlproject.serialize.ObjectSerializer;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class FrontendPlayerDeserializerTest {

    @InjectMocks
    private FrontendPlayerSerializer frontendPlayerSerializer;
    @Mock
    private ObjectSerializer<SrvGetPlayerByIdRq> deserializer;

    @Test
    void shouldCallDeserializerUponDeserialize() {
        //given
        final String rs = "{rs}";
        //when
        frontendPlayerSerializer.deserialize(rs);
        //then
        verify(deserializer, times(1)).deserialize(rs, SrvGetPlayerByIdRq.class);
    }
}
