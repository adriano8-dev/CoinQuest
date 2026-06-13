package com.coinquest.api.controller;

import com.coinquest.api.model.Meta;
import com.coinquest.api.model.Usuario;
import com.coinquest.api.repository.MetaRepository;
import com.coinquest.api.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/metas")
public class MetaController {
    
    @Autowired
    private MetaRepository metaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Rota 1: Criar uma nova meta para um usuario específico
    @PostMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> criarMeta(@PathVariable Long usuarioId, @Valid @RequestBody Meta meta) {
        // Verifica se o usuário realmente existe antes de associar a meta
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);
        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Erro: Usuário não encontrado. Não é possível associar a meta.");
        }

        // Vincula o usuário encontrado à meta
        meta.setUsuario(usuarioOpt.get());

        // Salva a nova meta no banco de dados H2
        Meta metaSalva = metaRepository.save(meta);
        return ResponseEntity.status(HttpStatus.CREATED).body(metaSalva);
    }

    // Rota 2: listar todas as metas de um usuário específico
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> listarMetasPorUsuario(@PathVariable Long usuarioId) {
        // Verifica se o usuário existe
        boolean usuarioExiste = usuarioRepository.existsById(usuarioId);
        if (!usuarioExiste) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Erro: Usuário não encontrado.");
        }

        // Busca a lista de metas usando o método inteligente do Repositório
        List<Meta> metas = metaRepository.findByUsuarioId(usuarioId);
        return ResponseEntity.ok(metas);
    }
      
}
