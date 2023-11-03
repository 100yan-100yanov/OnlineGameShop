package com.playtray.model.entity.offer;

import com.playtray.model.entity.product.Console;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "console_offers")
public class ConsoleOffer extends BaseOffer{

    private Console console;
}
