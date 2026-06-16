package com.coinquest.api.controller;

import com.coinquest.api.model.Usuario;
import com.coinquest.api.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {
    
    @Autowired
    private UsuarioRepository repository;

    // Rota 1: Cadastro de usuario
    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@Valid @RequestBody Usuario usuario) {
        // Valida se o e-mail já está cadastrado no sistema
        Optional<Usuario> usuarioExistente = repository.findByEmail(usuario.getEmail());
        if (usuarioExistente.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro: Este e-mail já está cadastrado.");
        }

        //Salva o usuário no banco de dados H2
        Usuario usuarioSalvo = repository.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
    }
    // Rota 2: Login de Usuário
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario loginDados) {
        // Busca o usuário pelo e-mail enviado
        Optional<Usuario> usuarioOpt = repository.findByEmail(loginDados.getEmail());
        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Erro: Usuário não encontrado");
        }

        Usuario usuario = usuarioOpt.get();

        // Valida se a senha bate com a cadastrada
        if (!usuario.getSenha().equals(loginDados.getSenha())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Erro: Senha incorreta.");
        }

        // Se tudo estiver certo, retorna o usuário logado
        return ResponseEntity.ok(usuario);
    }
}
