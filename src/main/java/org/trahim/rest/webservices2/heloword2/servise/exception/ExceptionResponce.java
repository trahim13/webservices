package org.trahim.rest.webservices2.heloword2.servise.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;
//теловоз воащаемого ответа, при ошибках
@Getter
@AllArgsConstructor
public class ExceptionResponce {

    private Date timestamp;
    private String message;
    private String exceptionDetails;
}
