package vananh.example.master.multitenant;

import vananh.example.common.multitenant.TenantContext;
import vananh.example.common.multitenant.constants.MultiTenantConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RequestInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    TenantContext tenantContext;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        String tenantID = request.getHeader("X-TenantID");
        if (tenantID == null) {
            tenantContext.setCurrentTenant(MultiTenantConstants.DEFAULT_TENANT_ID);
        }
        tenantContext.setCurrentTenant(tenantID);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }
}
