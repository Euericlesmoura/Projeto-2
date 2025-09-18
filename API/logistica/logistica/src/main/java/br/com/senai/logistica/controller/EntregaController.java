package br.com.senai.logistica.controller;

import br.com.senai.logistica.model.Entrega;
import br.com.senai.logistica.service.EntregaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entrega")
public class EntregaController {

    private final EntregaService entregaService;

    public EntregaController(EntregaService service) {
        entregaService = service;
    }

    //Metodo Listar
    @GetMapping
    public ResponseEntity<List<Entrega>> listarEntrega() {

        List<Entrega> entrega = entregaService.listarEntrega();
        return ResponseEntity.ok(entrega);
    }

    //Metodo Cadastrar
    @PostMapping
    public ResponseEntity<Entrega> cadastrarEntrega(@RequestBody Entrega entrega) {

        entregaService.cadastrarEntrega(entrega);

        return ResponseEntity.status(HttpStatus.CREATED).body(entrega);
    }

    //Metodo Buscar
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarEntregaPorId(@PathVariable Integer id) {

        Entrega entrega = entregaService.buscarPorId(id);

        if (entrega == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entrega " + id + " não encontrado !");
        }

        return  ResponseEntity.ok(entrega);
    }

    //Metodo Deletar
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarEntrega(@PathVariable Integer id) {

        Entrega entrega = entregaService.buscarPorId(id);

        if (entrega == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ENtrega " + id + " não encontrado !");
        }

        return ResponseEntity.ok(entrega);
    }

    //Metodo Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarEntrega(@PathVariable Integer id, @RequestBody Entrega entregaNovo) {

        Entrega entregaAntigo = entregaService.buscarPorId(id);

        if (entregaAntigo == null) {
            return ResponseEntity.status(404).body("Entrega não encontrado !");
        }

        return ResponseEntity.ok(entregaAntigo);
    }
}
