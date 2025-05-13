package com.logistica.logistica_principal.models.entity;

import java.sql.Date;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@EntityScan
@Data
@Table(name = "Pedido")
public class PedidoEntity {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPedido;

    @Column(name = "comunaPedido")
    private String comunaPedido;
    
    @Column(name = "fechaCompra")
    private Date fehaCompra;

    @Column(name = "fechaEntrega")
    private Date fechaEntrega;
    
}
