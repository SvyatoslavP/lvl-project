package ru.panifidkin.lvlproject.rs;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder
public class TeamDto {

    private String teamName;
    private BigDecimal teamRating;
    private String foundingDate;
    private List<PlayerDto> players;
}