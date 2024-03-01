package ru.panifidkin.lvlproject.rq;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SrvGetPlayerByIdRq {
    private String playerId;
}
