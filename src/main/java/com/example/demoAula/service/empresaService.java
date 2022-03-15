package com.example.demoAula.service;

import static java.lang.Float.NaN;
import static java.lang.Long.parseLong;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demoAula.model.Empresa;
import com.example.demoAula.repository.empresaRepository;

@Service
public class empresaService {
	private final empresaRepository empresaRepo;

	@Autowired
	public empresaService(empresaRepository empresaRepo) {
		this.empresaRepo = empresaRepo;
	}

    public boolean addEmpresa(Empresa empresa){
        if (empresa.getId() == null){
            empresaRepo.save(empresa);
            return true;
        }
        return false;
    }

    public boolean removeEmpresa(Empresa empresa){
        if (empresa.getId() == null || empresaRepo.findById(empresa.getId()).isEmpty()){
            return false;
        }

        Empresa p = empresaRepo.findById(empresa.getId()).get();
        empresaRepo.delete(p);

        return true;
    }

    public boolean removeEmpresa2(String id){
        try {
            Long id_long = parseLong(id);

            if (id == null || id_long==NaN || empresaRepo.findById(id_long).isEmpty()){
                return false;
            }

            Empresa p = empresaRepo.findById(id_long).get();
            empresaRepo.delete(p);

            return true;
        }catch (Exception e){
            return false;
        }

    }

    public boolean updateEmpresa(Empresa empresa){
        if (empresa.getId() == null || empresaRepo.findById(empresa.getId()).isEmpty()){
            return false;
        }

        Empresa p = empresaRepo.findById(empresa.getId()).get();

        if (p.getName() != null || !p.getName().isBlank()){
            p.setName(empresa.getName());
        }

        if (p.getAddress() != null || !p.getAddress().isBlank()){
            p.setAddress(empresa.getAddress());
        }

        empresaRepo.save(p);

        return true;
    }

    public List<Empresa> getEmpresas(){
        List<Empresa> listaEmpresas = new ArrayList<>();

        empresaRepo.findAll().forEach(listaEmpresas::add);

        return listaEmpresas;
    }

    public Optional<Empresa> getEmpresa(Long id)
    {
        return empresaRepo.findById(id);
    }

}
