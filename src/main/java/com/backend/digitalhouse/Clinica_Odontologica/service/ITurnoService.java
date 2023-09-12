package com.backend.digitalhouse.Clinica_Odontologica.service;

import com.backend.digitalhouse.Clinica_Odontologica.dto.entrada.modificacion.TurnoModificacionEntradaDto;
import com.backend.digitalhouse.Clinica_Odontologica.dto.entrada.turno.TurnoEntradaDto;
import com.backend.digitalhouse.Clinica_Odontologica.dto.salida.turno.TurnoSalidaDto;
import com.backend.digitalhouse.Clinica_Odontologica.exceptions.BadRequestException;
import com.backend.digitalhouse.Clinica_Odontologica.exceptions.ResourceNotFoundException;
import  java.util.List;
public interface ITurnoService {

    TurnoSalidaDto registrarTurno(TurnoEntradaDto turnoEntradaDto) throws BadRequestException;

    List<TurnoSalidaDto> listarTurnos();

    TurnoSalidaDto buscarTurnoPorId(Long id);

    void eliminarTurno(Long id) throws ResourceNotFoundException;

    TurnoSalidaDto modificarTurno(TurnoModificacionEntradaDto turnoModificacionEntradaDto) throws ResourceNotFoundException;

}
