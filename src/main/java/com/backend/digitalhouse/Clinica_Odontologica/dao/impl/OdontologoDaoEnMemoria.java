package com.backend.digitalhouse.Clinica_Odontologica.dao.impl;

import com.backend.digitalhouse.Clinica_Odontologica.dao.IDao;
import com.backend.digitalhouse.Clinica_Odontologica.entity.Odontologo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;

public class OdontologoDaoEnMemoria implements IDao<Odontologo> {

    private static final Logger LOGGER = LoggerFactory.getLogger(OdontologoDaoEnMemoria.class);
    private List<Odontologo> odontologoRepository;

    public OdontologoDaoEnMemoria(List<Odontologo> odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    @Override
    public Odontologo registrar(Odontologo odontologo) {
        odontologoRepository.add(odontologo);
        LOGGER.info("Odontologo guardado: " + odontologo);
        return odontologo;
    }

    @Override
    public Odontologo buscarPorID(Long t) {
        return null;
    }

    @Override
    public List<Odontologo> listarTodos() {
        LOGGER.info("Listado de todos los odontologos: \n" + odontologoRepository);
        return odontologoRepository;
    }

    @Override
    public void eliminar(Long t) {

    }

    @Override
    public Odontologo modificar(Odontologo odontologo) {
        return null;
    }
}
