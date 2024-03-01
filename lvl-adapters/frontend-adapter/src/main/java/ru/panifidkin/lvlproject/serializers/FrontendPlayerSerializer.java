package ru.panifidkin.lvlproject.serializers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.panifidkin.lvlproject.rq.SrvGetPlayerByIdRq;
import ru.panifidkin.lvlproject.rs.SrvGetPlayerByIdRs;
import ru.panifidkin.lvlproject.serialize.ObjectSerializer;

@Component
@AllArgsConstructor
public class FrontendPlayerSerializer {

    private final ObjectSerializer<SrvGetPlayerByIdRq> deserializer;
    private final ObjectSerializer<SrvGetPlayerByIdRs> serializer;

    public SrvGetPlayerByIdRq deserialize(String json) {
        return deserializer.deserialize(json, SrvGetPlayerByIdRq.class);
    }

    public String serialize(SrvGetPlayerByIdRs response) {
        return serializer.serialize(response);
    }
}
