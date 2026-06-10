package com.duoc.stimy.gamehub.repository;

import com.duoc.stimy.gamehub.model.Resenia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReseniaRepository extends JpaRepository<Resenia, Long> {
    List<Resenia> findByVideojuegoId(Long videojuegoId);
}