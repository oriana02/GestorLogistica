package com.logistica.logistica_principal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.logistica.logistica_principal.models.entity.PedidoEntity;


@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, Integer> {
    PedidoEntity findById(int idPedido);
    Boolean exexistsById(int idPedido);
    void deledeleteById(int idPedido);
    PedidoEntity existById(int idPedido);
    PedidoEntity

}
