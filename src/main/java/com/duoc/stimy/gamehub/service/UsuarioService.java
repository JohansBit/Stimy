package com.duoc.stimy.gamehub.service;

// 1. IMPORTS CORREGIDOS: Apuntando ahora sí al paquete definitivo gamehub
import com.duoc.stimy.gamehub.dto.UsuarioRequestDTO;
import com.duoc.stimy.gamehub.model.Usuario;
import com.duoc.stimy.gamehub.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private static final Logger log = LoggerFactory.getLogger(UsuarioService.class);

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario crearUsuario(UsuarioRequestDTO dto) {
        log.info("Service: Procesando la creación del usuario: {}", dto.getNombreUsuario());

        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(dto.getNombreUsuario());
        usuario.setFechaNacimiento(dto.getFechaNacimiento());
        usuario.setNacionalidad(dto.getNacionalidad());

        return usuarioRepository.save(usuario);
    }

    public List<Usuario> obtenerTodos() {
        log.info("Service: Buscando todos los usuarios en la base de datos");
        return usuarioRepository.findAll();
    }
}