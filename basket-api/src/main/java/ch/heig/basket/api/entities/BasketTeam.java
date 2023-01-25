package ch.heig.basket.api.entities;


import jakarta.persistence.*;

@Entity(name = "Team")
@Table(name = "teams")
public class BasketTeam {

    @Id
    private int team_id;
    private String team_name;


    public BasketTeam() {}

    public BasketTeam(int id, String name) {
        this.team_id = id;
        this.team_name = name;

    }

    public String getName() {
        return team_name;
    }

    public void setName(String name) {
        this.team_name = name;
    }


}
