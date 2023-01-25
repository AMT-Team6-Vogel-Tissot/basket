package ch.heig.basket.api.endpoints;

import ch.heig.basket.api.entities.BasketPlayer;
import ch.heig.basket.api.entities.BasketTeam;
import ch.heig.basket.api.repositories.PlayerRepository;
import ch.heig.basket.api.repositories.TeamRepository;
import org.openapitools.api.PlayersApi;
import org.openapitools.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PlayersEndPoint implements PlayersApi{

    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private TeamRepository teamRepository;

    @Override
    public ResponseEntity<List<Player>> getPlayers() {
        List<BasketPlayer> playerEntities = playerRepository.findAll();
        List<Player> players = new ArrayList<>();
        for (BasketPlayer playerEntity : playerEntities) {
            Player player = new Player();
            player.setId(playerEntity.getId());
            player.setName(playerEntity.getName());
            player.setSurname(playerEntity.getSurname());
            player.setFkTeam(playerEntity.getFq_name_team().getTeam_id());
            players.add(player);
        }
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Player> addPlayer(Player player) {

        Player newPlayer = new Player();

        BasketTeam basketTeam = teamRepository.findById(player.getFkTeam().intValue());

        BasketPlayer basketPlayer = playerRepository.save(new BasketPlayer(player.getId(), player.getName(), player.getSurname(), basketTeam));

        newPlayer.setId(basketPlayer.getId());
        newPlayer.setName(basketPlayer.getName());
        newPlayer.setSurname(basketPlayer.getSurname());
        newPlayer.setFkTeam(basketPlayer.getFq_name_team().getTeam_id());

        return new ResponseEntity<>(newPlayer, HttpStatus.CREATED);
    }

/*
    @Override
    public ResponseEntity<Void> addQuote(@RequestBody Quote quote) {
        QuoteEntity quoteEntity = new QuoteEntity();
        quoteEntity.setAuthor(quote.getAuthor());
        quoteEntity.setCitation(quote.getCitation());
        QuoteEntity quoteAdded = quoteRepository.save(quoteEntity);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(quoteAdded.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @Override
    public ResponseEntity<Quote> getQuote(Integer id) {
        Optional<QuoteEntity> opt = quoteRepository.findById(id);
        if (opt.isPresent()) {
            QuoteEntity quoteEntity = opt.get();
            Quote quote = new Quote();
            quote.setId(quoteEntity.getId());
            quote.setAuthor(quoteEntity.getAuthor());
            quote.setCitation(quoteEntity.getCitation());
            return new ResponseEntity<Quote>(quote, HttpStatus.OK);
        } else {
//            return ResponseEntity.notFound().build();
            throw new QuoteNotFoundException(id);
        }
    }
*/
}
