package com.boster.proyectoAPI.controller;

import com.boster.proyectoAPI.excepciones.InvalidDataException;
import com.boster.proyectoAPI.excepciones.NotFoundExceptionPersonalizada;
import com.boster.proyectoAPI.excepciones.RequestException;
import com.boster.proyectoAPI.models.ModelError;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value = RequestException.class)
    public ResponseEntity<ModelError> exceptionDeSolicitud(RequestException e){
        ModelError modelError = new ModelError(e.getCodigo(), e.getMessage());
        return new ResponseEntity<>(modelError, e.getHttpStatus());
    }

    @ExceptionHandler(value = NotFoundExceptionPersonalizada.class)
    public ResponseEntity<ModelError> excepcionElementoNoEncontrdao(NotFoundExceptionPersonalizada e){
        ModelError modelError = new ModelError(e.getCodigo(),e.getMessage());
        return new ResponseEntity<>(modelError, e.getHttpStatus());
    }

    @ExceptionHandler(value = InvalidDataException.class)
    public ResponseEntity<ModelError> validacionDeDatos(InvalidDataException e){
        List<String> errores = e.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        ModelError modelError = new ModelError(e.getCodigo(),e.getMessage(), errores);
        return new ResponseEntity<>(modelError, e.getHttpStatus());
    }
}
