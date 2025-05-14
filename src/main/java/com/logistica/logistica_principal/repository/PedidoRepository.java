package com.logistica.logistica_principal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.logistica.logistica_principal.models.Pedido;
import com.logistica.logistica_principal.models.entity.PedidoEntity;


@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, Integer> {
    
    /*
    PedidoEntity findById(int idPedido);
    
    verfica si pedido existe
    Boolean existsById(int idPedido);
    

    void deleteById(int idPedido);
    PedidoEntity existById(int idPedido);
    */

    //busca pedido por id
    List<Pedido> findById(int idPedido);

    //busca pedido por estado
    List<PedidoEntity> findByEstado(String estadoPedido);

    //guarda pedido
    PedidoEntity save(Pedido pedido);

    //borra pedido por id
    void deleteById(int idPedido);

    

}
