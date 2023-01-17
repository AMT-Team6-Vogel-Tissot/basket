package ch.heig.basket.api.repositories;

import ch.heig.basket.api.entities.QuoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteRepository extends JpaRepository<QuoteEntity, Integer> {
    QuoteEntity findById(int id);
    List<QuoteEntity> findByAuthorLike(String pattern);}