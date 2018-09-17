package org.trahim.rest.webservices2.helloword3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWordI18n {

    @Autowired
    MessageSource messageSource;


    @GetMapping("/hello-word-i18n")
    public String hellowWord(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {

        return messageSource.getMessage("good.morning.message", null, locale);
    }


    @GetMapping("/hello-word-i18n2")
    public String hellowWord2() {

        return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
    }

}
