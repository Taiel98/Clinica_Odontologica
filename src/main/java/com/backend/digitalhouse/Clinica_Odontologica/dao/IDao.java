package com.backend.digitalhouse.Clinica_Odontologica.dao;
import java.util.List;

public interface IDao<T> {

    T registrar(T t);

    T buscarPorID(Long t);

    List<T> listarTodos();

    void eliminar(Long t);

    T modificar(T t);
}
