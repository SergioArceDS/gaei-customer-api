package com.gaei.customer.service;

import com.gaei.customer.DataBuilderTest;
import com.gaei.customer.dto.ClienteRequestDTO;
import com.gaei.customer.dto.ClienteResponseDTO;
import com.gaei.customer.entity.ClienteEntity;
import com.gaei.customer.exception.BusinessException;
import com.gaei.customer.repository.ClienteRepository;
import com.gaei.customer.service.impl.ClienteServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceImplTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    @Test
    void guardarClienteTest() {
        ClienteRequestDTO clienteRequestDTO = DataBuilderTest.buildClienteRequestDTO();
        when(clienteRepository.existsByTipoDocumentoAndNumeroDocumento(clienteRequestDTO.getTipoDocumento(), clienteRequestDTO.getNumeroDocumento()))
                .thenReturn(false);

        clienteService.guardarCliente(clienteRequestDTO);

        verify(clienteRepository).save(any(ClienteEntity.class));
    }

    @Test
    void guardarClienteExceptionTest() {
        ClienteRequestDTO clienteRequestDTO = DataBuilderTest.buildClienteRequestDTO();

        when(clienteRepository.existsByTipoDocumentoAndNumeroDocumento(clienteRequestDTO.getTipoDocumento(), clienteRequestDTO.getNumeroDocumento()))
                .thenReturn(true);

        assertThrows(BusinessException.class,
                () -> clienteService.guardarCliente(DataBuilderTest.buildClienteRequestDTO()));

        verify(clienteRepository, never()).save(any());
    }

    @Test
    void consultarClienteTest() {
        String tipoDocumento = "CC";
        String numeroDocumento = "1992883";

        when(clienteRepository.findByTipoDocumentoAndNumeroDocumento(tipoDocumento, numeroDocumento))
                .thenReturn(Optional.of(DataBuilderTest.buildClienteEntity()));

        ClienteResponseDTO response =
                clienteService.consultarCliente(tipoDocumento, numeroDocumento);

        assertNotNull(response);
        assertEquals(tipoDocumento, response.getTipoDocumento());
        assertEquals(numeroDocumento, response.getNumeroDocumento());

        verify(clienteRepository).findByTipoDocumentoAndNumeroDocumento(tipoDocumento, numeroDocumento);
    }

    @Test
    void consultarClienteExceptionTest() {
        String tipoDocumento = "CC";
        String numeroDocumento = "123456";

        when(clienteRepository.findByTipoDocumentoAndNumeroDocumento(tipoDocumento, numeroDocumento))
                .thenReturn(Optional.empty());

        assertThrows(BusinessException.class,
                () -> clienteService.consultarCliente(tipoDocumento, numeroDocumento));

        verify(clienteRepository).findByTipoDocumentoAndNumeroDocumento(tipoDocumento, numeroDocumento);
    }

    @Test
    void actualizarClienteTest() {
        ClienteEntity clienteEntity = DataBuilderTest.buildClienteEntity();
        ClienteRequestDTO clienteRequestDTO = DataBuilderTest.buildClienteRequestDTO();

        when(clienteRepository.findByTipoDocumentoAndNumeroDocumento(clienteRequestDTO.getTipoDocumento(), clienteRequestDTO.getNumeroDocumento()))
                .thenReturn(Optional.of(clienteEntity));

        clienteService.actualizarCliente(clienteRequestDTO);

        verify(clienteRepository).save(clienteEntity);
        assertEquals(clienteRequestDTO.getPrimerNombre(), clienteEntity.getPrimerNombre());
    }

    @Test
    void actualizarClienteExceptionTest() {
        ClienteRequestDTO clienteRequestDTO = DataBuilderTest.buildClienteRequestDTO();

        when(clienteRepository.findByTipoDocumentoAndNumeroDocumento(clienteRequestDTO.getTipoDocumento(), clienteRequestDTO.getNumeroDocumento()))
                .thenReturn(Optional.empty());

        assertThrows(BusinessException.class,
                () -> clienteService.actualizarCliente(clienteRequestDTO));

        verify(clienteRepository, never()).save(any());
    }
}
