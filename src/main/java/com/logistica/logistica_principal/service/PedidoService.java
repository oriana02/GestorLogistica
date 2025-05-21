package com.logistica.logistica_principal.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.logistica.logistica_principal.models.Pedido;
import com.logistica.logistica_principal.models.dto.PedidoDto;
import com.logistica.logistica_principal.repository.PedidoRepository;

@Transactional
@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidorepository;    

   public List<Pedido> listarPedidos(){
        return pedidorepository.findAll();
    }

    public Optional<Pedido> buscarPorId(Integer idPedido){
        return pedidorepository.findById(idPedido);
    }

    // se cambia pedido a pedidodto
    private PedidoDto convertirDto(Pedido pedido){
        PedidoDto dto = new PedidoDto();
        dto.setIdPedido(pedido.getIdPedido());
        dto.setComunaPedido(pedido.getComunaPedido());
        dto.setEstadoPedido(pedido.getEstadoPedido());
        dto.setFechaEntrega(pedido.getFechaEntrega());
        return dto;
    }

    public List<PedidoDto> buscarPorEstado(String estadoPedido){
        try {
            List<Pedido> pedidos = pedidorepository.findByEstadoPedido(estadoPedido);
            List<PedidoDto> pedidoDtos = new ArrayList<>();
            for (Pedido pedido : pedidos){
                pedidoDtos.add(convertirDto(pedido));
            }
            return pedidoDtos;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }


    public List<PedidoDto> buscarPorComuna(String comunaPedido){
        try {
            List<Pedido> pedidos = pedidorepository.findByComunaPedido(comunaPedido);
            List<PedidoDto> pedidoDtos = new ArrayList<>();
            for (Pedido pedido : pedidos) {
                pedidoDtos.add(convertirDto(pedido)); 
            }
            return pedidoDtos;
        } catch (Exception e) {
            return new ArrayList<>();  //si da error, devuelve una lista vacia
        } 
    }
    

    public Pedido agregarPedido(Pedido pedido){
        return pedidorepository.save(pedido);
    }

    //elimina pedido por id
    public void eliminarPedido(Integer idPedido){
        pedidorepository.deleteById(idPedido);
    }

    //actualiza un pedido existente
    public Optional<Pedido> actualizaPedido(Pedido pedidoActualizado){
        try {
            Integer idPedido = pedidoActualizado.getIdPedido();
            Optional<Pedido> pedidoExiste = pedidorepository.findById(idPedido);
            
            if (pedidoExiste.isPresent()) {
                Pedido pedido = pedidoExiste.get();
                pedido.setComunaPedido(pedidoActualizado.getComunaPedido());
                pedido.setFechaCompra(pedidoActualizado.getFechaCompra());
                pedido.setFechaEntrega(pedidoActualizado.getFechaEntrega());
                pedido.setEstadoPedido(pedidoActualizado.getEstadoPedido());
                Pedido pedidoGuardado = pedidorepository.save(pedido);
                return Optional.of(pedidoGuardado);
            }else{
                return Optional.empty();
            }
            
        } catch (Exception e) {
            return Optional.empty();
        }
    }

}
