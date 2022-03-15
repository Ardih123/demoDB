package com.example.demoAula.controller;

import com.example.demoAula.dto.SimpleResponse;
import com.example.demoAula.dto.SimpleResponseEmpresas;
import com.example.demoAula.model.Empresa;
import com.example.demoAula.service.empresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class empresaController {

    private final empresaService empresa_service;

    @Autowired
    public empresaController(empresaService empresa_service) {
        this.empresa_service = empresa_service;
    }

    @GetMapping("/getEmpresas")
    public List<Empresa> getEmpresas(){
        return empresa_service.getEmpresas();
    }

    @PostMapping(path = "/addEmpresa")
    public ResponseEntity<SimpleResponse> addEmpresa(@RequestBody Empresa p){
        SimpleResponseEmpresas sr = new SimpleResponseEmpresas();

        if (p.getAddress() == null || p.getAddress().isBlank()){
            sr.setMessage("Morada Invalida");
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(sr);
        }

        if (p.getName() == null || p.getName().isBlank()){
            sr.setMessage("Nome Invalido");
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(sr);
        }

        if (empresa_service.addEmpresa(p)){
            sr.setAsSuccess("Empresa Inserida Com Sucesso");
            sr.setEmpresas(empresa_service.getEmpresas());

        }else{
            sr.setAsError("Erro ao inserir a Empresa");
        }


        return ResponseEntity
                .status(HttpStatus.OK)
                .body(sr);
    }


    @DeleteMapping("/removeEmpresa/{id}")
    public SimpleResponse removeEmpresa2(@PathVariable(name = "id") String id){
        SimpleResponse sr = new SimpleResponse();

        if (empresa_service.removeEmpresa2(id)){
            sr.setAsSuccess("Empresa Removida Com Sucesso");
        }
        else{
            sr.setAsError("Erro a Remover a Pessoa");
        }

        return sr;
    }

    @DeleteMapping("/removeEmpresa")
    public SimpleResponse removeEmpresa(@RequestBody Empresa p){
        SimpleResponseEmpresas sr = new SimpleResponseEmpresas();
        sr.setAsSuccess("Sucesso");

        if (empresa_service.removeEmpresa(p)){
            sr.setAsSuccess("Empresa Removida Com Sucesso");
        }
        else{
            sr.setAsError("Erro a Remover a Empresa");
        }

        return sr;
    }

    @PutMapping("/updateEmpresa")
    public SimpleResponse updateEmpresa(@RequestBody Empresa p){
        SimpleResponse sr = new SimpleResponse();

        if (p.getId()==null){
            sr.setAsError("Id invalido");
            return sr;
        }

        if (p.getName() == null || p.getName().isBlank()){
            sr.setAsError("Nome Invalido");
            return sr;
        }
        
        if (p.getAddress() == null || p.getAddress().isBlank()){
            sr.setAsError("Morada Invalida");
            return sr;
        }

        boolean suc = empresa_service.updateEmpresa(p);

        if (suc){
            sr.setAsSuccess("Empresa atualizada com sucesso");
        }
        else{
            sr.setAsError("Erro na atualização da empresa "+ p.getId());
        }
        return sr;

    }
}
