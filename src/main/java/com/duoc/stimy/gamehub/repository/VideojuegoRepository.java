package com.duoc.stimy.gamehub.repository;

import com.duoc.stimy.gamehub.model.Videojuego;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideojuegoRepository extends JpaRepository<Videojuego, Long> {
}