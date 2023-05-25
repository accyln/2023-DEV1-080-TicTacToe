package com.accyln.tictactoe.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Player")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "PlayerName")
    private String playerName;
    @Column(name = "PlayerSign")
    private char playerSign;

    public Player(String playerName, char playerSign){
        this.playerName=playerName;
        this.playerSign=playerSign;
    }
}
