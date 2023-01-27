package ch.heig.basket.api.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "Team")
@Table(name = "teams")
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class BasketTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<BasketPlayer> basketPlayers;

}
