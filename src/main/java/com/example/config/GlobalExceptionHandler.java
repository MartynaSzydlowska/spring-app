package com.example.config;

import com.example.api.ErrorResponseDto;
import com.example.bookings.core.NotAvailableSlotException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> globalErrors = ex.getBindingResult().getGlobalErrors()
                .stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());
        List<String> fieldErrors = ex.getBindingResult().getFieldErrors()
                .stream().map(fieldError -> "'" + fieldError.getField() + "' " + fieldError.getDefaultMessage()).collect(Collectors.toList());
        ErrorResponseDto error = new ErrorResponseDto();
        globalErrors.addAll(fieldErrors);
        String message = globalErrors.stream().map(e -> e.endsWith(".") ? e:e + ".").collect(Collectors.joining(" "));
        error.setMessage(message);
        return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotAvailableSlotException.class)
    public ResponseEntity<ErrorResponseDto> handleNotAvailableSlotError(NotAvailableSlotException ex) {
        String message = ex.getMessage();
        ErrorResponseDto error = new ErrorResponseDto();
        error.setMessage(message);
        return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }

}