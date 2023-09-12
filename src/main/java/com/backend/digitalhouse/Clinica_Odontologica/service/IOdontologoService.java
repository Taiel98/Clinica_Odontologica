package com.backend.digitalhouse.Clinica_Odontologica.service;

import com.backend.digitalhouse.Clinica_Odontologica.dto.entrada.modificacion.OdontologoModificacionEntradaDto;
import com.backend.digitalhouse.Clinica_Odontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.digitalhouse.Clinica_Odontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.digitalhouse.Clinica_Odontologica.exceptions.ResourceNotFoundException;
import java.util.List;
public interface IOdontologoService {

    List<OdontologoSalidaDto> listarOdontologos();


    OdontologoSalidaDto registrarOdontologo(OdontologoEntradaDto odontologo);

    OdontologoSalidaDto buscarOdontologoPorId(Long id);

    void eliminarOdontologo(Long id) throws ResourceNotFoundException;

    OdontologoSalidaDto actualizarOdontologo(OdontologoModificacionEntradaDto odontologoModificacionEntradaDto) throws ResourceNotFoundException;

}
