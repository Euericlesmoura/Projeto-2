package br.com.senai.logistica.controller;

import br.com.senai.logistica.model.Endereco;
import br.com.senai.logistica.service.EnderecoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/endereco")
public class EnderecoController {

    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService service) {
        enderecoService = service;
    }

    //Metodo Listar Todos
    @GetMapping
    public ResponseEntity<List<Endereco>> listarEndereco() {

        List<Endereco> enderecos = enderecoService.listarEnderecos();
        return ResponseEntity.ok(enderecos);
    }

    //Metodo Cadastrar
    @PostMapping
    public ResponseEntity<Endereco> cadastrarEndereco(@RequestBody Endereco endereco) {

        enderecoService.cadastrarEndereco(endereco);

        return ResponseEntity.status(HttpStatus.CREATED).body(endereco);
    }

    //Metodo Buscar
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarEndereco(@PathVariable Integer id) {

        Endereco endereco = enderecoService.busarPorId(id);

        if (endereco == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereco " + id + " não encontrado !");
        }

        return ResponseEntity.ok(endereco);
    }

    //Metodo Deletar
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarEndereco(@PathVariable Integer id) {

        Endereco endereco = enderecoService.busarPorId(id);

        if (endereco == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereco " + id + " não encontrado !");
        }

        return ResponseEntity.ok(endereco);
    }

    //Metodo Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarEndereco(@PathVariable Integer id, @RequestBody Endereco endNovo) {

        Endereco endAntigo = enderecoService.atualizarEndereco(id, endNovo);

        if (endAntigo == null) {
            return ResponseEntity.status(404).body("Endereco não encontrado !");
        }

        return ResponseEntity.ok(endAntigo);
    }

}
