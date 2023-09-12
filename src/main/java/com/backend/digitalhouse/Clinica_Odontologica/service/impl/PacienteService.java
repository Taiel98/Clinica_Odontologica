package com.backend.digitalhouse.Clinica_Odontologica.service.impl;

import com.backend.digitalhouse.Clinica_Odontologica.dao.IDao;
import com.backend.digitalhouse.Clinica_Odontologica.dto.entrada.modificacion.PacienteModificacionEntradaDto;
import com.backend.digitalhouse.Clinica_Odontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.digitalhouse.Clinica_Odontologica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.digitalhouse.Clinica_Odontologica.entity.Paciente;
import com.backend.digitalhouse.Clinica_Odontologica.service.IPacienteService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService implements IPacienteService{

    private final IDao<Paciente> pacienteIDao;
    private final ModelMapper modelMapper;

    public PacienteService(IDao<Paciente> pacienteIDao, ModelMapper modelMapper) {
        this.pacienteIDao = pacienteIDao;
        this.modelMapper = modelMapper;
        configureMapping();
    }

    /*public Paciente registrarPaciente(Paciente paciente) {
        return pacienteIDao.registrar(paciente);
    }

    public Paciente buscarPacientePorId(int id) {
        return pacienteIDao.buscarPorID(id);
    }

    public List<Paciente> listarPacientes() {
    return pacienteIDao.listarTodos();
    }

    public void eliminarPaciente(int id) {
        pacienteIDao.eliminar(id);
    }*/

    @Override
    public List<PacienteSalidaDto> listarPacinetes() {
        List<Paciente> pacientes = pacienteIDao.listarTodos();

        return pacientes.stream().map(this::entidadADtoSalida).toList();
    }

    @Override
    public PacienteSalidaDto registrarPaciente(PacienteEntradaDto paciente) {
        Paciente pacienteRecibido = dtoEntradaAEntidad(paciente);
        Paciente pacienteRegistrado = pacienteIDao.registrar(pacienteRecibido);

        return entidadADtoSalida(pacienteRegistrado);
    }

    @Override
    public PacienteSalidaDto buscarPacientePorId(Long id) {
        return entidadADtoSalida(pacienteIDao.buscarPorID(id));
    }

    @Override
    public void eliminarPaciente(Long id) {
        pacienteIDao.eliminar(id);
    }

    @Override
    public PacienteSalidaDto modificarPaciente(PacienteModificacionEntradaDto pacienteModificado) {
        PacienteSalidaDto pacienteSalidaDto = null;
        Paciente pacienteAModificar = pacienteIDao.buscarPorID(pacienteModificado.getId());

        if(pacienteAModificar != null){
            pacienteAModificar = dtoModificadoAEntidad(pacienteModificado);
            pacienteSalidaDto = entidadADtoSalida(pacienteIDao.modificar(pacienteAModificar));
        }
        return pacienteSalidaDto;
    }

    public PacienteSalidaDto entidadADtoSalida(Paciente paciente){
        return modelMapper.map(paciente, PacienteSalidaDto.class);
    }

    private void configureMapping() {
        modelMapper.typeMap(PacienteEntradaDto.class, Paciente.class).addMappings(mapper -> mapper.map(PacienteEntradaDto::getDomicilio, Paciente::setDomicilio));
        modelMapper.typeMap(Paciente.class, PacienteSalidaDto.class).addMappings(mapper -> mapper.map(Paciente::getDomicilio, PacienteSalidaDto:: setDomicilio));
        modelMapper.typeMap(PacienteModificacionEntradaDto.class, Paciente.class).addMappings(mapper -> mapper.map(PacienteModificacionEntradaDto::getDomicilio, Paciente::setDomicilio));
    }

    public Paciente dtoModificadoAEntidad(PacienteModificacionEntradaDto pacienteEntradaDto) {
        return modelMapper.map(pacienteEntradaDto, Paciente.class);
    }

    public Paciente dtoEntradaAEntidad(PacienteEntradaDto pacienteEntradaDto) {
        return modelMapper.map(pacienteEntradaDto, Paciente.class);
    }
}
