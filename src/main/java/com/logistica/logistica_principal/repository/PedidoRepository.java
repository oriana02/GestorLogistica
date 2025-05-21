package com.logistica.logistica_principal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.logistica.logistica_principal.models.entity.PedidoEntity;



@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, Integer> {

    List<PedidoEntity> findByComunaPedido(String comunaPedido);
    List<PedidoEntity> findByEstadoPedido(String estadoPedido);

    
}
