package ch.heig.basket.api.services;

import ch.heig.basket.api.entities.BasketPlayer;
import ch.heig.basket.api.exceptions.PlayerNotFoundException;
import ch.heig.basket.api.exceptions.TeamNotFoundException;
import ch.heig.basket.api.repositories.PlayerRepository;
import ch.heig.basket.api.repositories.TeamRepository;
import ch.heig.basket.api.repositories.TrophyRepository;
import org.modelmapper.ModelMapper;
import org.openapitools.model.PlayerID;
import org.openapitools.model.Playerobj;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final ModelMapper modelMapper;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
        modelMapper = new ModelMapper();
    }

    public List<Playerobj> getPlayers() {
        return playerRepository.findAll().stream().map(basketPlayer -> modelMapper.map(basketPlayer, Playerobj.class)).toList();
    }

    public int addPlayer(PlayerID player) throws TeamNotFoundException {

        return modelMapper.map(playerRepository.save(modelMapper.map(player, BasketPlayer.class)), PlayerID.class).getId();
    }

    public Playerobj getPlayer(Integer id) throws PlayerNotFoundException {

        Playerobj playerobj = modelMapper.map(playerRepository.findById(id), Playerobj.class);

        if(playerobj == null){
            throw new PlayerNotFoundException(id);
        }

        return playerobj;
    }

    public int putPlayer(PlayerID player) {

        BasketPlayer updateP = playerRepository.save(modelMapper.map(player, BasketPlayer.class));

        return updateP.getId();
    }

    public void deletePlayer(Integer id) throws PlayerNotFoundException {
        BasketPlayer p = modelMapper.map(playerRepository.findById(id), BasketPlayer.class);

        if(p == null){
            throw new PlayerNotFoundException(id);
        }

        playerRepository.delete(p);
    }

}
