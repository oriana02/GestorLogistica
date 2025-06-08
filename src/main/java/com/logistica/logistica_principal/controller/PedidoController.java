package com.logistica.logistica_principal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logistica.logistica_principal.models.dto.PedidoDto;
import com.logistica.logistica_principal.models.entity.PedidoEntity;
import com.logistica.logistica_principal.service.PedidoService;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Operation(summary = "Lista de Pedidos")

    @GetMapping("/pedidos")
    public ResponseEntity<List<PedidoEntity>> listarPedidos(){
        return ResponseEntity.ok(pedidoService.listarPedidos());
    }

    @Operation(summary = "Busca Pedido por su ID")
    @GetMapping("/pedidos/{idPedido}")
    public ResponseEntity<PedidoEntity> obtenerPedidoPorId(@PathVariable Integer idPedido){
        PedidoEntity pedido = pedidoService.buscarPorId(idPedido);       
        if (pedido != null) {
            return ResponseEntity.ok(pedido);            
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Agregar Pedido")
    @PostMapping("/pedidosNuevo")
    public ResponseEntity<String> agregarPedido(@RequestBody PedidoEntity nuevoPedido){
        String respuesta = pedidoService.agregarPedido(nuevoPedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @Operation(summary = "Actualizar Pedido")
    @PutMapping("/pedidosActualizar")
    public ResponseEntity<String> actualizarPedido(@RequestBody PedidoEntity pedido) {
        String respuesta = pedidoService.actualizaPedido(pedido);
        if (respuesta.equalsIgnoreCase("Pedido actualizado correctamente")) {
            return ResponseEntity.ok(respuesta);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("pedido no encontrado para actualizar"+ pedido);
        }
    }

    @Operation(summary = "Eliminar Pedido por ID")
    @DeleteMapping("/pedidosEliminar/{idPedido}")
    public ResponseEntity<String> eliminarPedido(@PathVariable Integer idPedido){
            String respuesta = pedidoService.eliminarPedido(idPedido);
        if (respuesta.equalsIgnoreCase("Pedido eliminado correctamente")) {
            pedidoService.eliminarPedido(idPedido);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido no encontrado "+ idPedido);
        }
    }

    @Operation(summary = "Busca Pedido por Estado")
    @GetMapping("/pedidodtoEstado/{estadoPedido}")
    public ResponseEntity<List<PedidoDto>> buscarPorEstado(@PathVariable String estadoPedido){
        List<PedidoDto> pedidoDto = pedidoService.buscarPorEstado(estadoPedido);
        if (pedidoDto != null) {
            return ResponseEntity.ok(pedidoDto);
        }
        return ResponseEntity.notFound().build();
    }

}
