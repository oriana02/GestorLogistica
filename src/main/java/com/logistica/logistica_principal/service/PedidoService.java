package com.logistica.logistica_principal.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.logistica.logistica_principal.models.Pedido;
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

    public Optional<PedidoEntity> buscarPorId(Integer idPedido){
        return pedidorepository.findById(idPedido);
    }

    public List<PedidoEntity> buscarPorEstado(String estadoPedido){
        return pedidorepository.findByEstado(estadoPedido);
    }

    public PedidoEntity guardarPedido(Pedido pedido){
        return pedidorepository.save(pedido);
    }

    public void eliminarPedido(Integer idPedido){
        pedidorepository.deleteById(idPedido);
    }

}
