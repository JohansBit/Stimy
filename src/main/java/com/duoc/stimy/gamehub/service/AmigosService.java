package com.duoc.stimy.gamehub.service;

// 1. IMPORTS CORREGIDOS: Apuntando exactamente a la nueva ruta de tus compañeros
import com.duoc.stimy.gamehub.model.Amigos;
import com.duoc.stimy.gamehub.model.Usuario;
import com.duoc.stimy.gamehub.repository.AmigosRepository;
import com.duoc.stimy.gamehub.repository.UsuarioRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AmigosService {

    private static final Logger log = LoggerFactory.getLogger(AmigosService.class);

    @Autowired
    private AmigosRepository amigoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void agregarAmigo(Long usuarioId, Long amigoId) {
        log.info("Service: Procesando alta de amigo. Usuario: {} -> Amigo: {}", usuarioId, amigoId);

        Amigos relacion = new Amigos();
        relacion.setUsuarioId(usuarioId);
        relacion.setAmigoId(amigoId);
        relacion.setEstado("ACEPTADO");

        amigoRepository.save(relacion);
        log.info("Service: Relación de amistad guardada con éxito en la base de datos.");
    }

    public List<Usuario> listarAmigos(Long usuarioId) {
        log.info("Service: Buscando amigos aceptados para el usuario ID: {}", usuarioId);

        List<Amigos> relaciones = amigoRepository.findByUsuarioIdAndEstado(usuarioId, "ACEPTADO");
        List<Usuario> amigos = new ArrayList<>();

        // Optimización de la Lambda (Para resolver el aviso del IDE)
        for (Amigos r : relaciones) {
            usuarioRepository.findById(r.getAmigoId()).ifPresent(amigos::add);
        }

        log.info("Service: Se encontraron {} amigos para el usuario ID: {}", amigos.size(), usuarioId);
        return amigos;
    }
}