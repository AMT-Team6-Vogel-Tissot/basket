package ch.heig.basket.api.endpoints;

import ch.heig.basket.api.exceptions.PlayerNotFoundException;
import ch.heig.basket.api.services.PlayerService;
import org.openapitools.api.PlayersApi;
import org.openapitools.model.PlayerID;
import org.openapitools.model.PlayerPatch;
import org.openapitools.model.Playerobj;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
public class PlayerEndPoint implements PlayersApi {

    private final PlayerService playerService;

    public PlayerEndPoint(PlayerService playerService) {
        this.playerService = playerService;
    }

    @Override
    public ResponseEntity<List<Playerobj>> getPlayers() {
        return ResponseEntity.ok(playerService.getPlayers());
    }

    @Override
    public ResponseEntity<Void> addPlayer(PlayerID player) {
        try{
            int p = playerService.addPlayer(player);

            return ResponseEntity.created(URI.create("/players/" + p)).build();
        } catch (PlayerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Playerobj> getPlayer(Integer id) {
        try{
            return ResponseEntity.ok(playerService.getPlayer(id));
        } catch (PlayerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Void> putPlayer(PlayerID player) {
            int p = playerService.putPlayer(player);

            if(p == player.getId()){
                return ResponseEntity.ok().header("Location", String.valueOf(URI.create("/players/" + p))).build();
            } else {
                return ResponseEntity.created(URI.create("/players/" + p)).build();
            }
    }

    @Override
    public ResponseEntity<Void> deletePlayer(Integer id) {
        try {
            playerService.deletePlayer(id);
        } catch (PlayerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> patchPlayer(PlayerPatch playerPatch) {
        try{
            playerService.patchPlayer(playerPatch);

            return ResponseEntity.ok().build();
        } catch (PlayerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
