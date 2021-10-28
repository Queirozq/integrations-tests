package com.MQueiroz.integrations.config;

import com.MQueiroz.integrations.services.EmailService;
import com.MQueiroz.integrations.services.SendGridEmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

    @Bean
    public EmailService emailService(){
        return new SendGridEmailService();
    }
}
