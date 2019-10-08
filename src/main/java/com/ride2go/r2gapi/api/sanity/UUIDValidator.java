package com.ride2go.r2gapi.api.sanity;

import java.util.regex.Pattern;

public class UUIDValidator {
    private static final Pattern UUID_PATTERN_5 = Pattern.compile("^[a-f0-9]{8}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{12}$", 2);

    public UUIDValidator() {
    }

    public static boolean isValidUUID(String s) {
        return UUID_PATTERN_5.matcher(s).matches();
    }
}
