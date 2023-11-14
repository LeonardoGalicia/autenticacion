package com.boster.proyectoAPI.models;

import java.util.List;

public class ModelError {

    private String codigo;
    private String mensaje;

    private List<String> errors;

    public ModelError(String codigo, String mensaje) {
        this.codigo = codigo;
        this.mensaje = mensaje;
    }

    public ModelError(String codigo, String mensaje, List<String> errors) {
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.errors = errors;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
