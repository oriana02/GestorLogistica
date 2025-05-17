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
import com.logistica.logistica_principal.service.PedidoService;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/hola")
    public String holaMundo(){
        return "Esto es una prueba desde Spring";
    }
    
    //lista de pedidos
    @GetMapping("/pedidos")
    public List<PedidoEntity> listarPedidos(){
        return pedidoService.listarPedidos();
    }

    //busca pedido por id   si funciona
    @GetMapping("/pedidos/{idPedido}")
    public ResponseEntity<Optional<PedidoEntity>> obtenerPedidoPorId(@PathVariable Integer idPedido){
        Optional<PedidoEntity> pedido = pedidoService.buscarPorId(idPedido);
        if (pedido != null) {
            return ResponseEntity.ok(pedido);            
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Este endpoint permite agregar pedido")
    //crea nuevo pedido
    @PostMapping("/pedidosNuevo")
    public ResponseEntity<PedidoEntity> crearPedido(@RequestBody PedidoEntity pedido){
        PedidoEntity nuevoPedido = pedidoService.agregarPedido(pedido);
        return ResponseEntity.ok(nuevoPedido);
    }

    //actualiza pedido
    @PutMapping("/pedidosActualizar")
    public ResponseEntity<Optional<PedidoEntity>> actualizarPedido(@RequestBody PedidoEntity pedido) {
        Optional<PedidoEntity> pedidoExiste = pedidoService.actualizaPedido(pedido);
        if (pedidoExiste.isPresent()) {
            return ResponseEntity.ok(pedidoExiste);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //elimina pedido
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

      @GetMapping("/pedidosdtoComuna/{comunaPedido}")
      public ResponseEntity<List<PedidoEntity>> buscarPorComuna(@PathVariable String comunaPedido){
        List<PedidoEntity> pedidoDto = pedidoService.buscarPorComuna(comunaPedido);
        if (pedidoDto != null) {
            return ResponseEntity.ok(pedidoDto);
            
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/pedidodtoEstado/{estadoPedido}")
    public ResponseEntity<List<PedidoEntity>> buscarPorEstado(@PathVariable String estadoPedido){
        List<PedidoEntity> pedidoDto = pedidoService.buscarPorEstado(estadoPedido);
        if (pedidoDto != null) {
            return ResponseEntity.ok(pedidoDto);
        }
        return ResponseEntity.notFound().build();
    }



}
