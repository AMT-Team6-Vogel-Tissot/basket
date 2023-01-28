package ch.heig.basket.api.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "Player")
@Table(name = "players")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class BasketPlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String surname;

    @ManyToOne(optional = false)
    private BasketTeam team;

    @ManyToMany
    private List<BasketTrophy> trophies;
}
