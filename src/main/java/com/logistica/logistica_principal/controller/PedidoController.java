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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
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

    //busca pedido por id
    @GetMapping("/pedidos/{id}")
    public ResponseEntity<PedidoEntity> obtenerPedidoPorId(@PathVariable Integer id){
        Optional<PedidoEntity> pedido = pedidoService.buscarPorId(id);
        return pedido.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //crea nuevo pedido
    @PostMapping("/pedidos")
    public ResponseEntity<PedidoEntity> crearPedido(@RequestBody PedidoEntity pedido){
        PedidoEntity nuevoPedido = pedidoService.agregarPedido(pedido);
        return ResponseEntity.ok(nuevoPedido);
    }

    //actualiza pedido
    @PutMapping("/pedidos/{id}")
    public ResponseEntity<Optional<PedidoEntity>> actualizarPedido(@PathVariable Integer id, @RequestBody PedidoEntity pedido) {
        Optional<PedidoEntity> pedidoExistente = pedidoService.buscarPorId(id);
        if (pedidoExistente.isPresent()) {
            Optional<PedidoEntity> pedidoActualizado = pedidoService.actualizaPedido(id, pedido);
            return ResponseEntity.ok(pedidoActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //elimina pedido
    @DeleteMapping("/pedidos/{id}")
    public ResponseEntity<Void> eliminarPedido(@PathVariable Integer id){
        Optional<PedidoEntity> pedido = pedidoService.buscarPorId(id);
        if (pedido.isPresent()) {
            pedidoService.eliminarPedido(id);
            return ResponseEntity.noContent().build();
        } else{
            return ResponseEntity.notFound().build();
        }
        
    }


}
