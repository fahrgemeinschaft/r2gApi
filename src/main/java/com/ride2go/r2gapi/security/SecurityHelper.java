package com.ride2go.r2gapi.security;

import com.ride2go.security.oauth.resourceserver.OAuthUserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.stereotype.Component;
import org.springframework.security.oauth2.jwt.Jwt;


@Component
public class SecurityHelper {

    @Autowired
    OAuthUserHelper oAuthUserHelper;

    public String getUserId(Authentication authentication) {
        return oAuthUserHelper.getUserId(asJwt(authentication));
    }

    public boolean isUserAdmin(Authentication authentication){
        return oAuthUserHelper.isUserAdmin(asJwt(authentication));
    }

    public boolean isRide2GoUser(Authentication authentication){
        return oAuthUserHelper.isRide2GoUser(asJwt(authentication));
    }

    Jwt asJwt(Authentication authentication){
        if(authentication instanceof OAuth2AuthenticatedPrincipal) {
            return (Jwt) authentication.getPrincipal();
        }
        throw new IllegalArgumentException("not an OAuth2AuthenticatedPrincipal ");
    }

}
