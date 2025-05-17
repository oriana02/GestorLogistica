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

   public List<PedidoEntity> listarPedidos(){
        return pedidorepository.findAll();
    }

    
    //busca pedido por id
    public Optional<PedidoEntity> buscarPorId(Integer idPedido){
        return pedidorepository.findById(idPedido);
    }

    public List<PedidoEntity> buscarPorEstado(String estadoPedido){
        return pedidorepository.findByEstadoPedido(estadoPedido);
    }

    public List<PedidoEntity> buscarPorComuna(String comunaPedido){
        return pedidorepository.findByComunaPedido(comunaPedido);
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
    public Optional<PedidoEntity> actualizaPedido(PedidoEntity pedidoActualizado){
        Integer idPedido = pedidoActualizado.getIdPedido();
        
        return pedidorepository.findById(idPedido).map(pedido -> {
            pedido.setComunaPedido(pedidoActualizado.getComunaPedido());
            pedido.setFechaCompra(pedidoActualizado.getFechaCompra());
            pedido.setFechaEntrega(pedidoActualizado.getFechaEntrega());
            return pedidorepository.save(pedido);
        });
    }

}
