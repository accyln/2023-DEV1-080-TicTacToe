package com.accyln.tictactoe.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Player")
@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "PlayerName")
    private String playerName;
    @Column(name = "PlayerSign")
    private char playerSign;
}
