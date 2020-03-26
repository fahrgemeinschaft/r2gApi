package com.ride2go.r2gapi.configuration;

import com.ride2go.security.oauth.resourceserver.KeycloakRealmRoleConverter;
import com.ride2go.security.oauth.resourceserver.OAuthRolesConfiguration;
import com.ride2go.security.oauth.resourceserver.OAuthUserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

@Configuration
@Import(com.ride2go.security.oauth.resourceserver.OAuthRolesConfiguration.class)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    OAuthRolesConfiguration oAuthRolesConfiguration;

    JwtAuthenticationConverter createJwtAuthenticationConverter() {
        final JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRealmRoleConverter());
        return jwtAuthenticationConverter;
    }

    @Bean
    OAuthUserHelper createOAuthUserHelper(){
        return new OAuthUserHelper();
    }

    protected void configure(HttpSecurity http) throws Exception {
        final String ride2GoUserRole=oAuthRolesConfiguration.getRide2GoUserRole();
        final String ride2GoAdminRole=oAuthRolesConfiguration.getRide2GoAdminRole();

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
