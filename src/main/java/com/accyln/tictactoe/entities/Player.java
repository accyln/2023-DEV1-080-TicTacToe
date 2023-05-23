package com.accyln.tictactoe.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Player")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "PlayerName")
    private String playerName;
    @Column(name = "PlayerSign")
    private char playerSign;

    public Player(String playerName,char playerSign) {
        this.playerName=playerName;
        this.playerSign=playerSign;
    }

    public Player(Long id, String playerName, char playerSign) {
        this.id = id;
        this.playerName = playerName;
        this.playerSign = playerSign;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return playerName;
    }

    public void setName(String naplayerNameme) {
        this.playerName = playerName;
    }

    public char getSign() {
        return playerSign;
    }

    public void setSign(char playerSign) {
        this.playerSign = playerSign;
    }
}
