package com.lyh.security.app.jwt;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shenhj
 * @version 1.0
 * Description:
 * @date 2019/12/26 23:24
 */
public class LyhJwtTokenEnhancer implements TokenEnhancer {

  @Override
  public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

    Map<String, Object> info = new HashMap<>();
    info.put("company", "lyh");
    ((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(info);

    return accessToken;
  }
}
