package com.logistica.logistica_principal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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
        when(pedidoRepository.existsByIdPedido(1)).thenReturn(false);
        String result = pedidoservice.agregarPedido(pedidoEntity);

        assertEquals("El Pedido ya existe", result);

    }
    
    @Test 
    public void testActualizarPedido_existe(){
        when(pedidoRepository.existsByIdPedido(1)).thenReturn(true);


    }

    @Test
    public void testBuscarPorId_existe(){

    }

    @Test
    public void testBuscarPorId_noExiste(){

    }

    @Test
    public void testBuscarPorEstado_existe(){

    }

    @Test
    public void testBuscarPorEstado_noExiste(){

    }

    @Test
    public void borrarPedido(){
        when(pedidoRepository.existsByIdPedido(1)).thenReturn(true);
        doNothing().when(pedidoRepository).deleteById(1);
        String result = pedidoservice.eliminarPedido(1);

        assertEquals("Pedido eliminado correctamente", result);
    }


}
