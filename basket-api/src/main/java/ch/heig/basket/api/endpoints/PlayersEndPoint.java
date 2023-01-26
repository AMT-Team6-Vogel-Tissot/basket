package ch.heig.basket.api.endpoints;

import ch.heig.basket.api.services.PlayersService;
import org.openapitools.api.PlayersApi;
import org.openapitools.model.Player;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
public class PlayersEndPoint implements PlayersApi{

    private final PlayersService playersService;

    public PlayersEndPoint(PlayersService playersService) {
        this.playersService = playersService;
    }

    @Override
    public ResponseEntity<List<Player>> getPlayers() {
        return ResponseEntity.ok(playersService.getPlayers());
    }

    @Override
    public ResponseEntity<Player> addPlayer(Player player) {
        Player p = playersService.addPlayer(player);

        return ResponseEntity.created(URI.create("/players/" + p.getId())).body(p);
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
