package com.francisco.api_jogos.controller;

import com.francisco.api_jogos.model.Jogo;
import com.francisco.api_jogos.repository.JogoRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class JogoController {

    private final JogoRepository repository;

    public JogoController(JogoRepository repository) {
        this.repository = repository;
    }

    // GET /jogos
    @GetMapping("/jogos")
    public List<Jogo> listar() {
        return repository.findAll();
    }

    // GET /jogos/{id}
    @GetMapping("/jogos/{id}")
    public ResponseEntity<Jogo> buscarPorId(@PathVariable Long id) {

        Optional<Jogo> jogo = repository.findById(id);

        return jogo
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /jogos
    @PostMapping("/jogos")
    public ResponseEntity<Jogo> cadastrar(@Valid @RequestBody Jogo jogo) {

        Jogo novoJogo = repository.save(jogo);

        return ResponseEntity
                .created(URI.create("/jogos/" + novoJogo.getId()))
                .body(novoJogo);
    }

    // PUT /jogos/{id}
    @PutMapping("/jogos/{id}")
    public ResponseEntity<Jogo> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody Jogo jogoAtualizado) {

        Optional<Jogo> jogoExistente = repository.findById(id);

        if (jogoExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        jogoAtualizado.setId(id);

        Jogo jogoSalvo = repository.save(jogoAtualizado);

        return ResponseEntity.ok(jogoSalvo);
    }

    // DELETE /jogos/{id}
    @DeleteMapping("/jogos/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {

        Optional<Jogo> jogo = repository.findById(id);

        if (jogo.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}