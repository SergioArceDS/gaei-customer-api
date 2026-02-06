package com.gaei.customer.service;

import com.gaei.customer.dto.ClienteRequestDTO;
import com.gaei.customer.dto.ClienteResponseDTO;

public interface ClienteService {

    void guardarCliente(ClienteRequestDTO clienteRequestDTO);
    ClienteResponseDTO consultarCliente(String tipoDocumento, String numeroDocumento);
    void actualizarCliente(ClienteRequestDTO clienteRequestDTO);
}
