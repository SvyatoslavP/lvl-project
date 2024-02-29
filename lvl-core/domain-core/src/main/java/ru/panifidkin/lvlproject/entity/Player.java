package ru.panifidkin.lvlproject.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "players", schema = "public")
public class Player {

    @Id
    @Builder.Default
    private final String persistenceId = UUID.randomUUID().toString();

    private String firstName;
    private String lastName;
    private LocalDate birthDate;
}
