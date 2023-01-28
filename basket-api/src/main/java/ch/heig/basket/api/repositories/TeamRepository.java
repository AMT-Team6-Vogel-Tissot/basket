package ch.heig.basket.api.repositories;

import ch.heig.basket.api.entities.BasketTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<BasketTeam, Integer> {

}
