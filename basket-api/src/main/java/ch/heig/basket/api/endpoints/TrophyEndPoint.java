package ch.heig.basket.api.endpoints;

import ch.heig.basket.api.exceptions.PlayerNotFoundException;
import ch.heig.basket.api.exceptions.TeamNotFoundException;
import ch.heig.basket.api.services.TrophyService;
import org.openapitools.api.TrophiesApi;
import org.openapitools.model.Trophy;
import org.openapitools.model.TrophyPlayers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
public class TrophyEndPoint implements TrophiesApi {

    private final TrophyService trophyService;

    public TrophyEndPoint(TrophyService trophyService) {
        this.trophyService = trophyService;
    }

    @Override
    public ResponseEntity<List<Trophy>> getTrophies() {
        return ResponseEntity.ok(trophyService.getTrophies());
    }

    @Override
    public ResponseEntity<Trophy> getTrophy(Integer id) {
        try{
            return ResponseEntity.ok(trophyService.getTrophy(id));
        } catch (PlayerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Void> addTrophy(Trophy trophy) {
        try{
            int t = trophyService.addTrophy(trophy);
            return ResponseEntity.created(URI.create("/teams/" + t)).build();
        } catch (TeamNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

    }

    @Override
    public ResponseEntity<TrophyPlayers> getTrophyPlayers(Integer id) {
        try{
            return ResponseEntity.ok(trophyService.getTrophyPlayers(id));
        } catch (PlayerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    }
