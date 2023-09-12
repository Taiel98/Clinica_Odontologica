package com.backend.digitalhouse.Clinica_Odontologica.service;

import com.backend.digitalhouse.Clinica_Odontologica.entity.Paciente;
import com.backend.digitalhouse.Clinica_Odontologica.dto.entrada.modificacion.PacienteModificacionEntradaDto;
import com.backend.digitalhouse.Clinica_Odontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.digitalhouse.Clinica_Odontologica.dto.salida.paciente.PacienteSalidaDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPacienteService {
    List<PacienteSalidaDto> listarPacinetes();

    PacienteSalidaDto registrarPaciente(PacienteEntradaDto paciente);

    PacienteSalidaDto buscarPacientePorId(Long id);

    void eliminarPaciente(Long id);

    PacienteSalidaDto modificarPaciente(PacienteModificacionEntradaDto pacienteModificado);
}
