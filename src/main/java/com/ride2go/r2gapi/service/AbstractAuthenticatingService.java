package com.ride2go.r2gapi.service;

import com.ride2go.r2gapi.IService;
import com.ride2go.r2gapi.api.dto.ThingDto;
import com.ride2go.r2gapi.legacy.elastic.ElasticTripRepository;
import com.ride2go.r2gapi.security.SecurityHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.UUID;
//TODO: reconsider if a delegate pattern is possible here.
public abstract class AbstractAuthenticatingService<T extends ThingDto> implements IService<T> {
    final SecurityHelper securityHelper;
    final ElasticTripRepository elasticTripRepository;

    protected AbstractAuthenticatingService(SecurityHelper securityHelper, ElasticTripRepository elasticTripRepository) {
        this.securityHelper = securityHelper;
        this.elasticTripRepository = elasticTripRepository;
    }

    protected boolean canUserCreate(T data){
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return data!=null && securityHelper.isUserAdmin(authentication) || securityHelper.isRide2GoUser(authentication);
    }

    protected boolean canUserUpdate(T data){
        if (data==null){
            return false;
        }
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(securityHelper.isUserAdmin(authentication) ||
                securityHelper.isRide2GoUser(authentication) && isUserCreatorOf(authentication,data))
        {
            return true;
        }
        return false;
    }

    protected boolean isUserCreatorOf(Authentication authentication, T data){
        UUID createdBy = elasticTripRepository.findById(data.getId())
                .map(d ->d.getCreatedBy())
                .orElse(UUID.randomUUID());

        return createdBy.equals(securityHelper.getUserId(authentication));
    }


}
