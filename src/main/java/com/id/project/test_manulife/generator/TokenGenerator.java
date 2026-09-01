package com.id.project.test_manulife.generator;

import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class TokenGenerator {

    public String generateBearerToken() {
        return "ory_at_" + UUID.randomUUID().toString().replace("-", "");
    }
}