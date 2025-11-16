package com.in28minutes.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.accept.ApiVersionParser;

public class CustomApiVersionParser implements ApiVersionParser {

    @Override
    public Comparable<?> parseVersion(String version) {
        // Handle "v1", "v2" etc.
        if (version.startsWith("v") || version.startsWith("V")) {
            version = version.substring(1);
        }

        // Append ".0" for major versions
        if (!version.contains(".")) {
            version += ".0";
        }

        return version;
    }
}
