package ch.heig.basket.api.services;

import ch.heig.basket.api.entities.BasketPlayer;
import ch.heig.basket.api.entities.BasketTeam;
import ch.heig.basket.api.entities.BasketTrophy;
import ch.heig.basket.api.exceptions.PlayerNotFoundException;
import ch.heig.basket.api.exceptions.TeamNotFoundException;
import ch.heig.basket.api.repositories.PlayerRepository;
import org.modelmapper.ModelMapper;
import org.openapitools.model.PlayerID;
import org.openapitools.model.PlayerPatch;
import org.openapitools.model.Playerobj;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final ModelMapper modelMapper;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
        modelMapper = new ModelMapper();
    }

    public List<Playerobj> getPlayers() {
        return playerRepository.findAll().stream().map(basketPlayer -> modelMapper.map(basketPlayer, Playerobj.class)).collect(Collectors.toList());
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

    public void patchPlayer(PlayerPatch playerPatch) throws PlayerNotFoundException {

        BasketPlayer basketPlayer = modelMapper.map(playerRepository.findById(playerPatch.getId()), BasketPlayer.class);

        if(basketPlayer == null) {
            throw new PlayerNotFoundException(playerPatch.getId());
        }

        if(playerPatch.getName() != null) {
            basketPlayer.setName(playerPatch.getName());
        }
        if(playerPatch.getSurname() != null) {
            basketPlayer.setSurname(playerPatch.getSurname());
        }
        if(playerPatch.getTeamId() != null) {
            BasketTeam b = new BasketTeam();
            b.setId(playerPatch.getTeamId());
            basketPlayer.setTeam(b);
        }
        if(playerPatch.getTrophiesId() != null) {
            List<BasketTrophy> Listbt = new ArrayList<>();

            for (Integer t : playerPatch.getTrophiesId()) {
                BasketTrophy basketTrophy = modelMapper.map(playerRepository.findById(t), BasketTrophy.class);
                Listbt.add(basketTrophy);
            }
            basketPlayer.setTrophies(Listbt);
        }

        playerRepository.save(basketPlayer);
    }

}
