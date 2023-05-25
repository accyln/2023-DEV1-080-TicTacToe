package com.accyln.tictactoe.entities;

import com.accyln.tictactoe.enums.GameStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Game")
@Getter
@Setter
@NoArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ElementCollection
    private char[][] board=new char[3][3];
    @Column(name = "Player1_Id")
    private Long player1Id;
    @Column(name = "Player2_id")
    private Long player2Id;
    @Column(name = "LastPlayedSign")
    private char lastPlayedSign='O';
    @Column(name = "GameStatus")
    private GameStatus gameStatus= GameStatus.ONGOING;
    @Column(name = "Winner")
    private char winner;
    @Column(name = "MoveCount")
    private int moveCount;
    public Game(Long player1Id,Long player2Id){
        this.player1Id =player1Id;
        this.player2Id =player2Id;
    }
}
