package com.duoc.stimy.gamehub.repository;

import com.duoc.stimy.gamehub.model.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long>{

    List<Carrito> buscarPorIdUsuario(Long, userId);

    void borrarPorId(Long userId);

}
