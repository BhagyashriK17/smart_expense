package com.expense_service.smart_expense.service;


import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;
import java.util.logging.Logger;

@Service
public class CurrencyService {
    //private static final Logger log = (Logger) LoggerFactory.getLogger(CurrencyService.class);
    private final WebClient webClient = WebClient.create();

    @Cacheable(value = "exchangeRates", key = "'USD_RATE'")
    public Double getUsdRate() {

       // log.info("Fetching exchange rate from external API");

        try {
            Map response = webClient.get()
                    .uri("https://api.fxratesapi.com/latest?base=INR")
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();

            Map rates = (Map) response.get("rates");

            return Double.valueOf(rates.get("USD").toString());

        } catch (NumberFormatException e) {

            return 0.012; // fallback value
        }
    }

    @CacheEvict(value = "exchangeRates", key = "'USD_RATE'")
    public void clearCache() {
    }
}
