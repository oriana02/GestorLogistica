package com.logistica.logistica_principal.service;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import com.logistica.logistica_principal.models.Pedido;
import com.logistica.logistica_principal.models.dto.PedidoDto;
import com.logistica.logistica_principal.models.entity.PedidoEntity;
import com.logistica.logistica_principal.repository.PedidoRepository;

@Transactional
@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidorepository;

    private final List<Pedido> pedidos = new ArrayList<>();

    public PedidoService(){
        pedidos.add(new Pedido(1, "viña", new Date(2025,4,12), new Date(2025,4,13), new Date(2025,4,23), "en preparacion"));
    }
    

   public List<Pedido> listarPedidos(){
        return pedidos;
    }

    
    //busca pedido por id
    public Optional<PedidoEntity> buscarPorId(Integer idPedido){
        return pedidorepository.findById(idPedido);
    }

    public PedidoEntity buscarPorEstado(String estadoPedido){
        return pedidorepository.findByEstadoPedido(estadoPedido);
    }

    public PedidoEntity buscarPorComuna(String comunaPedido){
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
