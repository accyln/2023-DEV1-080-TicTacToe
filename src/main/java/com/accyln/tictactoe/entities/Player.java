package com.accyln.tictactoe.entities;


public class Player {

    private Long id;
    private String name;
    private char sign;

    public Player(char sign) {
        this.sign=sign;
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