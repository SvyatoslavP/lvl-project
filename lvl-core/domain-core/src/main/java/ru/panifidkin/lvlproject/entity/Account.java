package ru.panifidkin.lvlproject.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Account {

    @Column(length = 20, nullable = false, unique = true)
    private String login;
    @Column(length = 20, nullable = false)
    private String password;

}
