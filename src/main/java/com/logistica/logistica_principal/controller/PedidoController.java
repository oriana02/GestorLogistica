package com.logistica.logistica_principal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logistica.logistica_principal.repository.PedidoRepository;
import com.logistica.logistica_principal.service.PedidoService;

@RestController
public class PedidoController {

    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoService pedidoService;
    PedidoService accionesPedido = new PedidoService();
    
    PedidoController(PedidoRepository pedidoRepository){
        this.pedidoRepository = pedidoRepository;
    }

    @GetMapping("/hola")
    public String Holamundo(){
        return "esto es una prueba desde spring";
    }
}
