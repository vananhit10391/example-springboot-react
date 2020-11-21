package vananh.example.common.multitenant;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class TenantContext implements Serializable {
    private static ThreadLocal<String> currentTenant = new ThreadLocal<>();

    public static String getCurrentTenant() {
        return currentTenant.get();
    }

    public static void setCurrentTenant(String tenant) {
        currentTenant.set(tenant);
    }

    public static void clear() {
        currentTenant.set(null);
    }
}
