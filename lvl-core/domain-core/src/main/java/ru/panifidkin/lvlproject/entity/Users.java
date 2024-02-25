package ru.panifidkin.lvlproject.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Getter
@Setter(AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
@Table(name = "users", schema = "public")
public class Users {

    @Id
    @Builder.Default
    @Column(name = "persistence_id")
    private final String persistenceId = UUID.randomUUID().toString();
    @Column(nullable = false, name = "first_name")
    private String firstName;

}
