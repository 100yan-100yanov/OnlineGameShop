package com.playtray.service.schedulers;

import com.playtray.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class CalculatingTotalSales {

    private final UserService userService;

    private final Logger logger = LoggerFactory.getLogger(CalculatingTotalSales.class);

    public CalculatingTotalSales(UserService userService) {
        this.userService = userService;
    }

    @Scheduled(timeUnit = TimeUnit.SECONDS, fixedRate = 6)
    public void calculateTotal() {
        Map<String, BigDecimal> totalSales = userService.getTotalSales();

        logger.info("Congratulations!");
        logger.info(String.format(
                "We have reached %.0f sold products amounting to €%.2f!",
                totalSales.get("Units"),
                totalSales.get("Amount")));
    }
}
