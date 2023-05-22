package com.accyln.tictactoe.entities;

public class Game {
    private Long id;
    private char[][] board=new char[3][3];
    private Long player1_id;
    private Long player2_id;
    private char lastPlayedSign='O';
    private String gameStatus="ONGOING";
    private char winner;
    private int moveCount;

    public Game(Long player1_id, Long player2_id) {
        this.player1_id = player1_id;
        this.player2_id = player2_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }

    public Long getPlayer1_id() {
        return player1_id;
    }

    public void setPlayer1_id(Long player1_id) {
        this.player1_id = player1_id;
    }

    public Long getPlayer2_id() {
        return player2_id;
    }

    public void setPlayer2_id(Long player2_id) {
        this.player2_id = player2_id;
    }
    public char getLastPlayedSign() {
        return lastPlayedSign;
    }
    public void setLastPlayedSign(char lastPlayedSign) {
        this.lastPlayedSign = lastPlayedSign;
    }

    public String getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(String gameStatus) {
        this.gameStatus = gameStatus;
    }

    public char getWinner() {
        return winner;
    }

    public void setWinner(char winner) {
        this.winner = winner;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public void setMoveCount(int moveCount) {
        this.moveCount = moveCount;
    }
}
