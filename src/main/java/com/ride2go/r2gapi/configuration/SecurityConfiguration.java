package com.ride2go.r2gapi.configuration;

import com.ride2go.r2gapi.security.oauth.resourceserver.KeycloakRealmRoleConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {



    JwtAuthenticationConverter createJwtAuthenticationConverter() {
        final JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRealmRoleConverter());
        return jwtAuthenticationConverter;
    }

    @Value("${RIDE2GO_USER_ROLE_NAME:ride2go_user}")
    private String ride2GoUserRole;

    @Value("${RIDE2GO_ADMIN_ROLE_NAME:ride2go_admin}")
    private String ride2GoAdminRole;

    @Value("${RIDE2GO_USER_ROLES_CLAIM:r2g_roles}")
    private String ride2GoUserRolesClaim;

    @Value("${RIDE2GO_USER_ID_CLAIM:user_id}")
    private String ride2GoUserIdClaim;

    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                //endpoints with explicit auth  config
                //user
                .antMatchers("/user/**")
                .hasAnyRole(ride2GoUserRole, ride2GoAdminRole)
                //endpoints/contact
                .antMatchers("/endpoint/**")
                .hasAnyRole(ride2GoUserRole, ride2GoAdminRole)
                //trips
                .antMatchers(HttpMethod.GET, "/trip/{id:[\\\\w+]}").hasAnyRole(ride2GoUserRole, ride2GoAdminRole)
                .antMatchers(HttpMethod.POST, "/trip/search").anonymous()
                //Offers
                .antMatchers(HttpMethod.POST, "/offer/").hasAnyRole(ride2GoUserRole, ride2GoAdminRole)
                .antMatchers(HttpMethod.PUT, "/offer/{id:[\\\\w+]}").hasAnyRole(ride2GoUserRole, ride2GoAdminRole)
                .antMatchers(HttpMethod.GET, "/offer/{id:[\\\\w+]}").anonymous()
                .antMatchers(HttpMethod.POST, "/offer/search").anonymous()


                //anything else
                .anyRequest().permitAll().and()
                //oauth config
                .oauth2ResourceServer()
                .jwt().jwtAuthenticationConverter(createJwtAuthenticationConverter());
    }



}
