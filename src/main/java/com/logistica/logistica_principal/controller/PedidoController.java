package com.logistica.logistica_principal.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logistica.logistica_principal.models.entity.PedidoEntity;
import com.logistica.logistica_principal.repository.PedidoRepository;
import com.logistica.logistica_principal.service.PedidoService;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api")
public class PedidoController {

    private final PedidoRepository pedidoRepository;

    @Autowired
    private PedidoService pedidoService;

    PedidoController(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }
    
    @Operation(summary = "Entrega una lista de los pedidos")
    //lista de pedidos
    @GetMapping("/pedidos")
    public List<PedidoEntity> listarPedidos(){
        return pedidoService.listarPedidos();
    }

    @Operation(summary = "Busca un pedido por su id")
    @GetMapping("/pedidos/{idPedido}")
    public ResponseEntity<Optional<PedidoEntity>> obtenerPedidoPorId(@PathVariable Integer idPedido){
        Optional<PedidoEntity> pedido = pedidoService.buscarPorId(idPedido);
        if (pedido != null) {
            return ResponseEntity.ok(pedido);            
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Este endpoint permite agregar pedido")
    @PostMapping("/pedidosNuevo")
    public ResponseEntity<PedidoEntity> agregarPedido(@RequestBody PedidoEntity nuevoPedido){
        nuevoPedido.setIdPedido(null);
        PedidoEntity savedPedido = pedidoService.agregarPedido(nuevoPedido);
        return ResponseEntity.ok(nuevoPedido);
        
        //PedidoEntity nuevoPedido = pedidoService.agregarPedido(pedido);
        //return ResponseEntity.ok(nuevoPedido);
    }

    @Operation(summary = "Permite actualizar el pedido")
    @PutMapping("/pedidosActualizar")
    public ResponseEntity<Optional<PedidoEntity>> actualizarPedido(@RequestBody PedidoEntity pedido) {
        Optional<PedidoEntity> pedidoExiste = pedidoService.actualizaPedido(pedido);
        if (pedidoExiste.isPresent()) {
            return ResponseEntity.ok(pedidoExiste);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Elimina un pedido por id")
    @DeleteMapping("/pedidosEliminado/{idPedido}")
    public ResponseEntity<Optional<PedidoEntity>> eliminarPedido(@PathVariable Integer idPedido){
        Optional<PedidoEntity> pedido = pedidoService.buscarPorId(idPedido);
        if (pedido.isPresent()) {
            pedidoService.eliminarPedido(idPedido);
            return ResponseEntity.ok(pedido);
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Busca pedido por comuna")
    @GetMapping("/pedidosdtoComuna/{comunaPedido}")
    public ResponseEntity<List<PedidoEntity>> buscarPorComuna(@PathVariable String comunaPedido){
        List<PedidoEntity> pedidoDto = pedidoService.buscarPorComuna(comunaPedido);
        if (pedidoDto != null) {
            return ResponseEntity.ok(pedidoDto);
            
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Busca pedido por estado de pedido")
    @GetMapping("/pedidodtoEstado/{estadoPedido}")
    public ResponseEntity<List<PedidoEntity>> buscarPorEstado(@PathVariable String estadoPedido){
        List<PedidoEntity> pedidoDto = pedidoService.buscarPorEstado(estadoPedido);
        if (pedidoDto != null) {
            return ResponseEntity.ok(pedidoDto);
        }
        return ResponseEntity.notFound().build();
    }



}
