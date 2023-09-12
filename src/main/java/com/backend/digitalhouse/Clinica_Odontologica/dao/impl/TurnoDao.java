package com.backend.digitalhouse.Clinica_Odontologica.dao.impl;

import com.backend.digitalhouse.Clinica_Odontologica.dao.IDao;
import com.backend.digitalhouse.Clinica_Odontologica.entity.Odontologo;
import com.backend.digitalhouse.Clinica_Odontologica.entity.Paciente;
import com.backend.digitalhouse.Clinica_Odontologica.entity.Turno;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class TurnoDao implements IDao<Turno> {

    private PacienteDaoH2 pacienteDaoH2 = new PacienteDaoH2();
    private OdontologoDaoH2 odontologoDaoH2 = new OdontologoDaoH2();

    private List<Turno> turnos;

    public TurnoDao() {turnos = new ArrayList<>();}

    @Override
    public Turno registrar(Turno turno) {
        Turno turnoGuardado = turno;
        turnos.add(turnoGuardado);
        return turnoGuardado;
    }

    @Override
    public Turno buscarPorID(Long id) {
        Turno turnoBuscado = null;
        for(Turno t : turnos) {
            if (Objects.equals(t.getId(), id)) {
                turnoBuscado = t;
            }
        }
        return turnoBuscado;
    }

    @Override
    public List<Turno> listarTodos() {
        return null;
    }

    @Override
    public void eliminar(Long id) {
        Turno t = buscarPorID(id);
        if (t != null) {
            turnos.remove(t);
        }
    }

    @Override
    public Turno modificar(Turno turno) {
        eliminar(turno.getId());
        registrar(turno);
        return turno;
    }
}
