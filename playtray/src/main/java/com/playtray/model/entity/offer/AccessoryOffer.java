package com.playtray.model.entity.offer;

import com.playtray.model.entity.product.Accessory;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "accessory_offers")
public class AccessoryOffer extends BaseOffer {

    private Accessory accessory;

}
