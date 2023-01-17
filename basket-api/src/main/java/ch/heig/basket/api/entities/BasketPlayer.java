package ch.heig.basket.api.entities;


import jakarta.persistence.*;

@Entity(name = "Player")
@Table(name = "players")
public class BasketPlayer {


    @Id
    private int id;

    private String player_name;

    private String player_surname;

    @ManyToOne @JoinColumn(name="fq_team_name", nullable = false)
    private BasketTeam fq_name_team;


    public BasketPlayer() {}

    public BasketPlayer(int id, String name, String surname) {
        this.id = id;
        this.player_name = name;
        this.player_surname = surname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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


}
