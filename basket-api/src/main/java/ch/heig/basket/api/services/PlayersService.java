package ch.heig.basket.api.services;

import ch.heig.basket.api.entities.BasketPlayer;
import ch.heig.basket.api.entities.BasketTeam;
import ch.heig.basket.api.repositories.PlayerRepository;
import ch.heig.basket.api.repositories.TeamRepository;
import org.openapitools.model.Player;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayersService {

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    public PlayersService(PlayerRepository playerRepository, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    public List<Player> getPlayers(){
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

        return players;
    }

    public Player addPlayer(Player player) {

        Player newPlayer = new Player();

        BasketTeam basketTeam = teamRepository.findById(player.getFkTeam().intValue());

        BasketPlayer basketPlayer = playerRepository.save(new BasketPlayer(player.getId(), player.getName(), player.getSurname(), basketTeam));

        newPlayer.setId(basketPlayer.getId());
        newPlayer.setName(basketPlayer.getName());
        newPlayer.setSurname(basketPlayer.getSurname());
        newPlayer.setFkTeam(basketPlayer.getFq_name_team().getTeam_id());

        return newPlayer;
    }

}
