package com.accyln.tictactoe.DTOs;

public class CreatePlayerRequestDto {
    private String name;
    private char sign;
    public CreatePlayerRequestDto(String name, char sign) {
        this.name = name;
        this.sign = sign;
    }
    public CreatePlayerRequestDto(){

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
