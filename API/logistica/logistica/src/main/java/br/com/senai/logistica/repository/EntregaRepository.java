package br.com.senai.logistica.repository;

import br.com.senai.logistica.model.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega,Integer> {
}
