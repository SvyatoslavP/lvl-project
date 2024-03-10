package ru.panifidkin.lvlproject.serializers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.panifidkin.lvlproject.rq.SrvGetTeamByIdRq;
import ru.panifidkin.lvlproject.rs.SrvGetTeamByIdRs;
import ru.panifidkin.lvlproject.serialize.ObjectSerializer;

@Component
@AllArgsConstructor
public class FrontendTeamSerializer {

    private final ObjectSerializer<SrvGetTeamByIdRq> deserializer;
    private final ObjectSerializer<SrvGetTeamByIdRs> serializer;

    public SrvGetTeamByIdRq deserialize(String json) {
        return deserializer.deserialize(json, SrvGetTeamByIdRq.class);
    }

    public String serialize(SrvGetTeamByIdRs response) {
        return serializer.serialize(response);
    }
}
