package com.accyln.tictactoe.DTOs;

import com.accyln.tictactoe.entities.Game;

public class MakeAMoveRequestDto {
    private Game game;
    private int rowId;
    private int colId;
    private char sign;

    public MakeAMoveRequestDto(Game game, int rowId, int colId, char sign) {
        this.game = game;
        this.rowId = rowId;
        this.colId = colId;
        this.sign = sign;
    }
    public MakeAMoveRequestDto(){

    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getRowId() {
        return rowId;
    }

    public void setRowId(int rowId) {
        this.rowId = rowId;
    }

    public int getColId() {
        return colId;
    }

    public void setColId(int colId) {
        this.colId = colId;
    }

    public char getSign() {
        return sign;
    }

    public void setSign(char sign) {
        this.sign = sign;
    }
}
