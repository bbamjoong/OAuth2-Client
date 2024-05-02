package com.example.oauthsession.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

@RequiredArgsConstructor
public class CustomOAuth2User implements OAuth2User {

    private final OAuth2Response oAuth2Response;
    private final String role;

    @Override
    public Map<String, Object> getAttributes() { // 모든 데이터. 구글, 네이버의 양식이 다르므로 일단 null 처리
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { // role에 해당
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add((GrantedAuthority) () -> role);

        return collection;
    }

    @Override
    public String getName() {
        return oAuth2Response.getName();
    }
}
