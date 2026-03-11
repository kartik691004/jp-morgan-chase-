package com.jpmc.midascore.component;

import com.jpmc.midascore.foundation.Incentive;
import com.jpmc.midascore.foundation.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class IncentiveService {

    private static final Logger logger = LoggerFactory.getLogger(IncentiveService.class);

    private final RestTemplate restTemplate;
    private final String incentiveApiUrl;

    public IncentiveService(@Value("${general.incentive-api-url}") String incentiveApiUrl) {
        this.restTemplate = new RestTemplate();
        this.incentiveApiUrl = incentiveApiUrl;
    }

    public float getIncentive(Transaction transaction) {
        try {
            Incentive incentive = restTemplate.postForObject(incentiveApiUrl, transaction, Incentive.class);
            if (incentive != null) {
                logger.info("Received incentive: {}", incentive.getAmount());
                return incentive.getAmount();
            }
        } catch (Exception e) {
            logger.warn("Failed to get incentive: {}", e.getMessage());
        }
        return 0f;
    }
}
