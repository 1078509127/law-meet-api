package com.example.meet.oauth.serve.config.jwt;

import com.example.meet.oauth.serve.details.MyUserDetails;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * jwt增强
 * */

public class JwtTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        MyUserDetails principal = (MyUserDetails) oAuth2Authentication.getPrincipal();
        Map<String, Object> info = new HashMap<>();
        info.put("userId", principal.getUserId());
        info.put("userName", principal.getUsername());
        info.put("nickname", principal.getNickname());
        info.put("authorities", principal.getAuthorities());
        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(info);
        return oAuth2AccessToken;
    }

}
