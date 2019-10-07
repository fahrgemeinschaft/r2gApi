package com.ride2go.r2gapi.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Configuration
@ConfigurationProperties("system")
@Validated
@Data
public class SystemConfiguration {

    @NotNull(message = "system.appName must be set")
    private String appName;
}
