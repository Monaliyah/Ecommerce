package com.June.User.controller.Utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * June
 */
@Data
@Component
@ConfigurationProperties(prefix = "pattern")
public class PatternProperties {

    private String dateformat;
}
