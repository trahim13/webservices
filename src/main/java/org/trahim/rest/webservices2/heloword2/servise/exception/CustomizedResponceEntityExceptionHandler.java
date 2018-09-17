package org.trahim.rest.webservices2.heloword2.servise.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.trahim.rest.webservices2.heloword2.servise.UserNotFoundException;

import java.util.Date;
//создаем совет для всех контроллесов для обработки ошибок
@ControllerAdvice
@RestController
public class CustomizedResponceEntityExceptionHandler extends ResponseEntityExceptionHandler {

    //обработчики конкретныхтипов ошибок
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
        ExceptionResponce exceptionResponce = new ExceptionResponce(new Date(), ex.getMessage(), request.getDescription(false));
        //возврашаем ответ и его статус
        return new ResponseEntity<>(exceptionResponce, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //обработчики конкретныхтипов ошибок
    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
        ExceptionResponce exceptionResponce = new ExceptionResponce(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponce, HttpStatus.NOT_FOUND);
    }

//для валидвции передаваемых параметров
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionResponce exceptionResponce = new ExceptionResponce(new Date(), "The parameters not valid",
                ex.getBindingResult().getFieldError().toString());
        return new ResponseEntity<>(exceptionResponce, HttpStatus.BAD_REQUEST);
    }


}
