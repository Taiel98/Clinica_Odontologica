package com.backend.digitalhouse.Clinica_Odontologica.dao.impl;

import com.backend.digitalhouse.Clinica_Odontologica.dao.IDao;
import com.backend.digitalhouse.Clinica_Odontologica.entity.Paciente;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PacienteDaoH2 implements IDao<Paciente> {
    @Override
    public Paciente registrar(Paciente paciente) {
        return null;
    }

    @Override
    public Paciente buscarPorID(Long t) {
        return null;
    }

    @Override
    public List<Paciente> listarTodos() {
        return null;
    }

    @Override
    public void eliminar(Long t) {

    }

    @Override
    public Paciente modificar(Paciente paciente) {
        return null;
    }
}
