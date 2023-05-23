package com.accyln.tictactoe.repositories;

import com.accyln.tictactoe.entities.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGameRepository extends CrudRepository<Game,Long> {
}
