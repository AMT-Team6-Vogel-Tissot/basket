package ch.heig.basket.api.repositories;

import ch.heig.basket.api.entities.BasketTrophy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrophyRepository extends JpaRepository<BasketTrophy, Integer> {

    BasketTrophy findById(int id);
}
