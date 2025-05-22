package com.logistica.logistica_principal.models.dto;


import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDto {
    private String comunaPedido;
    private String estadoPedido;
    private LocalDate fechaEntrega;
}
