package ch.heig.basket.api.services;

import ch.heig.basket.api.entities.BasketPlayer;
import ch.heig.basket.api.exceptions.PlayerNotFoundException;
import ch.heig.basket.api.exceptions.TeamNotFoundException;
import ch.heig.basket.api.repositories.PlayerRepository;
import ch.heig.basket.api.repositories.TeamRepository;
import ch.heig.basket.api.repositories.TrophyRepository;
import org.modelmapper.ModelMapper;
import org.openapitools.model.Player;
import org.openapitools.model.PlayerID;
import org.openapitools.model.Playerobj;
import org.openapitools.model.Trophy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    private final ModelMapper modelMapper;

    public PlayerService(PlayerRepository playerRepository, TeamRepository teamRepository, TrophyRepository trophyRepository) {
        this.playerRepository = playerRepository;
        modelMapper = new ModelMapper();
    }

    public List<Playerobj> getPlayers() {
        return playerRepository.findAll().stream().map(playerEntity -> modelMapper.map(playerEntity, Playerobj.class)).toList();
    }

    public int addPlayer(PlayerID player) throws TeamNotFoundException {

        return modelMapper.map(playerRepository.save(modelMapper.map(player, BasketPlayer.class)), PlayerID.class).getId();
    }

    public Playerobj getPlayer(Integer id) throws PlayerNotFoundException {

        Optional<BasketPlayer> basketPlayer = playerRepository.findById(id);

        Playerobj playerobj = modelMapper.map(basketPlayer, Playerobj.class);

        if(playerobj == null){
            throw new PlayerNotFoundException(id);
        }

        return playerobj;
    }

}
