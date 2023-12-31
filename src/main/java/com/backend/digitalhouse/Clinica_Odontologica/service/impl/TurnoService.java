package com.backend.digitalhouse.Clinica_Odontologica.service.impl;

import com.backend.digitalhouse.Clinica_Odontologica.dto.entrada.modificacion.TurnoModificacionEntradaDto;
import com.backend.digitalhouse.Clinica_Odontologica.dto.entrada.turno.TurnoEntradaDto;
import com.backend.digitalhouse.Clinica_Odontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.digitalhouse.Clinica_Odontologica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.digitalhouse.Clinica_Odontologica.dto.salida.turno.OdontologoTurnoSalidaDto;
import com.backend.digitalhouse.Clinica_Odontologica.dto.salida.turno.PacienteTurnoSalidaDto;
import com.backend.digitalhouse.Clinica_Odontologica.dto.salida.turno.TurnoSalidaDto;
import com.backend.digitalhouse.Clinica_Odontologica.entity.Odontologo;
import com.backend.digitalhouse.Clinica_Odontologica.entity.Paciente;
import com.backend.digitalhouse.Clinica_Odontologica.entity.Turno;
import com.backend.digitalhouse.Clinica_Odontologica.exceptions.BadRequestException;
import com.backend.digitalhouse.Clinica_Odontologica.exceptions.ResourceNotFoundException;
import com.backend.digitalhouse.Clinica_Odontologica.repository.TurnoRepository;
import com.backend.digitalhouse.Clinica_Odontologica.service.ITurnoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TurnoService implements ITurnoService{

    private final TurnoRepository turnoRepository;
    private final ModelMapper modelMapper;
    private final OdontologoService odontologoService;
    private final PacienteService pacienteService;

    @Autowired
    public TurnoService(TurnoRepository turnoRepository, ModelMapper modelMapper, OdontologoService odontologoService, PacienteService pacienteService) {
        this.turnoRepository = turnoRepository;
        this.modelMapper = modelMapper;
        this.odontologoService = odontologoService;
        this.pacienteService = pacienteService;

    }
    @Override
    public TurnoSalidaDto registrarTurno(TurnoEntradaDto turnoEntradaDto) throws BadRequestException {
        TurnoSalidaDto turnoSalidaDto;

        PacienteSalidaDto paciente = pacienteService.buscarPacientePorId(turnoEntradaDto.getPacienteId());
        OdontologoSalidaDto odontologo = odontologoService.buscarOdontologoPorId(turnoEntradaDto.getOdontologoId());

        String pacienteNoEnBdd = "El paciente no se encuentra en nuestra base de datos";
        String odontologoNoEnBdd = "El odontologo no se encuentra en nuestra base de datos";

        if (paciente == null || odontologo == null) {
            if (paciente == null && odontologo == null) {
                throw new BadRequestException("El paciente y el odontologo no se encuentran en nuestra base de datos");
            } else if (paciente == null) {
                throw new BadRequestException(pacienteNoEnBdd);
            } else {
                throw new BadRequestException(odontologoNoEnBdd);
            }
        } else {
            Turno turnoNuevo = turnoRepository.save(modelMapper.map(turnoEntradaDto, Turno.class));
            turnoSalidaDto = entidadADto(turnoNuevo);
        }

        return turnoSalidaDto;
    }

    @Override
    public List<TurnoSalidaDto> listarTurnos() {
        List<TurnoSalidaDto> turnos = turnoRepository.findAll().stream().map(this::entidadADto).toList();
        return turnos;
    }

    @Override
    public TurnoSalidaDto buscarTurnoPorId(Long id) {
        Turno turnoBuscado = turnoRepository.findById(id).orElse(null);

        TurnoSalidaDto turnoSalidaDto = null;
        if (turnoBuscado != null) {
            turnoSalidaDto = entidadADto(turnoBuscado);
        }
        return turnoSalidaDto;
    }

    @Override
    public void eliminarTurno(Long id) throws ResourceNotFoundException {
        if (buscarTurnoPorId(id) != null) {
            turnoRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("No se ha encontrado el turno con id " + id);
        }
    }

    @Override
    public TurnoSalidaDto modificarTurno(TurnoModificacionEntradaDto turnoModificacionEntradaDto) throws ResourceNotFoundException {
        Turno turnoAActualizar = turnoRepository.findById(turnoModificacionEntradaDto.getId()).orElse(null);
        TurnoSalidaDto turnoSalidaDto = null;

        if (turnoAActualizar != null) {
            turnoAActualizar.setPaciente(modelMapper.map(pacienteService.buscarPacientePorId(turnoModificacionEntradaDto.getIdPaciente()), Paciente.class));
            turnoAActualizar.setOdontologo(modelMapper.map(odontologoService.buscarOdontologoPorId(turnoModificacionEntradaDto.getIdOdontologo()), Odontologo.class));
            turnoAActualizar.setFechaYHora(turnoModificacionEntradaDto.getFechaYHora());
            turnoRepository.save(turnoAActualizar);

            turnoSalidaDto = entidadADto(turnoAActualizar);

        } else {
            throw new ResourceNotFoundException("No fue posible actualizar los datos ya que el turno no se encuentra registrado");
        }
        return turnoSalidaDto;
    }

    private PacienteTurnoSalidaDto pacienteSalidaDtoASalidaTurnoDto(Long id) {
        return modelMapper.map(pacienteService.buscarPacientePorId(id), PacienteTurnoSalidaDto.class);
    }

    private OdontologoTurnoSalidaDto odontologoSalidaDtoASalidaTurnoDto(Long id) {
        return modelMapper.map(odontologoService.buscarOdontologoPorId(id), OdontologoTurnoSalidaDto.class);
    }

    private TurnoSalidaDto entidadADto(Turno turno) {
        TurnoSalidaDto turnoSalidaDto = modelMapper.map(turno, TurnoSalidaDto.class);
        turnoSalidaDto.setPacienteTurnoSalidaDto(pacienteSalidaDtoASalidaTurnoDto(turno.getPaciente().getId()));
        turnoSalidaDto.setOdontologoTurnoSalidaDto(odontologoSalidaDtoASalidaTurnoDto(turno.getOdontologo().getId()));
        return turnoSalidaDto;
    }
}
