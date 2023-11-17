package com.playtray.init;

import com.playtray.model.entity.Product;
import com.playtray.model.enums.PlatformName;
import com.playtray.model.enums.ProductCategory;
import com.playtray.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class ProductsInit implements CommandLineRunner {

    private final ProductRepository productRepository;

    public ProductsInit(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (productRepository.count() <= 0) {

            Product consolePS5 = new Product();

            consolePS5
                    .setCategory(ProductCategory.CONSOLE)
                    .setPlatform(PlatformName.PS5)
                    .setName("Sony")
                    .setPrice(BigDecimal.valueOf(1000.0))
                    .setDescription("Last generation game console by Sony");

            Product consoleSwitch = new Product();

            consoleSwitch
                    .setCategory(ProductCategory.CONSOLE)
                    .setPlatform(PlatformName.SWITCH)
                    .setName("Nintendo")
                    .setPrice(BigDecimal.valueOf(600.0))
                    .setDescription("A hybrid console that can be played on the go or at home docked at your TV");

            Product gameEldenRing = new Product();

            gameEldenRing
                    .setCategory(ProductCategory.GAME)
                    .setPlatform(PlatformName.PS5)
                    .setName("Elden Ring")
                    .setPrice(BigDecimal.valueOf(120.0))
                    .setDescription("Winner of Game of the year award, Elden Ring offers as much fun and challenge as its predecessors.");

            Product gameGhostOfTsushima = new Product();

            gameGhostOfTsushima
                    .setCategory(ProductCategory.GAME)
                    .setPlatform(PlatformName.PS4)
                    .setName("Ghost of Tsushima")
                    .setPrice(BigDecimal.valueOf(70.0))
                    .setDescription("Experience the stunning visuals and refined combat as a samurai in feudal Japan.");

            Product accessoryXboxHeadset = new Product();

            accessoryXboxHeadset
                    .setCategory(ProductCategory.ACCESSORY)
                    .setPlatform(PlatformName.XBOX)
                    .setName("Microsoft Xbox Series X/S")
                    .setPrice(BigDecimal.valueOf(150.0))
                    .setDescription("Light and flexible, the wired stereo headphones Xbox stand out with their ergonomic design for maximum comfort.");

            Product accessoryKeyboardPC = new Product();

            accessoryKeyboardPC
                    .setCategory(ProductCategory.ACCESSORY)
                    .setPlatform(PlatformName.PC)
                    .setName("Razer BlackWidow V3")
                    .setPrice(BigDecimal.valueOf(140.0))
                    .setDescription("BlackWidow V3 Tenkeyless continues the Razer tradition in creating successful products.");

            List<Product> products =
                    List.of(consolePS5,
                            consoleSwitch,
                            gameEldenRing,
                            gameGhostOfTsushima,
                            accessoryXboxHeadset,
                            accessoryKeyboardPC);

            productRepository.saveAll(products);
        }
    }
}
