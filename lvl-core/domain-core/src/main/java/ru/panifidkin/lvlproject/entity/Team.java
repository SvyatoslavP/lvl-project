package ru.panifidkin.lvlproject.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "teams", schema = "public")
public class Team {

    @Id
    @Builder.Default
    private final String persistenceId = UUID.randomUUID().toString();

    private String teamName;
    @Column(length = 17, precision = 2)
    private BigDecimal teamRating;
    @Column(columnDefinition = "date")
    private LocalDate foundingDate;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Player.class, orphanRemoval = true)
    private List<Player> players;

}
