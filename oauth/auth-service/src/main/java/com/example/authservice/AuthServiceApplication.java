package com.example.authservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Set;

@SpringBootApplication
public class AuthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    RegisteredClientRepository clients() {
        var browser = "browser";
        var client = RegisteredClient
                .withId(browser)
                .clientId(browser)
                .clientName(browser)
                .clientSecret("{bcrypt}$2a$10$us1QCGw8FILSFp7jo5HCMuHoLKvUFV61PVwsEEzQNekS3EiIT9ND2") // 'pw' (without quotes)
                .authorizationGrantTypes(a -> a.addAll(Set.of(AuthorizationGrantType.AUTHORIZATION_CODE, AuthorizationGrantType.REFRESH_TOKEN)))
                .redirectUri("http://127.0.0.1:8080/login/oauth2/code/browser")
                .scope("openid")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
                .build();
        return new InMemoryRegisteredClientRepository(client);
    }

    @Bean
    UserDetailsService users() {
        var user = User
                .withUsername("cora")
                .password("{bcrypt}$2a$10$E8FEFmBZNKMBTZqGw1LRVuTujXeRGCCxtg.ui6DVZSLhsV1GFYm1u")// 'pw' (without quotes)
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

}
