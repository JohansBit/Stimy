package com.duoc.stimy.gamehub.repository;

import com.duoc.stimy.gamehub.model.Logro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogroRepository extends JpaRepository<Logro, Long> {
    List<Logro> findByVideojuegoId(Long videojuegoId);
}