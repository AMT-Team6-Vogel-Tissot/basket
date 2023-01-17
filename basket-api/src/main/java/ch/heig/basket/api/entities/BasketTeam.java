package ch.heig.basket.api.entities;


import jakarta.persistence.*;

@Entity(name = "Team")
@Table(name = "teams")
public class BasketTeam {


    @Id
    private String team_name;


    public BasketTeam() {}

    public BasketTeam(String name) {
        this.team_name = name;

    }

    public String getName() {
        return team_name;
    }

    public void setName(String name) {
        this.team_name = name;
    }


}
