package com.duoc.stimy.gamehub.repository;

import com.duoc.stimy.gamehub.model.Biblioteca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BibliotecaRepository  extends JpaRepository<Biblioteca, Long>{
    List<Biblioteca> findByusuarioId(Long usuarioId);

}
