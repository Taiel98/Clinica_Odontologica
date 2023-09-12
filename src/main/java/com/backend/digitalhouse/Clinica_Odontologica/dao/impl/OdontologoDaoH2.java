package com.backend.digitalhouse.Clinica_Odontologica.dao.impl;

import com.backend.digitalhouse.Clinica_Odontologica.dao.H2Connection;
import com.backend.digitalhouse.Clinica_Odontologica.dao.IDao;
import com.backend.digitalhouse.Clinica_Odontologica.entity.Odontologo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoH2 implements IDao<Odontologo> {
    private final Logger LOGGER = LoggerFactory.getLogger(OdontologoDaoH2.class);
    @Override
    public Odontologo registrar(Odontologo odontologo) {
        Connection connection = null;
        String insert = "INSERT INTO ODONTOLOGOS (ID, MATRICULA, NOMBRE, APELLIDO) VALUES (?, ?, ?, ?";
        Odontologo odontologo1 = null;

        try{
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement ps = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, odontologo.getId());
            ps.setInt(2, odontologo.getMatricula());
            ps.setString(3, odontologo.getNombre());
            ps.setString(4, odontologo.getApellido());
            ps.execute();

            connection.commit();

            odontologo1 = new Odontologo(odontologo.getId(), odontologo.getMatricula(), odontologo.getNombre(), odontologo.getApellido());
            ResultSet key = ps.getGeneratedKeys();
            while (key.next()){
                odontologo1.setId(key.getInt(1));
            }
            LOGGER.info("Odontologo guardado: " + odontologo1);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                    System.out.println("Tuvimos un problema");
                    e.printStackTrace();
                } catch (SQLException exception) {
                    LOGGER.error(exception.getMessage());
                    exception.printStackTrace();
                }
            }
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("No se pudo cerrar la conexion: " + ex.getMessage());
            }
        }


        return odontologo1;
    }

    @Override
    public Odontologo buscarPorID(Long t) {
        return null;
    }

    @Override
    public List<Odontologo> listarTodos() {
        Connection connection = null;
        List<Odontologo> odontologos = new ArrayList<>();
        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM ODONTOLOGOS");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                odontologos.add(crearObjetoOdontologo(rs));
            }
            connection.commit();
            LOGGER.info("Listado de todos los medicamentos: " + odontologos);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                    System.out.println("Tuvimos un problema");
                    e.printStackTrace();
                } catch (SQLException exception) {
                    LOGGER.error(exception.getMessage());
                    exception.printStackTrace();
                }
            }
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("Ha ocurrido un error al intentar cerrar la bdd. " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return odontologos;
    }

    @Override
    public void eliminar(Long t) {

    }

    @Override
    public Odontologo modificar(Odontologo odontologo) {
        return null;
    }

    private Odontologo crearObjetoOdontologo(ResultSet rs) throws SQLException {

        int id = rs.getInt("id");
        int matricula = rs.getInt("matricula");
        String nombre = rs.getString("nombre");
        String apellido = rs.getString("apellido");

        return new Odontologo(id, matricula, nombre, apellido);

    }

}
