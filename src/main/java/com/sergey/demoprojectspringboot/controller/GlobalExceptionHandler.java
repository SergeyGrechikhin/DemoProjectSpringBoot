package com.sergey.demoprojectspringboot.controller;

import com.sergey.demoprojectspringboot.exception.AlreadyExistException;
import com.sergey.demoprojectspringboot.exception.NotFoundException;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handlerNullPointerException(NullPointerException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handlerNotFoundException(NotFoundException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<String> handlerAlreadyExistException(AlreadyExistException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handlerConstraintViolationException(ConstraintViolationException e){
        StringBuilder responseMessage = new StringBuilder();

        e.getConstraintViolations().forEach(
                constraintViolation -> {
                    String currentField = constraintViolation.getPropertyPath().toString();
                    String currentMessage = constraintViolation.getMessage();
                    responseMessage.append("Field : " + currentField + " : " + currentMessage);
                    responseMessage.append("\n");
                }
        );
        return new ResponseEntity<>(responseMessage.toString(), HttpStatus.BAD_REQUEST);
    }

   /* @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e){

        StringBuilder responseMessage = new StringBuilder();

        e.getBindingResult().getAllErrors().forEach(
                objectError -> {
                    String fieldName = ((FieldError) objectError).getField();
                    String currentMessage = objectError.getDefaultMessage();
                    responseMessage.append(fieldName + " : " + currentMessage);
                    responseMessage.append("\n");

                }
        );

        return new ResponseEntity<>(responseMessage.toString(), HttpStatus.BAD_REQUEST);

    } */


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", "Validation failed");
        Map<String, String> errors = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));
        body.put("errors", errors);
        return ResponseEntity.badRequest().body(body);
    }

  /*  // Неверный формат JSON/поля (в т.ч. LocalDate)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> handleNotReadable(HttpMessageNotReadableException ex) {
        String msg = "Invalid request body";
        Throwable cause = ex.getMostSpecificCause();

        if (cause instanceof java.time.format.DateTimeParseException
                || (cause instanceof com.fasterxml.jackson.databind.exc.InvalidFormatException ife
                && ife.getTargetType() == java.time.LocalDate.class)) {
            msg = "Invalid date format. Use yyyy-MM-dd (e.g. 2025-10-08)";
        }
        return ResponseEntity.badRequest().body(Map.of("message", msg));
    } */

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> handleNotReadable(HttpMessageNotReadableException ex) {
        String msg = "Invalid request body";
        Throwable cause = ex.getMostSpecificCause();

        if (cause instanceof com.fasterxml.jackson.databind.exc.InvalidFormatException ife) {
            Class<?> target = ife.getTargetType();

            // 1) Подсказка для Enum (например, Task.Status)
            if (target.isEnum()) {
                String field = ife.getPath().isEmpty() ? "value"
                        : ife.getPath().get(0).getFieldName();
                String allowed = java.util.Arrays.stream(target.getEnumConstants())
                        .map(Object::toString)
                        .reduce((a, b) -> a + ", " + b).orElse("");
                msg = "Invalid value for '" + field + "'. Use one of: " + allowed;
            }

            // 2) Подсказка для LocalDate
            if (target == java.time.LocalDate.class) {
                msg = "Invalid date format. Use yyyy-MM-dd (e.g. 2025-10-08)";
            }
        }

        return ResponseEntity.badRequest().body(java.util.Map.of("message", msg));
    }
}






