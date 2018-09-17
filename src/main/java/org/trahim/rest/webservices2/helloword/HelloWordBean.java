package org.trahim.rest.webservices2.helloword;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HelloWordBean {
    private String message;

    public HelloWordBean(String message) {
        this.message = message;
    }
}
