package com.jcaa.usersmanagement.infrastructure.adapter.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MailConfig {

    @Value("${smtp.host}")           private String host;
    @Value("${smtp.port}")           private int    port;
    @Value("${smtp.username}")       private String username;
    @Value("${smtp.password}")       private String password;
    @Value("${smtp.from.address}")   private String fromAddress;
    @Value("${smtp.from.name}")      private String fromName;

    @Bean
    public SmtpConfig smtpConfig() {
        return new SmtpConfig(host, port, username, password, fromAddress, fromName);
    }
}