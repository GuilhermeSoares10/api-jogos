package com.francisco.api_jogos.repository;

import com.francisco.api_jogos.model.Jogo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JogoRepository extends JpaRepository<Jogo, Long> {
}