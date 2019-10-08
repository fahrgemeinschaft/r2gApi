package com.ride2go.r2gapi.api.sanity;

public class ApiDataSanitizer {

    public boolean sanitizeId(final String id) {
        return id != null && !id.isEmpty() ? UUIDValidator.isValidUUID(id) : false;
    }

}