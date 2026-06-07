package com.duoc.stimy.gamehub.repository;

import com.duoc.stimy.gamehub.model.Amigos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AmigosRepository extends JpaRepository<Amigos, Long> {
    List<Amigos> findByUsuarioIdAndEstado(Long usuarioId, String estado);
}