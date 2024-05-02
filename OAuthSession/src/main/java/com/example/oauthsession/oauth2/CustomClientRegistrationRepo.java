package com.example.oauthsession.oauth2;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;

@Configuration
@RequiredArgsConstructor
public class CustomClientRegistrationRepo {

    private final SocialClientRegistration socialClientRegistration;

    public ClientRegistrationRepository clientRegistrationRepository() {
        // Inmemory, JDBC연동 DB저장 두가지 방법이 있으나
        // 2가지 경우의 데이터는 별로 없으니 Inmemory방식 이용.
        return new InMemoryClientRegistrationRepository(socialClientRegistration.naverClientRegistration(),
                socialClientRegistration.googleClientRegistration());
    }
}