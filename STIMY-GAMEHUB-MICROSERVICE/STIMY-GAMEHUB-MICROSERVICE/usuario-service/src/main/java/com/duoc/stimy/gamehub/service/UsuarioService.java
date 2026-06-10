package com.duoc.stimy.gamehub.service;

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

    public Usuario guardarUsuario(Usuario usuario) {
        log.info("[USUARIO-SERVICE] Creando o actualizando usuario: {}", usuario.getNombreUsuario());
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarTodos() {
        log.info("[USUARIO-SERVICE] Listando todos los usuarios registrados");
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        log.info("[USUARIO-SERVICE] Buscando usuario remoto por ID: {}", id);
        return usuarioRepository.findById(id).orElse(null);
    }
}