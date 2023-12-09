package com.playtray.init;

import com.playtray.model.entity.Cart;
import com.playtray.model.entity.Product;
import com.playtray.model.entity.User;
import com.playtray.model.enums.PlatformName;
import com.playtray.model.enums.ProductCategory;
import com.playtray.model.enums.UserRole;
import com.playtray.repository.ProductRepository;
import com.playtray.repository.RoleRepository;
import com.playtray.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class SeedDB implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public SeedDB(ProductRepository productRepository,
                  UserRepository userRepository,
                  RoleRepository roleRepository,
                  PasswordEncoder passwordEncoder) {

        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        if (productRepository.count() <= 0) {

            Product consolePS5 = new Product();
            Product consolePS4 = new Product();
            Product consoleSwitch = new Product();
            Product consoleXBOX = new Product();
            Product gameEldenRing = new Product();
            Product gameGhostOfTsushima = new Product();
            Product gameFinalFantasyXVI = new Product();
            Product accessoryXboxHeadset = new Product();
            Product accessoryKeyboardPC = new Product();
            Product accessoryControllerSwitch = new Product();

            List<Product> products =
                    List.of(
                            consolePS5
                                    .setCategory(ProductCategory.CONSOLE)
                                    .setPlatform(PlatformName.PS5)
                                    .setName("Sony")
                                    .setPrice(BigDecimal.valueOf(499.99))
                                    .setSummary("Experience lightning fast loading, deeper immersion with support for haptic feedback, adaptive triggers and an all-new generation of incredible PlayStation games.")
                                    .setDescription("The PlayStation 5 (PS5) is a home video game console developed by Sony Interactive Entertainment. The PS5 is part of the ninth generation of video game consoles, along with Microsoft's Xbox Series X/S consoles, which were released in the same month.")
                                    .setImageUrl("https://cdn.ozone.bg/media/catalog/product/cache/1/image/a4e40ebdc3e371adff845072e1c73f37/p/l/d5cbabf70bde6f305d9d3ba3f76ae93f/playstation-5-standard-edition-31.jpg"),

                            consolePS4
                                    .setCategory(ProductCategory.CONSOLE)
                                    .setPlatform(PlatformName.PS4)
                                    .setName("Sony")
                                    .setPrice(BigDecimal.valueOf(299.99))
                                    .setSummary("The PS4 enables the greatest game developers in the world to unlock their creativity and push the boundaries of play through a system that is tuned specifically to their needs.")
                                    .setDescription("The PlayStation 4 (PS4) is a home video game console developed by Sony Interactive Entertainment. A console of the eighth generation, it competes with Microsoft's Xbox One and Nintendo's Wii U and Switch.")
                                    .setImageUrl("https://m.media-amazon.com/images/I/51HPlBnOoBL._AC_SL1500_.jpg"),

                            consoleSwitch
                                    .setCategory(ProductCategory.CONSOLE)
                                    .setPlatform(PlatformName.SWITCH)
                                    .setName("Nintendo")
                                    .setPrice(BigDecimal.valueOf(299.99))
                                    .setSummary("Play at home or on the go with one system, instantly transforming from a home console you play on TV to a portable system you can play anywhere.")
                                    .setDescription("The Nintendo Switch is a video game console developed by Nintendo. Released in the middle of the eighth generation of home consoles, the Switch succeeded the Wii U and competes with Microsoft's Xbox One and Sony's PlayStation 4; it has also competed with the ninth-generation consoles, Microsoft's Xbox Series X/S and Sony's PlayStation 5.")
                                    .setImageUrl("https://cdn.ozone.bg/media/catalog/product/cache/1/image/a4e40ebdc3e371adff845072e1c73f37/n/i/b0ef8356bd869c0d2eae15b2c0e30185/nintendo-switch---red-and-blue-310.jpg"),

                            consoleXBOX
                                    .setCategory(ProductCategory.CONSOLE)
                                    .setPlatform(PlatformName.XBOX)
                                    .setName("Microsoft")
                                    .setPrice(BigDecimal.valueOf(389.99))
                                    .setSummary("With thousands of current favorites, new releases, and time-honored titles available, the Xbox Series S brings a library that’s ready for fun as soon as you open the box.")
                                    .setDescription("The Xbox Series S is fourth generation console in the Xbox series. The console is part of the ninth generation of video game consoles, which also includes Sony's PlayStation 5, released the same month.")
                                    .setImageUrl("https://m.media-amazon.com/images/I/613bi6ajL6L._AC_SL1500_.jpg"),

                            gameEldenRing
                                    .setCategory(ProductCategory.GAME)
                                    .setPlatform(PlatformName.PS5)
                                    .setName("Elden Ring")
                                    .setPrice(BigDecimal.valueOf(49.99))
                                    .setSummary("Journey the Lands Between, a realm where demigods rule and search for the Elden Ring, a powerful force that manifested itself as the physical concept of order.")
                                    .setDescription("Elden Ring is a 2022 action role-playing game developed by FromSoftware. It was directed by Hidetaka Miyazaki with worldbuilding provided by fantasy writer George R. R. Martin. Players control a customizable player character who is on a quest to repair the Elden Ring and become the new Elden Lord.")
                                    .setImageUrl("https://cdn.ozone.bg/media/catalog/product/cache/1/image/800x996/a4e40ebdc3e371adff845072e1c73f37/e/l/a1f7cfe3c7450a1bd7cb16c6896b594e/elden-ring-ps5-30.jpg"),

                            gameGhostOfTsushima
                                    .setCategory(ProductCategory.GAME)
                                    .setPlatform(PlatformName.PS4)
                                    .setName("Ghost of Tsushima")
                                    .setPrice(BigDecimal.valueOf(39.99))
                                    .setSummary("Set in 1274 on the Tsushima Island, the last samurai, Jin Sakai, must master the way of the Ghost, to defeat the Mongol forces and fight for the freedom and independence of Japan.")
                                    .setDescription("Ghost of Tsushima is a 2020 action-adventure game developed by Sucker Punch Productions and published by Sony Interactive Entertainment. The player controls Jin Sakai, a samurai on a quest to protect Tsushima Island during the first Mongol invasion of Japan.")
                                    .setImageUrl("https://cdn.ozone.bg/media/catalog/product/cache/1/image/a4e40ebdc3e371adff845072e1c73f37/g/o/a0828919ac9ec15a4d5e1c5e9a46c5a9/ghost-of-tsushima---director-s-cut-ps4-30.jpg"),

                            gameFinalFantasyXVI
                                    .setCategory(ProductCategory.GAME)
                                    .setPlatform(PlatformName.PS5)
                                    .setName("Final Fantasy XVI")
                                    .setPrice(BigDecimal.valueOf(59.99))
                                    .setSummary("Set in the twin continents of Valisthea, divided between six nations who hold power through access to magical Crystals and Dominants, who act as hosts for each nation's Eikon.")
                                    .setDescription("Final Fantasy XVI is a 2023 action role-playing game developed and published by Square Enix and is the sixteenth main installment in the Final Fantasy series. The game features segmented open environments and an action-based combat system involving melee and magic-based attacks.")
                                    .setImageUrl("https://cdn.ozone.bg/media/catalog/product/cache/1/image/a4e40ebdc3e371adff845072e1c73f37/f/i/b31715f0325df432381707605d3e9d91/final-fantasy-xvi-ps5-30.jpg"),

                            accessoryXboxHeadset
                                    .setCategory(ProductCategory.ACCESSORY)
                                    .setPlatform(PlatformName.XBOX)
                                    .setName("Series X/S")
                                    .setPrice(BigDecimal.valueOf(89.99))
                                    .setSummary("Game loud and clear with the Xbox Wireless Headset, surround yourself with spatial sound technologies including Windows Sonic, Dolby Atmos, and DTS Headphone:X.")
                                    .setDescription("Experience high quality audio with a low-latency, 100% wireless connection to your Xbox console, without the need for a dongle or a base station. Supports spatial sound technologies including Windows Sonic, Dolby Atmos, and DTS Headphone:X for realism and audio precision that fully surrounds you.")
                                    .setImageUrl("https://m.media-amazon.com/images/I/51ODvrKqxTL._SL1200_.jpg"),

                            accessoryKeyboardPC
                                    .setCategory(ProductCategory.ACCESSORY)
                                    .setPlatform(PlatformName.PC)
                                    .setName("Razer BlackWidow V3")
                                    .setPrice(BigDecimal.valueOf(99.99))
                                    .setSummary("Hear and feel the satisfying feedback in every keystroke you make, with a tactile design that offers optimized actuation for better precision and performance when gaming.")
                                    .setDescription("Mean performance in a leaner form — enter the Razer BlackWidow V3 Tenkeyless. Continuing an iconic legacy, this compact gaming keyboard is armed with our world-renowned Razer™ Mechanical Switches and powered by Razer Chroma™ RGB, for a level of precision and personalization beloved by gamers worldwide.")
                                    .setImageUrl("https://cdn.ozone.bg/media/catalog/product/cache/1/image/a4e40ebdc3e371adff845072e1c73f37/8/1/bc4cf409725f7a98c87293a191df4ae8/mehanichna-klaviatura-razer---blackwidow-v3-tenkeyless--razer-green-31.jpg"),

                            accessoryControllerSwitch
                                    .setCategory(ProductCategory.ACCESSORY)
                                    .setPlatform(PlatformName.SWITCH)
                                    .setName("Pro Controller")
                                    .setPrice(BigDecimal.valueOf(74.99))
                                    .setSummary("The Nintendo Switch Pro Controller is a video game controller developed by Nintendo for use with the Nintendo Switch console. It is an alternative to Joy-Con.")
                                    .setDescription("Take your game sessions up a notch with the Nintendo Switch Pro Controller. Includes motion controls, HD rumble, built-in amiibo functionality, and more.")
                                    .setImageUrl("https://cdn.ozone.bg/media/catalog/product/cache/1/image/a4e40ebdc3e371adff845072e1c73f37/n/i/90a9fae510cd0c315704bb94b87b42b2/nintendo-switch-pro-controller-33.jpg")
                    );

            productRepository.saveAll(products);
        }

        if (userRepository.count() == 0) {
            User admin = new User();
            User test1 = new User();
            User test2 = new User();
            User test3 = new User();

            List<User> users =
                    List.of(
                            admin
                                    .setFirstName("Stoyan")
                                    .setLastName("Stoyanov")
                                    .setUsername("Knifer")
                                    .setPassword(passwordEncoder.encode("666666"))
                                    .setEmail("admin@playtray.com")
                                    .setActive(true)
                                    .setRoles(List.of(
                                            roleRepository.findByName(UserRole.ADMIN),
                                            roleRepository.findByName(UserRole.USER)))
                                    .setCart(new Cart().setCustomer(admin)),

                            test1
                                    .setFirstName("Ivan")
                                    .setLastName("Peshev")
                                    .setUsername("test1")
                                    .setPassword(passwordEncoder.encode("666666"))
                                    .setEmail("test1@playtray.com")
                                    .setActive(false)
                                    .setRoles(List.of(
                                            roleRepository.findByName(UserRole.ADMIN),
                                            roleRepository.findByName(UserRole.USER)))
                                    .setCart(new Cart().setCustomer(test1)),

                            test2
                                    .setFirstName("Dimitar")
                                    .setLastName("Hristov")
                                    .setUsername("test2")
                                    .setPassword(passwordEncoder.encode("666666"))
                                    .setEmail("test2@playtray.com")
                                    .setActive(false)
                                    .setRoles(List.of(
                                            roleRepository.findByName(UserRole.USER)))
                                    .setCart(new Cart().setCustomer(test2)),

                            test3
                                    .setFirstName("Georgi")
                                    .setLastName("Ivanov")
                                    .setUsername("test3")
                                    .setPassword(passwordEncoder.encode("666666"))
                                    .setEmail("test3@playtray.com")
                                    .setActive(false)
                                    .setRoles(List.of(
                                            roleRepository.findByName(UserRole.USER)))
                                    .setCart(new Cart().setCustomer(test3))
                    );

            userRepository.saveAll(users);
        }
    }
}
