package com.gaei.customer.mapper;

import com.gaei.customer.dto.ClienteRequestDTO;
import com.gaei.customer.dto.ClienteResponseDTO;
import com.gaei.customer.entity.ClienteEntity;

public final class ClienteMapper {

    private ClienteMapper(){}

    public static ClienteEntity toEntity(ClienteRequestDTO clienteRequestDTO) {
        if (clienteRequestDTO == null) {
            return null;
        }

        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setTipoDocumento(clienteRequestDTO.getTipoDocumento());
        clienteEntity.setNumeroDocumento(clienteRequestDTO.getNumeroDocumento());
        clienteEntity.setPrimerNombre(clienteRequestDTO.getPrimerNombre());
        clienteEntity.setSegundoNombre(clienteRequestDTO.getSegundoNombre());
        clienteEntity.setPrimerApellido(clienteRequestDTO.getPrimerApellido());
        clienteEntity.setSegundoApellido(clienteRequestDTO.getSegundoApellido());
        clienteEntity.setTelefono(clienteRequestDTO.getTelefono());
        clienteEntity.setCorreoElectronico(clienteRequestDTO.getCorreoElectronico());

        return clienteEntity;
    }

    public static ClienteResponseDTO toResponse(ClienteEntity clienteEntity) {
        if (clienteEntity == null) {
            return null;
        }

        ClienteResponseDTO response = new ClienteResponseDTO();
        response.setTipoDocumento(clienteEntity.getTipoDocumento());
        response.setNumeroDocumento(clienteEntity.getNumeroDocumento());
        response.setPrimerNombre(clienteEntity.getPrimerNombre());
        response.setSegundoNombre(clienteEntity.getSegundoNombre());
        response.setPrimerApellido(clienteEntity.getPrimerApellido());
        response.setSegundoApellido(clienteEntity.getSegundoApellido());
        response.setTelefono(clienteEntity.getTelefono());
        response.setCorreoElectronico(clienteEntity.getCorreoElectronico());

        return response;
    }


    public static void updateEntity(ClienteEntity clienteEntity, ClienteRequestDTO clienteRequestDTO) {

        clienteEntity.setPrimerNombre(clienteRequestDTO.getPrimerNombre());
        clienteEntity.setSegundoNombre(clienteRequestDTO.getSegundoNombre());
        clienteEntity.setPrimerApellido(clienteRequestDTO.getPrimerApellido());
        clienteEntity.setSegundoApellido(clienteRequestDTO.getSegundoApellido());
        clienteEntity.setTelefono(clienteRequestDTO.getTelefono());
        clienteEntity.setCorreoElectronico(clienteRequestDTO.getCorreoElectronico());
    }
}
