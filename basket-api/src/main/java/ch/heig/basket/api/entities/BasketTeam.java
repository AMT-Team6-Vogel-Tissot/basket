package ch.heig.basket.api.entities;


import jakarta.persistence.*;

@Entity(name = "Team")
@Table(name = "teams")
public class BasketTeam {


    @Id
    private String name;


    public BasketTeam() {}

    public BasketTeam(String name) {
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void setName(String author) {
        this.name = author;
    }


}
