package ch.heig.basket.api.entities;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Trophy")
@Table(name = "trophies")
public class BasketTrophy {


    @Id
    private int trophy_id;
    private String trophy_name;

    @ManyToMany
    @JoinTable(name = "players_trophies", joinColumns = @JoinColumn(name= "trophy_id"), inverseJoinColumns = @JoinColumn(name="player_id"))
    private List<BasketPlayer> players = new ArrayList<>();


    public BasketTrophy() {}

    public BasketTrophy(int id, String name, List<BasketPlayer> players) {
        this.trophy_id = id;
        this.trophy_name = name;
        this.players = players;
    }

    public String getTrophy_name() {
        return trophy_name;
    }

    public void setTrophy_name(String name) {
        this.trophy_name = name;
    }

    public List<BasketPlayer> getPlayers() {
        return players;
    }

    public void setPlayers(List<BasketPlayer> players) {
        this.players = players;
    }
}
