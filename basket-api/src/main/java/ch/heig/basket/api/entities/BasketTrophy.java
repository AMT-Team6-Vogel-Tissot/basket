package ch.heig.basket.api.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "Trophy")
@Table(name = "trophies")
public class BasketTrophy {


    @Id
    private String trophy_name;


    public BasketTrophy() {}

    public BasketTrophy(String name) {
        this.trophy_name = name;

    }

    public String getName() {
        return trophy_name;
    }

    public void setName(String name) {
        this.trophy_name = name;
    }


}
