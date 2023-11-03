package com.playtray.model.entity.offer;

import com.playtray.model.entity.product.Game;
import jakarta.persistence.*;

@Entity
@Table(name = "game_offers")
public class GameOffer extends BaseOffer {

    @ManyToOne
    private Game game;


}
