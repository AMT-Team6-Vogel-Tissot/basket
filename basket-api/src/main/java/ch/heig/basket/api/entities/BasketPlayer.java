package ch.heig.basket.api.entities;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Player")
@Table(name = "players")
public class BasketPlayer {


    @Id
    private int player_id;

    private String player_name;

    private String player_surname;

    @ManyToOne @JoinColumn(name="fq_team_id", nullable = false)
    private BasketTeam fq_name_team;

    @ManyToMany
    @JoinTable(name = "players_trophies", joinColumns = @JoinColumn(name= "player_id"), inverseJoinColumns = @JoinColumn(name="trophy_id"))
    private List<BasketTrophy> trophies = new ArrayList<>();


    public BasketPlayer() {}

    public BasketPlayer(int id, String name, String surname, BasketTeam fq_name_team) {
        this.player_id = id;
        this.player_name = name;
        this.player_surname = surname;
        this.fq_name_team = fq_name_team;
    }

    public int getId() {
        return player_id;
    }

    public void setId(int id) {
        this.player_id = id;
    }

    public String getName() {
        return player_name;
    }

    public void setName(String name) {
        this.player_name = name;
    }

    public String getSurname() {
        return player_surname;
    }

    public void setSurname(String surname) {
        this.player_surname = surname;
    }

    public BasketTeam getFq_name_team(){ return fq_name_team; }
    public void setFq_name_team(BasketTeam fq_name_team){ this.fq_name_team = fq_name_team; }



}
