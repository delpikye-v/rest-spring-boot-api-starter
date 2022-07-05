package com.dp.spring.template.utils;

import java.io.Serializable;

/**
 * Constants used in the projects.
 */
public abstract class Constants {

    public static abstract class Common {
        public static final String YES = "Y";
        public static final String NO = "N";
        public static final String TRUE = "true";
        public static final String FALSE = "false";
    }

    public static abstract class Character {
        public static final String COLON = ":";
        public static final String COMMA = ",";
        public static final String SEMI_COLON = ";";
    }

    public class Encoding {
        public static final String UTF_8 = "UTF-8";
    }

    public static abstract class BitBucketHeaders {
        public static final String X_REQUEST_UUID = "X-Request-UUID";
        public static final String X_EVENT_KEY = "X-Event-Key";
        public static final String USER_AGENT = "User-Agent";
        public static final String X_ATTEMPT_NUMBER = "X-Attempt-Number";
        public static final String ATTEMPT_NUMBER_0 = "0";
        public static final String X_HOOK_UUID = "X-Hook-UUID";
        public static final String CONTENT_TYPE = "Content-Type";
    }

    public static abstract class RoleName {
        public static final String ADMIN = "admin";
        public static final String MODERATOR = "mod";
        public static final String USER = "user";
    }

    public enum RoleSecurity implements Serializable {
        ROLE_USER,
        ROLE_MODERATOR,
        ROLE_ADMIN
    }
}
