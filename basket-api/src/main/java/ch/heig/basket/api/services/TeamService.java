package ch.heig.basket.api.services;

import ch.heig.basket.api.entities.BasketTeam;
import ch.heig.basket.api.exceptions.TeamNotFoundException;
import ch.heig.basket.api.repositories.TeamRepository;
import org.modelmapper.ModelMapper;
import org.openapitools.model.Playerobj;
import org.openapitools.model.Team;
import org.openapitools.model.TeamPlayers;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final ModelMapper modelMapper;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
        modelMapper = new ModelMapper();
    }

    public List<Team> getTeams(){
        return teamRepository.findAll().stream().map(basketTeam -> modelMapper.map(basketTeam, Team.class)).toList();
    }

    public Team getTeam(int id) throws TeamNotFoundException {
        BasketTeam basketTeam = teamRepository.findById(id);

        if(basketTeam == null) {
            throw new TeamNotFoundException(id);
        }

        return modelMapper.map(basketTeam, Team.class);
    }

    public int addTeam(Team team) {
        BasketTeam basketTeam = teamRepository.save(modelMapper.map(team, BasketTeam.class));

        return modelMapper.map(basketTeam, Team.class).getId();
    }

    public TeamPlayers getTeamPlayers(int id) throws TeamNotFoundException {
        BasketTeam basketTeam = teamRepository.findById(id);

        if(basketTeam == null) {
            throw new TeamNotFoundException(id);
        }

        return modelMapper.map(basketTeam, TeamPlayers.class);
    }
}
