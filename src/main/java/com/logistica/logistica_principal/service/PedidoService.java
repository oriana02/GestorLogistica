package com.logistica.logistica_principal.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.logistica.logistica_principal.models.dto.PedidoDto;
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

    // se cambia pedido a pedidodto
    private PedidoDto convertirDto(PedidoEntity entity){
        PedidoDto dto = new PedidoDto();
        dto.setIdPedido(entity.getIdPedido());
        dto.setComunaPedido(entity.getComunaPedido());
        dto.setEstadoPedido(entity.getEstadoPedido());
        dto.setFechaEntrega(entity.getFechaEntrega());
        return dto;
    }

    public List<PedidoDto> buscarPorEstado(String estadoPedido){
        try {
            List<PedidoEntity> pedidos = pedidorepository.findByEstadoPedido(estadoPedido);
            List<PedidoDto> pedidoDtos = new ArrayList<>();
            for (PedidoEntity pedido : pedidos){
                pedidoDtos.add(convertirDto(pedido));
            }
            return pedidoDtos;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }


    public List<PedidoDto> buscarPorComuna(String comunaPedido){
        try {
            List<PedidoEntity> pedidos = pedidorepository.findByComunaPedido(comunaPedido);
            List<PedidoDto> pedidoDtos = new ArrayList<>();
            for (PedidoEntity pedido : pedidos) {
                pedidoDtos.add(convertirDto(pedido)); 
            }
            return pedidoDtos;
        } catch (Exception e) {
            return new ArrayList<>();  //si da error, devuelve una lista vacia
        } 
    }
    

    public PedidoEntity agregarPedido(PedidoEntity pedido){
        return pedidorepository.save(pedido);
    }

    //elimina pedido por id
    public void eliminarPedido(Integer idPedido){
        pedidorepository.deleteById(idPedido);
    }

    //actualiza un pedido existente
    public Optional<PedidoEntity> actualizaPedido(PedidoEntity pedidoActualizado){
        try {
            Integer idPedido = pedidoActualizado.getIdPedido();
            Optional<PedidoEntity> pedidoExiste = pedidorepository.findById(idPedido);
            
            if (pedidoExiste.isPresent()) {
                PedidoEntity pedido = pedidoExiste.get();
                pedido.setComunaPedido(pedidoActualizado.getComunaPedido());
                pedido.setFechaCompra(pedidoActualizado.getFechaCompra());
                pedido.setFechaEntrega(pedidoActualizado.getFechaEntrega());
                pedido.setEstadoPedido(pedidoActualizado.getEstadoPedido());
                PedidoEntity pedidoGuardado = pedidorepository.save(pedido);
                return Optional.of(pedidoGuardado);
            }else{
                return Optional.empty();
            }
            
        } catch (Exception e) {
            return Optional.empty();
        }
    }

}
