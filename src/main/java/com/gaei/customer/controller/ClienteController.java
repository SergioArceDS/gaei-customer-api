package com.gaei.customer.controller;


import com.gaei.customer.dto.ClienteRequestDTO;
import com.gaei.customer.dto.ClienteResponseDTO;
import com.gaei.customer.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
@Slf4j
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping("/guardarCliente")
    public ResponseEntity<?> guardarCliente(@Valid @RequestBody ClienteRequestDTO clienteRequestDTO) {
        log.info("[INICIO] POST /guardarCliente idTx={} tipoDocumento={} numeroDocumento={}", clienteRequestDTO.getIdTx(), clienteRequestDTO.getTipoDocumento(), clienteRequestDTO.getNumeroDocumento());

        this.clienteService.guardarCliente(clienteRequestDTO);

        log.info("[FIN] POST /guardarCliente idTx={} status=200", clienteRequestDTO.getIdTx());

        return ResponseEntity.ok(
                Map.of(
                        "idTx", clienteRequestDTO.getIdTx(),
                        "mensaje", "Cliente " + clienteRequestDTO.getNumeroDocumento() + " almacenado de forma exitosa."
                )
        );
    }

    @GetMapping("/consultarCliente/{tipoDocumento}_{numeroDocumento}")
    public ResponseEntity<ClienteResponseDTO> consultarCliente(@PathVariable String tipoDocumento, @PathVariable String numeroDocumento) {
        log.info("[INICIO] GET /consultarCliente tipoDocumento={} numeroDocumento={}", tipoDocumento, numeroDocumento);

        ClienteResponseDTO clienteResponseDTO = this.clienteService.consultarCliente(tipoDocumento, numeroDocumento);

        log.info("[FIN] GET /consultarCliente status=200 tipoDocumento={} numeroDocumento={}", tipoDocumento, numeroDocumento);
        return ResponseEntity.ok(clienteResponseDTO);
    }

    @PostMapping("/actualizarCliente")
    public ResponseEntity<?> actualizarCliente(@Valid @RequestBody ClienteRequestDTO clienteRequestDTO) {
        log.info("[INICIO] POST /actualizarCliente idTx={} tipoDocumento={} numeroDocumento={}", clienteRequestDTO.getIdTx(), clienteRequestDTO.getTipoDocumento(), clienteRequestDTO.getNumeroDocumento());

        this.clienteService.actualizarCliente(clienteRequestDTO);

        log.info("[FIN] POST /actualizarCliente idTx={} status=200", clienteRequestDTO.getIdTx());
        return ResponseEntity.ok(
                Map.of(
                        "idTx", clienteRequestDTO.getIdTx(),
                        "mensaje", "Cliente " + clienteRequestDTO.getNumeroDocumento() + " actualizado de forma exitosa."
                )
        );
    }
}
