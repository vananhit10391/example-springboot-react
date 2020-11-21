package vananh.example.master.multitenant;

import vananh.example.common.multitenant.TenantContext;
import vananh.example.common.multitenant.constants.MultiTenantConstants;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TenantSchemaResolver implements CurrentTenantIdentifierResolver {
    @Autowired
    TenantContext tenantContext;

    @Override
    public String resolveCurrentTenantIdentifier() {
        String currentTenant =  tenantContext.getCurrentTenant();
        if (currentTenant != null){
            return currentTenant;
        } else {
            return MultiTenantConstants.DEFAULT_TENANT_ID;
        }
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}
