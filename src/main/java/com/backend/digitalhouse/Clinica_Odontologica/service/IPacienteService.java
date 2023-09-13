package com.backend.digitalhouse.Clinica_Odontologica.service;

import com.backend.digitalhouse.Clinica_Odontologica.dto.entrada.modificacion.PacienteModificacionEntradaDto;
import com.backend.digitalhouse.Clinica_Odontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.digitalhouse.Clinica_Odontologica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.digitalhouse.Clinica_Odontologica.exceptions.ResourceNotFoundException;
import java.util.List;

public interface IPacienteService {
    List<PacienteSalidaDto> listarPacinetes();

    PacienteSalidaDto registrarPaciente(PacienteEntradaDto paciente);

    PacienteSalidaDto buscarPacientePorId(Long id);

    void eliminarPaciente(Long id) throws ResourceNotFoundException;

    PacienteSalidaDto modificarPaciente(PacienteModificacionEntradaDto pacienteModificado) throws ResourceNotFoundException;
}
