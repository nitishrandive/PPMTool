package com.ndroid.ppmtool.security;

public class SecurityConstants {
    public static final String SIGN_UP_URLS = "/api/user/**";
    public static final String H2_URL = "/h2-console/**";
    public static final String SECRET = "SecretKeyToJWT";
    public static final String HEADER_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final long EXPIRATION_TIME = 30000;


}
