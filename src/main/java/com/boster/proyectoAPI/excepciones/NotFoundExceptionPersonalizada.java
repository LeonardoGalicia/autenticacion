package com.boster.proyectoAPI.excepciones;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class NotFoundExceptionPersonalizada extends RuntimeException{

    private String codigo;
    private HttpStatus httpStatus;
    public NotFoundExceptionPersonalizada(String mensaje, String codigo, HttpStatus status) {
        super(mensaje);
        this.codigo = codigo;
        this.httpStatus = status;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
