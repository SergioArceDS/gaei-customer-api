package com.gaei.customer;

import com.gaei.customer.dto.ClienteRequestDTO;
import com.gaei.customer.entity.ClienteEntity;

import java.util.UUID;

public final class DataBuilderTest {

    private DataBuilderTest(){}

    public static ClienteRequestDTO buildClienteRequestDTO(){
        ClienteRequestDTO clienteRequestDTO = new ClienteRequestDTO();
        clienteRequestDTO.setIdTx(UUID.randomUUID().toString());
        clienteRequestDTO.setTipoDocumento("CC");
        clienteRequestDTO.setNumeroDocumento("1992883");
        clienteRequestDTO.setPrimerNombre("Andres");
        clienteRequestDTO.setPrimerApellido("Arango");
        clienteRequestDTO.setSegundoApellido("Suarez");
        clienteRequestDTO.setCorreoElectronico("andres2222@gmail.com");
        clienteRequestDTO.setTelefono(3163467087L);

        return clienteRequestDTO;
    }

    public static ClienteEntity buildClienteEntity(){
        ClienteEntity clienteEntity = new ClienteEntity();

        clienteEntity.setTipoDocumento("CC");
        clienteEntity.setNumeroDocumento("1992883");
        clienteEntity.setPrimerNombre("Sergio");
        clienteEntity.setPrimerApellido("Gonzalez");
        clienteEntity.setSegundoApellido("Arce");
        clienteEntity.setCorreoElectronico("sergioarce@gmail.com");
        clienteEntity.setTelefono(3163467087L);

        return clienteEntity;
    }
}
