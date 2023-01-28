package ch.heig.basket.api.repositories;

import ch.heig.basket.api.entities.BasketPlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<BasketPlayer, Integer> {

}