package com.accyln.tictactoe.entities;


import org.springframework.data.annotation.Id;

public class Player {

    @Id
    private Long id;
    private String name;
    private char sign;

    public Player(char sign) {
        this.sign=sign;
    }

    public Player(Long id, String name, char sign) {
        this.id = id;
        this.name = name;
        this.sign = sign;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getSign() {
        return sign;
    }

    public void setSign(char sign) {
        this.sign = sign;
    }
}
