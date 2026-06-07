package com.duoc.stimy.gamehub.service;

import com.duoc.stimy.gamehub.client.CarritoClient;
import com.duoc.stimy.gamehub.dto.CarritoRequestDTO;
import com.duoc.stimy.gamehub.model.Pagos;
import com.duoc.stimy.gamehub.model.Biblioteca;
import com.duoc.stimy.gamehub.repository.PagosRepository;
import com.duoc.stimy.gamehub.repository.BibliotecaRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Service

public class PagoService {
    private static final Logger log = LoggerFactory.getLogger(PagoService.class);

    private final PagosRepository pagosRepository;
    private final BibliotecaRepository bibliotecaRepository;
    private final CarritoClient carritoClient;

    public PagoService(PagosRepository pagosRepository, BibliotecaRepository bibliotecaRepository, CarritoClient carritoClient){
        this.pagosRepository = pagosRepository;
        this.bibliotecaRepository = bibliotecaRepository;
        this.carritoClient = carritoClient;
    }

    @Transactional
    public Pagos procesarPago(Long usuarioId){
        log.info("Service Pago: Iniciando proceso para usuario Id: {}", usuarioId);

        List<CarritoRequestDTO> itemsCarrito = carritoClient.obtenerCarrito(usuarioId);

        if(itemsCarrito == null || itemsCarrito.isEmpty()){
            log.warn("Service Pago: No existen items para pagar para el usuario ID: {}.",usuarioId);
            throw new RuntimeException("El carrito está vacío. No hay nada que pagar.");
        }

        int total = (int) itemsCarrito.stream().mapToDouble(CarritoRequestDTO::getPrecio).sum();
        log.info("Service Pago: Carrito detectado con {} juegos. Total: ${}",itemsCarrito.size(),total);

        Pagos pagos  = new Pagos();
        pagos.setUsuarioId(usuarioId);
        pagos.setMontoTotal(total);
        pagos.setEstado("APROBADO");
        pagos.setFechaPago(LocalDateTime.now());

        Pagos pagoGuardado = pagosRepository.save(pagos);
        log.info("Service Pago: Boleta registrada con ID: {}", pagoGuardado.getIdPago());

        log.info("Service Pago: Traspasando juegos comprados a la biblioteca del usuario ID: {}", usuarioId);
        for(CarritoRequestDTO item: itemsCarrito){
            Biblioteca registroBiblioteca = new Biblioteca();
            registroBiblioteca.setUsuarioId(usuarioId);
            registroBiblioteca.setVideojuegoId(item.getVideojuegoId());
            registroBiblioteca.setFechaCompra(LocalDateTime.now());

            bibliotecaRepository.save(registroBiblioteca);
            log.info("Service Pagos: Juego ID: {} agregado a la biblioteca del usuario ID: {}", item.getNombreVideojuego(), usuarioId);

        }
        log.info("Service Pago: Solicitando vaciado de Carrito...");
        carritoClient.vaciarCarrito(usuarioId);
        log.info("Service Pago: Carrito vaciado, Flujo de compra completado exitosamente.");

        return pagoGuardado;
    }
}
