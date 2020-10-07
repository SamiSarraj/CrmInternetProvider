package com.crm.security;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Constants {
    public final static Set<String> PERMITTED_PATHS = Collections.unmodifiableSet(
            new HashSet<>(Arrays.asList("/token/generate-token","/login" ,"/user/allUsersTest"
            , "/user/addUserTest","/net-packages/getAllTest")));
    public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 30*60;
    public static final String SIGNING_KEY = "netcrm";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
