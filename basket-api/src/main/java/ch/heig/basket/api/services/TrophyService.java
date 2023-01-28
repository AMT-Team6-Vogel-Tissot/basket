package ch.heig.basket.api.services;

import ch.heig.basket.api.entities.BasketTrophy;
import ch.heig.basket.api.exceptions.TeamNotFoundException;
import ch.heig.basket.api.exceptions.TrophyNotFoundException;
import ch.heig.basket.api.repositories.TrophyRepository;
import org.modelmapper.ModelMapper;
import org.openapitools.model.Trophy;
import org.openapitools.model.TrophyPlayers;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrophyService {

    private final TrophyRepository trophyRepository;
    private final ModelMapper modelMapper;

    public TrophyService(TrophyRepository trophyRepository) {
        this.trophyRepository = trophyRepository;
        modelMapper = new ModelMapper();
    }

    public List<Trophy> getTrophies(){
        return trophyRepository.findAll().stream().map(basketTrophy -> modelMapper.map(basketTrophy, Trophy.class)).toList();
    }

    public Trophy getTrophy(int id) throws TrophyNotFoundException {
        BasketTrophy basketTrophy = trophyRepository.findById(id);

        if(basketTrophy == null) {
            throw new TrophyNotFoundException(id);
        }

        return modelMapper.map(basketTrophy, Trophy.class);
    }

    public int addTrophy(Trophy trophy) {
        BasketTrophy basketTrophy = trophyRepository.save(modelMapper.map(trophy, BasketTrophy.class));

        return modelMapper.map(basketTrophy, Trophy.class).getId();
    }

    public TrophyPlayers getTrophyPlayers(int id) throws TrophyNotFoundException {
        BasketTrophy basketTrophy = trophyRepository.findById(id);

        if(basketTrophy == null) {
            throw new TeamNotFoundException(id);
        }

        return modelMapper.map(basketTrophy, TrophyPlayers.class);
    }
}
