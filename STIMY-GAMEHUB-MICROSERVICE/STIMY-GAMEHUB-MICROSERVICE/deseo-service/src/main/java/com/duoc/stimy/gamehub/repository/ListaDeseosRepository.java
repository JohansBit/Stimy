package com.duoc.stimy.gamehub.repository;

import com.duoc.stimy.gamehub.model.ListaDeseos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ListaDeseosRepository extends JpaRepository<ListaDeseos, Long> {
    List<ListaDeseos> findByUsuarioId(Long usuarioId);
}