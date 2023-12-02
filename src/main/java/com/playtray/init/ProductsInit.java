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
                    .setSummary("summary")
                    .setDescription("Last generation game console by Sony")
                    .setImageUrl("https://cdn.ozone.bg/media/catalog/product/cache/1/image/a4e40ebdc3e371adff845072e1c73f37/p/l/d5cbabf70bde6f305d9d3ba3f76ae93f/playstation-5-standard-edition-31.jpg");

            Product consolePS4 = new Product();

            consolePS4
                    .setCategory(ProductCategory.CONSOLE)
                    .setPlatform(PlatformName.PS4)
                    .setName("Sony")
                    .setPrice(BigDecimal.valueOf(600.0))
                    .setSummary("summary")
                    .setDescription("Previous generation game console by Sony")
                    .setImageUrl("https://m.media-amazon.com/images/I/51HPlBnOoBL._AC_SL1500_.jpg");

            Product consoleSwitch = new Product();

            consoleSwitch
                    .setCategory(ProductCategory.CONSOLE)
                    .setPlatform(PlatformName.SWITCH)
                    .setName("Nintendo")
                    .setPrice(BigDecimal.valueOf(600.0))
                    .setSummary("summary")
                    .setDescription("A hybrid console that can be played on the go or at home docked at your TV")
                    .setImageUrl("https://cdn.ozone.bg/media/catalog/product/cache/1/image/a4e40ebdc3e371adff845072e1c73f37/n/i/b0ef8356bd869c0d2eae15b2c0e30185/nintendo-switch---red-and-blue-310.jpg");

            Product consoleXBOX = new Product();

            consoleXBOX
                    .setCategory(ProductCategory.CONSOLE)
                    .setPlatform(PlatformName.XBOX)
                    .setName("Microsoft")
                    .setPrice(BigDecimal.valueOf(600.0))
                    .setSummary("summary")
                    .setDescription("Last generation game console by Microsoft")
                    .setImageUrl("https://m.media-amazon.com/images/I/613bi6ajL6L._AC_SL1500_.jpg");

            Product gameEldenRing = new Product();

            gameEldenRing
                    .setCategory(ProductCategory.GAME)
                    .setPlatform(PlatformName.PS5)
                    .setName("Elden Ring")
                    .setPrice(BigDecimal.valueOf(120.0))
                    .setSummary("summary")
                    .setDescription("Winner of Game of the year award, Elden Ring offers as much fun and challenge as its predecessors.")
                    .setImageUrl("https://cdn.ozone.bg/media/catalog/product/cache/1/image/800x996/a4e40ebdc3e371adff845072e1c73f37/e/l/a1f7cfe3c7450a1bd7cb16c6896b594e/elden-ring-ps5-30.jpg");

            Product gameGhostOfTsushima = new Product();

            gameGhostOfTsushima
                    .setCategory(ProductCategory.GAME)
                    .setPlatform(PlatformName.PS4)
                    .setName("Ghost of Tsushima")
                    .setPrice(BigDecimal.valueOf(70.0))
                    .setSummary("summary")
                    .setDescription("Experience the stunning visuals and refined combat as a samurai in feudal Japan.")
                    .setImageUrl("https://cdn.ozone.bg/media/catalog/product/cache/1/image/a4e40ebdc3e371adff845072e1c73f37/g/o/a0828919ac9ec15a4d5e1c5e9a46c5a9/ghost-of-tsushima---director-s-cut-ps4-30.jpg");

            Product gameFinalFantasyXVI = new Product();

            gameFinalFantasyXVI
                    .setCategory(ProductCategory.GAME)
                    .setPlatform(PlatformName.PS5)
                    .setName("Final Fantasy XVI")
                    .setPrice(BigDecimal.valueOf(99.99))
                    .setSummary("summary")
                    .setDescription("An action role-playing game developed and published by Square Enix. The sixteenth main installment in the Final Fantasy series.")
                    .setImageUrl("https://cdn.ozone.bg/media/catalog/product/cache/1/image/a4e40ebdc3e371adff845072e1c73f37/f/i/b31715f0325df432381707605d3e9d91/final-fantasy-xvi-ps5-30.jpg");

            Product accessoryXboxHeadset = new Product();

            accessoryXboxHeadset
                    .setCategory(ProductCategory.ACCESSORY)
                    .setPlatform(PlatformName.XBOX)
                    .setName("Xbox Series X/S")
                    .setPrice(BigDecimal.valueOf(150.0))
                    .setSummary("summary")
                    .setDescription("Light and flexible, the wired stereo headphones Xbox stand out with their ergonomic design for maximum comfort.")
                    .setImageUrl("https://m.media-amazon.com/images/I/51ODvrKqxTL._SL1200_.jpg");

            Product accessoryKeyboardPC = new Product();

            accessoryKeyboardPC
                    .setCategory(ProductCategory.ACCESSORY)
                    .setPlatform(PlatformName.PC)
                    .setName("Razer BlackWidow V3")
                    .setPrice(BigDecimal.valueOf(140.0))
                    .setSummary("summary")
                    .setDescription("BlackWidow V3 Tenkeyless continues the Razer tradition in creating successful products.")
                    .setImageUrl("https://cdn.ozone.bg/media/catalog/product/cache/1/image/a4e40ebdc3e371adff845072e1c73f37/8/1/bc4cf409725f7a98c87293a191df4ae8/mehanichna-klaviatura-razer---blackwidow-v3-tenkeyless--razer-green-31.jpg");

            List<Product> products =
                    List.of(consolePS4,
                            consoleSwitch,
                            consoleXBOX,
                            consolePS5,
                            gameEldenRing,
                            gameGhostOfTsushima,
                            gameFinalFantasyXVI,
                            accessoryKeyboardPC,
                            accessoryXboxHeadset);

            productRepository.saveAll(products);
        }
    }
}
