package com.duoc.stimy.gamehub.repository;

import com.duoc.stimy.gamehub.model.Pagos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PagosRepository extends JpaRepository<Pagos, Long> {
}
