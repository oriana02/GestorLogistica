package com.logistica.logistica_principal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.logistica.logistica_principal.models.dto.PedidoDto;
import com.logistica.logistica_principal.models.entity.PedidoEntity;
import com.logistica.logistica_principal.repository.PedidoRepository;
import com.logistica.logistica_principal.service.PedidoService;

public class PedidoTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private PedidoService pedidoservice;
    private PedidoEntity pedidoEntity;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        pedidoEntity = new PedidoEntity();
        pedidoEntity.setIdPedido(1);
        pedidoEntity.setComunaPedido("Quilpue");
        pedidoEntity.setFechaCompra(LocalDate.of(25, 5, 12));
        pedidoEntity.setFechaEntrega(LocalDate.of(25, 5, 24));
        pedidoEntity.setEstadoPedido("Entregado");
    }

    @Test
    public void testAgregarPedido_nuevo(){
        when(pedidoRepository.save(any(PedidoEntity.class))).thenReturn(pedidoEntity);
        String result = pedidoservice.agregarPedido(pedidoEntity);

        assertEquals("Pedido agregado correctamente", result);
    }

    @Test
    public void testAgregarPedido_existe(){
        when(pedidoRepository.existsByIdPedido(1)).thenReturn(true);
        String result = pedidoservice.agregarPedido(pedidoEntity);

        assertEquals("El Pedido ya existe", result);
    }
    
    @Test 
    public void testActualizarPedido_existe(){
        when(pedidoRepository.existsByIdPedido(1)).thenReturn(true);
        when(pedidoRepository.findPedidoByIdPedido(1)).thenReturn(pedidoEntity);
        when(pedidoRepository.save(any(PedidoEntity.class))).thenReturn(pedidoEntity);
        
        PedidoEntity actualizado = new PedidoEntity(1, "Viña", LocalDate.of(25, 5, 23), LocalDate.of(25, 5, 24), "Pendiente");
        String result = pedidoservice.actualizaPedido(actualizado);

        assertEquals("Pedido actualizado correctamente", result);

    }

    @Test
    public void testBuscarPorId_existe(){
        when(pedidoRepository.existsByIdPedido(1)).thenReturn(true);
        when(pedidoRepository.findPedidoByIdPedido(1)).thenReturn(pedidoEntity);
        PedidoEntity result = pedidoservice.buscarPorId(1);
        assertNotNull(result);
        assertEquals(1, result.getIdPedido());

    }

    @Test
    public void testBuscarPorId_noExiste(){
        when(pedidoRepository.existsByIdPedido(55)).thenReturn(false);
        when(pedidoRepository.findPedidoByIdPedido(55)).thenReturn(pedidoEntity);
        PedidoEntity result = pedidoservice.buscarPorId(55);
        assertNull(result);

    }

    @Test
    public void testBuscarPorEstado_existe(){
        PedidoEntity pedido1 = new PedidoEntity(2, "valpo", LocalDate.of(25, 4, 13), LocalDate.of(25, 6, 23), "Pendiente");
        PedidoEntity pedido2 = new PedidoEntity(3, "quilpue", LocalDate.of(25, 4, 13), LocalDate.of(25, 6, 23), "Pendiente");
        List<PedidoEntity> listaPedidos = Arrays.asList(pedido1, pedido2);
        
        when(pedidoRepository.findByEstadoPedido("Pendiente")).thenReturn(listaPedidos);
        List<PedidoDto> result = pedidoservice.buscarPorEstado("Pendiente");

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("valpo", result.get(0).getComunaPedido());
        assertEquals("Pendiente", result.get(0).getEstadoPedido());
    }

    @Test
    public void borrarPedido(){
        when(pedidoRepository.existsByIdPedido(1)).thenReturn(true);
        doNothing().when(pedidoRepository).deleteById(1);
        String result = pedidoservice.eliminarPedido(1);

        assertEquals("Pedido eliminado correctamente", result);
    }


}
