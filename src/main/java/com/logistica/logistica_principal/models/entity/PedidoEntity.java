package com.logistica.logistica_principal.models.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "Pedido")
public class PedidoEntity {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPedido;

    @Column(name = "comunaPedido")
    private String comunaPedido;
    
    @Column(name = "fechaCompra")
    private Date fechaCompra;

    @Column(name = "fechaEntrega")
    private Date fechaEntrega;

    @Column(name = "estadoPedido")
    private String estadoPedido;
    
}
