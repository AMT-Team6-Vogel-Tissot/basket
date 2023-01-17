package ch.heig.basket.api.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "Trophy")
@Table(name = "trophies")
public class BasketTrophy {


    @Id
    private String name;


    public BasketTrophy() {}

    public BasketTrophy(String name) {
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
