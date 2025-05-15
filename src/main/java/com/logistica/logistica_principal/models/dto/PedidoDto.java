package com.logistica.logistica_principal.models.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDto {
    private int idPedido;
    private String comunaPedido;
    private String estadoPedido;
    private Date fechaEntrega;


    
}
