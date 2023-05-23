package com.accyln.tictactoe.entities;

import com.accyln.tictactoe.enums.GameStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Game")
@Data @Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ElementCollection
    private char[][] board=new char[3][3];
    @Column(name = "Player1_Id")
    private Long player1_id;
    @Column(name = "Player2_id")
    private Long player2_id;
    @Column(name = "LastPlayedSign")
    private char lastPlayedSign='O';
    @Column(name = "GameStatus")
    private GameStatus gameStatus= GameStatus.ONGOING;
    @Column(name = "Winner")
    private char winner;
    @Column(name = "MoveCount")
    private int moveCount;
    public Game(Long player1_id,Long player2_id){
        this.player1_id=player1_id;
        this.player2_id=player2_id;
    }
}
