package com.gaei.customer.service.impl;

import com.gaei.customer.dto.ClienteRequestDTO;
import com.gaei.customer.dto.ClienteResponseDTO;
import com.gaei.customer.entity.ClienteEntity;
import com.gaei.customer.exception.BusinessException;
import com.gaei.customer.mapper.ClienteMapper;
import com.gaei.customer.repository.ClienteRepository;
import com.gaei.customer.service.ClienteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Transactional
    @Override
    public void guardarCliente(ClienteRequestDTO clienteRequestDTO) {
        String idTx = clienteRequestDTO.getIdTx();
        String tipoDocumento = clienteRequestDTO.getTipoDocumento();
        String numeroDocumento = clienteRequestDTO.getNumeroDocumento();

        log.info("[{}] Procesando registro de cliente tipoDocumento={} numeroDocumento={}", idTx, tipoDocumento, numeroDocumento);

        if(this.clienteRepository.existsByTipoDocumentoAndNumeroDocumento(tipoDocumento, numeroDocumento)){
            log.info("[{}] El cliente ya existe tipoDocumento={} numeroDocumento={}", idTx, tipoDocumento, numeroDocumento);

            throw new BusinessException(idTx,
                    String.format("El cliente %s %s ya se encuentra registrado", tipoDocumento, numeroDocumento));
        }

        clienteRepository.save(ClienteMapper.toEntity(clienteRequestDTO));

        log.info("[{}] Cliente registrado exitosamente tipoDocumento={} numeroDocumento={}", idTx, tipoDocumento, numeroDocumento);
    }

    @Transactional(readOnly = true)
    @Override
    public ClienteResponseDTO consultarCliente(String tipoDocumento, String numeroDocumento) {
        log.info("Consultando cliente tipoDocumento={} numeroDocumento={}", tipoDocumento, numeroDocumento);

        ClienteEntity clienteEntity = this.obtenerCliente(UUID.randomUUID().toString(), tipoDocumento, numeroDocumento);

        log.info("Cliente encontrado tipoDocumento={} numeroDocumento={}", tipoDocumento, numeroDocumento);
        return ClienteMapper.toResponse(clienteEntity);
    }

    @Transactional
    @Override
    public void actualizarCliente(ClienteRequestDTO clienteRequestDTO) {
        String idTx = clienteRequestDTO.getIdTx();
        String tipoDocumento = clienteRequestDTO.getTipoDocumento();
        String numeroDocumento = clienteRequestDTO.getNumeroDocumento();

        log.info("[{}] Procesando actualizacion de cliente tipoDocumento={} numeroDocumento={}", idTx, tipoDocumento, numeroDocumento);

        ClienteEntity clienteEntity = this.obtenerCliente(idTx, tipoDocumento, numeroDocumento);

        ClienteMapper.updateEntity(clienteEntity, clienteRequestDTO);
        clienteRepository.save(clienteEntity);

        log.info("[{}] Cliente actualizado exitosamente tipoDocumento={} numeroDocumento={}", idTx, tipoDocumento, numeroDocumento);
    }

    private ClienteEntity obtenerCliente(String idTx, String tipoDocumento, String numeroDocumento){
        return clienteRepository.findByTipoDocumentoAndNumeroDocumento(tipoDocumento, numeroDocumento)
                .orElseThrow(() -> {
                    log.warn("[{}] Cliente no encontrado tipoDocumento={} numeroDocumento={}", idTx, tipoDocumento, numeroDocumento);

                    return new BusinessException(idTx,
                            String.format("El cliente %s %s no se encuentra registrado", tipoDocumento, numeroDocumento));
                });
    }


}
