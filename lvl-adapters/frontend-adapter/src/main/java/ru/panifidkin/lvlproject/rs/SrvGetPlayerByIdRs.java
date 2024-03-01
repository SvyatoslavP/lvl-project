package ru.panifidkin.lvlproject.rs;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SrvGetPlayerByIdRs {
    private PlayerDto player;
    private Status status;
}
