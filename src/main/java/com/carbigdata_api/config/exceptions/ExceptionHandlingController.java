package com.carbigdata_api.config.exceptions;

import com.fasterxml.jackson.databind.JsonMappingException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlingController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public List<MessageException> handleBeanValidation(BindException ex) {
        log.error("Erro de validação: {}", ex.getMessage());
        return ex.getBindingResult().getFieldErrors()
            .stream()
            .map(error -> {
                String fieldName = error.getField();
                String errorMessage = error.getDefaultMessage();
                if (StringUtils.containsIgnoreCase(errorMessage, "campo")) {
                    return new MessageException(errorMessage);
                }
                return new MessageException("O campo " + fieldName + " " + errorMessage, fieldName);
            }).toList();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public List<MessageException> notFoundError(NotFoundException ex) {
        return List.of(new MessageException(ex.getMessage()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public List<MessageException> illegalArgumentException(IllegalArgumentException ex) {
        return List.of(new MessageException(ex.getMessage()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public List<MessageException> validationException(ValidationException ex) {
        return List.of(new MessageException(ex.getMessage()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(JsonMappingException.class)
    public List<MessageException> handleJsonMapping(JsonMappingException ex) {
        return List.of(new MessageException("Erro no formato do campo de entrada: " + ex.getPathReference()));
    }
}