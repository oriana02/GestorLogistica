package com.logistica.logistica_principal.service;
import java.util.ArrayList;
import java.util.List;
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

    public String buscarPorId(Integer idPedido){
        try {
            if (pedidorepository.existsByIdPedido(idPedido)) {
                PedidoEntity pedido = pedidorepository.findPedidoByIdPedido(idPedido);
                return "Pedido encontrado: "+ pedido.getIdPedido();
                
            } 
            return "Pedido no existe";
            
        } catch (Exception e) {
            return "Error al buscar el Pedido: "+ e.getMessage();
        }

    }

    public List<PedidoDto> buscarPorEstado(String estado){
        try {
            List<PedidoEntity>pedidos = pedidorepository.findByEstadoPedido(estado);
            List<PedidoDto> pedidoDtos = new ArrayList<>();
            for (PedidoEntity pedido : pedidos) {
                pedidoDtos.add(new PedidoDto(
                    pedido.getComunaPedido(),
                    pedido.getEstadoPedido(),
                    pedido.getFechaEntrega()
                ));  
            }
            return pedidoDtos;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public  String agregarPedido(PedidoEntity pedido){
        try {
            boolean existe = pedidorepository.existsByIdPedido(pedido.getIdPedido());  
            if (!existe) {
                PedidoEntity pedidonuevo = new PedidoEntity(); 
                pedidonuevo.setComunaPedido(pedido.getComunaPedido());
                pedidonuevo.setFechaCompra(pedido.getFechaCompra());
                pedidonuevo.setFechaEntrega(pedido.getFechaEntrega());
                pedidonuevo.setEstadoPedido(pedido.getEstadoPedido());
                pedidorepository.save(pedidonuevo);
                return "Pedido agregado correctamente";  
            }
            return "El Pedido ya existe";
        } catch (Exception e) {
            return "Error al agregar el pedido: "+ e.getMessage();
        }
    }

    //elimina pedido por id
    public String eliminarPedido(Integer idPedido){
        try {
            
            if (pedidorepository.existsByIdPedido(idPedido)) {
            pedidorepository.deleteById(idPedido);
            return "Pedido eliminado correctamente";
            }
            return "Pedido no existe";
        } catch (Exception e) {
            return e.getMessage();
        }
        
        
    }

    //actualiza un pedido existente
    public String actualizaPedido(PedidoEntity pedidoActualizado){
        try {
            Integer idPedido = pedidoActualizado.getIdPedido();
            
            if (pedidorepository.existsByIdPedido(idPedido)) {
                PedidoEntity pedido = pedidorepository.findPedidoByIdPedido(idPedido);
                pedido.setComunaPedido(pedidoActualizado.getComunaPedido());
                pedido.setFechaCompra(pedidoActualizado.getFechaCompra());
                pedido.setFechaEntrega(pedidoActualizado.getFechaEntrega());
                pedido.setEstadoPedido(pedidoActualizado.getEstadoPedido());
                pedidorepository.save(pedido);
                return "Pedido actualizado correctamente";
            }else{
                return "Pedido no existe";
            }
            
        } catch (Exception e) {
            return "Error al actualizar pedido: "+ e.getMessage();
        }
    }

}
