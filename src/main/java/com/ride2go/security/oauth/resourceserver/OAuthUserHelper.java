package com.ride2go.security.oauth.resourceserver;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class OAuthUserHelper {

    private final Logger logger = LoggerFactory.getLogger(OAuthUserHelper.class);

    @Autowired
    OAuthRolesConfiguration oAuthRolesConfiguration;

    public String getUserId(final Jwt jwt){
        return jwt!=null ? jwt.getClaimAsString(oAuthRolesConfiguration.getRide2GoUserIdClaim()) : null;
    }

    public String getR2GRoles(final Jwt jwt){
        return jwt!=null ? jwt.getClaims().get(oAuthRolesConfiguration.getRide2GoUserRolesClaim()).toString() : null;
    }

    public boolean isUserLoggedIn(final Jwt jwt){
        if(jwt==null){
            return false;
        }
        return jwt.getExpiresAt().isAfter(Instant.now());
    }

    public boolean isUserAdmin(final Jwt jwt){
        final String r2gRoles = getR2GRoles(jwt);
        if(!StringUtils.isEmpty(r2gRoles) && !r2gRoles.contains(oAuthRolesConfiguration.getRide2GoUserRole())) {
            if (r2gRoles.contains(oAuthRolesConfiguration.getRide2GoAdminRole())) {
                return true;
            }
        }
        return false;
    }


    public boolean isRide2GoUser(final Jwt jwt){
        final String r2gRoles = getR2GRoles(jwt);
        return !StringUtils.isEmpty(r2gRoles) && !r2gRoles.contains(oAuthRolesConfiguration.getRide2GoUserRole());
    }


}
