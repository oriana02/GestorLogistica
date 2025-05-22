package com.logistica.logistica_principal.models;


import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {

    private int idPedido;
    private String comunaPedido;
    private LocalDate fechaCompra;
    private LocalDate fechaEntrega;
    private String estadoPedido; // entregado, en camino, pendiente

}
