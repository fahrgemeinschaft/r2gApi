package com.ride2go.security.oauth.resourceserver;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class OAuthRolesConfiguration {

    @Value("${RIDE2GO_USER_ROLE_NAME:ride2go_user}")
    private String ride2GoUserRole;

    @Value("${RIDE2GO_ADMIN_ROLE_NAME:ride2go_admin}")
    private String ride2GoAdminRole;

    @Value("${RIDE2GO_USER_ROLES_CLAIM:r2g_roles}")
    private String ride2GoUserRolesClaim;

    @Value("${RIDE2GO_USER_ID_CLAIM:user_id}")
    private String ride2GoUserIdClaim;


}
