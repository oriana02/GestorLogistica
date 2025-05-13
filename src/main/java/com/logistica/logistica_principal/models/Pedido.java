package com.logistica.logistica_principal.models;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {

    private int idPedido;
    private String comunaPedido;
    private Date fehaCompra;
    private Date fechaLimite;
    private Date fechaEntrega;
    private String estadoPedido; //en preparacion, en transito, pedido entregado



    
}
