package com.duoc.stimy.gamehub.repository;

import com.duoc.stimy.gamehub.model.Deseo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeseoRepository extends JpaRepository<Deseo, Long> {
    List<Deseo> findByUsuarioId(Long usuarioId);
}