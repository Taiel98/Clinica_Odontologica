package com.backend.digitalhouse.Clinica_Odontologica.service.impl;

import com.backend.digitalhouse.Clinica_Odontologica.dto.entrada.modificacion.OdontologoModificacionEntradaDto;
import com.backend.digitalhouse.Clinica_Odontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.digitalhouse.Clinica_Odontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.digitalhouse.Clinica_Odontologica.entity.Odontologo;
import com.backend.digitalhouse.Clinica_Odontologica.exceptions.ResourceNotFoundException;
import com.backend.digitalhouse.Clinica_Odontologica.repository.OdontologoRepository;
import com.backend.digitalhouse.Clinica_Odontologica.service.IOdontologoService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService implements IOdontologoService {

    private final OdontologoRepository odontologoRepository;
    private final ModelMapper modelMapper;

    public OdontologoService(OdontologoRepository odontologoRepository, ModelMapper modelMapper) {
        this.odontologoRepository = odontologoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<OdontologoSalidaDto> listarOdontologos() {
        return null;
    }

    @Override
    public OdontologoSalidaDto registrarOdontologo(OdontologoEntradaDto odontologo) {
        return null;
    }

    @Override
    public OdontologoSalidaDto buscarOdontologoPorId(Long id) {
        return null;
    }

    @Override
    public void eliminarOdontologo(Long id) throws ResourceNotFoundException {

    }

    @Override
    public OdontologoSalidaDto actualizarOdontologo(OdontologoModificacionEntradaDto odontologoModificacionEntradaDto) throws ResourceNotFoundException {
        return null;
    }
}
