package com.logistica.logistica_principal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.logistica.logistica_principal.models.Pedido;
import com.logistica.logistica_principal.models.entity.PedidoEntity;


@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, Integer> {

    PedidoEntity findByComunaPedido(String comunaPedido);
    PedidoEntity findByEstadoPedido(String estadoPedido);
    PedidoEntity findById(int idPedido);
    Boolean existsById(int idPedido);
    void deleteById(int idPedido);
    PedidoEntity existById(int idPedido);
    PedidoEntity save(Pedido pedido);

}
