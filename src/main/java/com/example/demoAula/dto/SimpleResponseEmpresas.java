package com.example.demoAula.dto;

import java.util.ArrayList;
import java.util.List;

import com.example.demoAula.model.Empresa;

public class SimpleResponseEmpresas extends SimpleResponse{
    List<Empresa> empresa;

    public SimpleResponseEmpresas() {
        this.empresa = new ArrayList<>();
    }

    public List<Empresa> getEmpresas() {
        return empresa;
    }

    public void setEmpresas(List<Empresa> empresa) {
        this.empresa = empresa;
    }
}
