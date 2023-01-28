package ch.heig.basket.api.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "Trophy")
@Table(name = "trophies")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class BasketTrophy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToMany(mappedBy = "trophies")
    private List<BasketPlayer> players;

}
