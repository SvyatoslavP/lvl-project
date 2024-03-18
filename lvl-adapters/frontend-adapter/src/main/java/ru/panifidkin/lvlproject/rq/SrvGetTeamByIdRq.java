package ru.panifidkin.lvlproject.rq;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SrvGetTeamByIdRq {

    private String teamId;
}
