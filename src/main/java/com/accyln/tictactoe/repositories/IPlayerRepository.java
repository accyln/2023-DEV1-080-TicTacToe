package com.accyln.tictactoe.repositories;

import com.accyln.tictactoe.entities.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPlayerRepository extends CrudRepository<Player,Long> {
}
