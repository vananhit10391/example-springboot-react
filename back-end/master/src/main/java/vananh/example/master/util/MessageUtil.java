package vananh.example.master.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;

@Component
public class MessageUtil {
    @Autowired
    MessageSource messageSource;

    public String getMessage(String code, String defaulMessage, HttpServletRequest request) {
        return messageSource.getMessage(code, null, defaulMessage, new RequestContext(request).getLocale());
    }
}
