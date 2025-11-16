package com.in28minutes.rest.webservices.restfulwebservices.versioning;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ApiVersionConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // Select ONE of the following approaches (they cannot be combined)
    // 1. Path Segment Versioning
    // 2. Query Parameter Versioning
    // 3. Request Header Versioning
    // 4. Media Type Versioning (Content Negotiation)

    // 1. Path Segment Versioning
    @Override
    public void configureApiVersioning(ApiVersionConfigurer configurer) {
        configurer.usePathSegment(0)
                .addSupportedVersions("1.0","2.0");
    }

    // 2. Query Parameter Based
//    @Override
//    public void configureApiVersioning(ApiVersionConfigurer configurer) {
//        configurer.useQueryParam("version")
//                .addSupportedVersions("1");
//    }

    // 3. Request Header Versioning
//    @Override
//    public void configureApiVersioning(ApiVersionConfigurer configurer) {
//        configurer.useRequestHeader("X-API-VERSION")
//                .addSupportedVersions("1.0","2.0");
//    }

    // 4. Media Type Versioning (Content Negotiation)
//    @Override
//    public void configureApiVersioning(ApiVersionConfigurer configurer) {
//        configurer.useMediaTypeParameter(MediaType.APPLICATION_JSON, "version")
//                .addSupportedVersions("1.0","2.0")
//                .setDefaultVersion("1.0")
//                .setVersionParser(new CustomApiVersionParser());
//    }
}