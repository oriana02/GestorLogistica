package com.logistica.logistica_principal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.logistica.logistica_principal.models.Pedido;
import com.logistica.logistica_principal.models.dto.PedidoDto;



@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByComunaPedido(String comunaPedido);
    List<Pedido> findByEstadoPedido(String estadoPedido);

}
