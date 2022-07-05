package com.dp.spring.template.common;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * Create and keep the UserContext information in InheritableThreadLocal, so that it can be accessed in the same thread, and its child threads.
 */
@Component
public class UserContextHolder {
    
    private static final ThreadLocal<UserContext> contextHolder = new InheritableThreadLocal<>();

    public static UserContext getContext() {
        UserContext ctx = contextHolder.get();

        if (ctx == null) {
            ctx = createEmptyContext();
            contextHolder.set(ctx);
        }

        return ctx;
    }

    public static void setContext(UserContext context) {
        Assert.notNull(context, "Only non-null UserContext instances are permitted");
        contextHolder.set(context);
    }

    public static void clearContext() {
        contextHolder.remove();
    }

    public static UserContext createEmptyContext() {
        return new UserContext();
    }
}
