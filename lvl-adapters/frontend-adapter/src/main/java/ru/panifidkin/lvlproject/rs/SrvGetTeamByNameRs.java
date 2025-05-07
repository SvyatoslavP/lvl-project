package ru.panifidkin.lvlproject.rs;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SrvGetTeamByNameRs {

    private TeamDto team;
    private Status status;
}