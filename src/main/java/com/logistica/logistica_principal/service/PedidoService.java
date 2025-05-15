package com.logistica.logistica_principal.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.logistica.logistica_principal.models.entity.PedidoEntity;
import com.logistica.logistica_principal.repository.PedidoRepository;

@Transactional
@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidorepository;

    //lista todos los pedidos
    public List<PedidoEntity> listarPedidos(){
        return pedidorepository.findAll();
    }
    
    //busca pedido por id
    public Optional<PedidoEntity> buscarPorId(Integer idPedido){
        return pedidorepository.findById(idPedido);
    }

    public PedidoEntity buscarPorEstado(String estadoPedido){
        return pedidorepository.findByEstado(estadoPedido);
    }

    public PedidoEntity buscarPorComuna(String comunaPedido){
        return pedidorepository.findByComuna(comunaPedido);
    }
    
    //crea nuevo pedido
    public PedidoEntity agregarPedido(PedidoEntity pedido){
        return pedidorepository.save(pedido);
    }

    //elimina pedido por id
    public void eliminarPedido(Integer idPedido){
        pedidorepository.deleteById(idPedido);
    }

    //actualiza un pedido existente
    public Optional<PedidoEntity> actualizaPedido(Integer idPedido,PedidoEntity pedidoActualizado){
        return pedidorepository.findById(idPedido).map(pedido -> {
            pedido.setComunaPedido(pedidoActualizado.getComunaPedido());
            pedido.setFechaCompra(pedidoActualizado.getFechaCompra());
            pedido.setFechaEntrega(pedidoActualizado.getFechaEntrega());
            return
             pedidorepository.save(pedido);
        });

    }


}
