package ch.heig.basket.api.services;

import ch.heig.basket.api.entities.BasketPlayer;
import ch.heig.basket.api.entities.BasketTeam;
import ch.heig.basket.api.entities.BasketTrophy;
import ch.heig.basket.api.exceptions.PlayerNotFoundException;
import ch.heig.basket.api.exceptions.TeamNotFoundException;
import ch.heig.basket.api.repositories.PlayerRepository;
import ch.heig.basket.api.repositories.TeamRepository;
import ch.heig.basket.api.repositories.TrophyRepository;
import org.modelmapper.ModelMapper;
import org.openapitools.model.Player;
import org.openapitools.model.Playerobj;
import org.openapitools.model.Trophy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;
    private final TrophyRepository trophyRepository;
    private final ModelMapper modelMapper;

    public PlayerService(PlayerRepository playerRepository, TeamRepository teamRepository, TrophyRepository trophyRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
        this.trophyRepository = trophyRepository;
        modelMapper = new ModelMapper();
    }

    public List<Playerobj> getPlayers(){
        List<BasketPlayer> playerEntities = playerRepository.findAll();

        return playerEntities.stream()
                .map(playerEntity -> modelMapper.map(playerEntity, Playerobj.class))
                .toList();
    }
/*
    public List<Player> getPlayers(){
        List<BasketPlayer> playerEntities = playerRepository.findAll();
        List<Player> players = new ArrayList<>();

        for (BasketPlayer playerEntity : playerEntities) {
            Player p;
            p = modelMapper.map(playerEntity, Player.class);
            p.setFkTeam(playerEntity.getFq_name_team().getTeam_id());
            List<Trophy> t = new ArrayList<>();
            for (BasketTrophy basketTrophy: playerEntity.getTrophies()) {
                Trophy trophy = new Trophy();
                trophy.setName(basketTrophy.getTrophy_name());
                t.add(trophy);
            }
            p.setTrophee(t);

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

        BasketPlayer basketPlayer = playerRepository.save(new BasketPlayer(player.getId(), player.getName(), player.getSurname(), basketTeam, null));

        newPlayer = modelMapper.map(basketPlayer, Player.class);
        newPlayer.setFkTeam(player.getFkTeam());

        return newPlayer.getId();
    }

    public Player getPlayer(Integer id) throws PlayerNotFoundException {
        BasketPlayer p = playerRepository.findById(id.intValue());
        List<BasketTrophy> trophies = trophyRepository.findAll();

        if(p == null) {
            throw new PlayerNotFoundException(id);
        }

        Player player = modelMapper.map(p, Player.class);
        player.setFkTeam(p.getFq_name_team().getTeam_id());

        return player;
    }
*/
}
