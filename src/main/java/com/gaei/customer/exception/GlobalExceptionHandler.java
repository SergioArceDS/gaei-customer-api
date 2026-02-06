package com.gaei.customer.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex) {
        String error = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(FieldError::getField)
                .collect(Collectors.joining(", "));

        return ResponseEntity.badRequest().body(
                Map.of("Error", "Los campos " + error + " son obligatorios.")
        );
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handleBusinessException(BusinessException ex) {
        log.warn("[{}] Error de negocio: {}", ex.getIdTx(), ex.getMessage());

        return ResponseEntity.badRequest().body(
                Map.of("idTx", ex.getIdTx(), "error", ex.getMessage())
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGenericException(Exception ex) {

        log.error("Error técnico no controlado", ex);

        return ResponseEntity.internalServerError().body(
                Map.of(
                        "error", "Error técnico inesperado"
                )
        );
    }
}
