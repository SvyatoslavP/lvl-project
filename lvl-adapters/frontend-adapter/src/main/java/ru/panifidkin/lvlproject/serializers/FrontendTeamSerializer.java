package ru.panifidkin.lvlproject.serializers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.panifidkin.lvlproject.rq.SrvGetTeamByIdRq;
import ru.panifidkin.lvlproject.rq.SrvGetTeamByNameRq;
import ru.panifidkin.lvlproject.rs.SrvGetTeamByIdRs;
import ru.panifidkin.lvlproject.rs.SrvGetTeamByNameRs;
import ru.panifidkin.lvlproject.serialize.ObjectSerializer;

@Component
@AllArgsConstructor
public class FrontendTeamSerializer {

    private final ObjectSerializer<SrvGetTeamByIdRq> idDeserializer;
    private final ObjectSerializer<SrvGetTeamByIdRs> idSerializer;
    private final ObjectSerializer<SrvGetTeamByNameRq> nameDeserializer;
    private final ObjectSerializer<SrvGetTeamByNameRs> nameSerializer;

    public SrvGetTeamByIdRq idDeserialize(String json) {
        return idDeserializer.deserialize(json, SrvGetTeamByIdRq.class);
    }

    public String idSerialize(SrvGetTeamByIdRs response) {
        return idSerializer.serialize(response);
    }

    public SrvGetTeamByNameRq nameDeserialize(String json) {
        return nameDeserializer.deserialize(json, SrvGetTeamByNameRq.class);
    }

    public String nameSerialize(SrvGetTeamByNameRs response) {
        return nameSerializer.serialize(response);
    }
}