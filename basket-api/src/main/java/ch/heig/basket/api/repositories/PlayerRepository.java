package ch.heig.basket.api.repositories;

import ch.heig.basket.api.entities.BasketPlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public interface PlayerRepository extends JpaRepository<BasketPlayer, Integer> {
    BasketPlayer findById(int id);
    //List<BasketPlayer> findByPlayerLike(String pattern);

   // @Query("select f FROM Player f")
    //List<BasketPlayer> listBasketPlayer();
}