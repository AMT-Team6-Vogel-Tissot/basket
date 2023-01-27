package ch.heig.basket.api.services;

import ch.heig.basket.api.entities.BasketTeam;
import ch.heig.basket.api.exceptions.TeamNotFoundException;
import ch.heig.basket.api.repositories.TeamRepository;
import org.modelmapper.ModelMapper;
import org.openapitools.model.Team;
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
        List<BasketTeam> teamEntities = teamRepository.findAll();
        List<Team> teams = new ArrayList<>();

        for (BasketTeam teamEntity : teamEntities) {
            Team t;
            t = modelMapper.map(teamEntity, Team.class);
            teams.add(t);
        }

        return teams;
    }

    public Team getTeam(int id){
        BasketTeam teamEntity = teamRepository.findById(id);

        return modelMapper.map(teamEntity, Team.class);
    }



    public int addTeam(Team team) {
        Team newTeam;
        BasketTeam basketTeam = teamRepository.save(new BasketTeam(team.getId(), team.getName()));
        newTeam = modelMapper.map(basketTeam, Team.class);

        return newTeam.getId();
    }

}
