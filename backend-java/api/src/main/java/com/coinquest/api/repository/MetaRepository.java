package com.coinquest.api.repository;

import com.coinquest.api.model.Meta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MetaRepository extends JpaRepository<Meta, Long> {
    //Busca todas as metas pertecentes a um ID de usuário específico
    List<Meta> findByUsuarioId(Long usuarioId);

}
