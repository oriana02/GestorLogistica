package com.logistica.logistica_principal.models;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pedido {

    @Id
    private int idPedido;
    private String comunaPedido;
    private Date fechaCompra;
    private Date fechaEntrega;
    private String estadoPedido; // entregado, en camino, pendiente

}
