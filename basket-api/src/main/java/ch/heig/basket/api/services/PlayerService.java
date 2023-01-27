package ch.heig.basket.api.services;

import ch.heig.basket.api.entities.BasketPlayer;
import ch.heig.basket.api.entities.BasketTeam;
import ch.heig.basket.api.exceptions.TeamNotFoundException;
import ch.heig.basket.api.repositories.PlayerRepository;
import ch.heig.basket.api.repositories.TeamRepository;
import org.modelmapper.ModelMapper;
import org.openapitools.model.Player;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;
    private final ModelMapper modelMapper;

    public PlayerService(PlayerRepository playerRepository, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
        modelMapper = new ModelMapper();
    }

    public List<Player> getPlayers(){
        List<BasketPlayer> playerEntities = playerRepository.findAll();
        List<Player> players = new ArrayList<>();

        for (BasketPlayer playerEntity : playerEntities) {
            Player p;
            p = modelMapper.map(playerEntity, Player.class);
            p.setFkTeam(playerEntity.getFq_name_team().getId());

            players.add(p);
        }

        return players;
    }

    public int addPlayer(Player player) throws TeamNotFoundException {

        Player newPlayer;

        BasketTeam basketTeam = teamRepository.findById(player.getFkTeam().intValue());

        if(basketTeam == null){
            throw new TeamNotFoundException(player.getFkTeam());
        }

        BasketPlayer basketPlayer = playerRepository.save(new BasketPlayer(player.getId(), player.getName(), player.getSurname(), basketTeam));

        newPlayer = modelMapper.map(basketPlayer, Player.class);
        newPlayer.setFkTeam(player.getFkTeam());

        return newPlayer.getId();
    }

}
