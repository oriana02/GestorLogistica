package com.logistica.logistica_principal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.logistica.logistica_principal.models.Pedido;
import com.logistica.logistica_principal.repository.PedidoRepository;

@Transactional
@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidorepository;

    //public List<Pedido> findAll(){
     //   return pedidorepository.findAll();
    //}

    //public Pedido findById(int idPedido){
     //   return pedidorepository.findById(idPedido);
    //}



    
}
