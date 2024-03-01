package ru.panifidkin.lvlproject.rs;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PlayerDto {
    private String firstName;
    private String lastName;
    private String birthDate;
}
