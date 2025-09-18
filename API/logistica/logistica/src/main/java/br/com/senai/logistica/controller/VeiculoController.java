package br.com.senai.logistica.controller;

import br.com.senai.logistica.model.Veiculo;
import br.com.senai.logistica.service.VeiculoService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/veiculo")
public class VeiculoController {

    private final VeiculoService veiculoService;

    public VeiculoController(VeiculoService service) {
        veiculoService = service;
    }

    //Metodo Listar
    @GetMapping
    public ResponseEntity<List<Veiculo>> listarVeiculo() {

        List<Veiculo> veiculo = veiculoService.listarVeiculo();
        return ResponseEntity.ok(veiculo);
    }

    //Metodo Cadastrar
    @PostMapping
    public ResponseEntity<Veiculo> cadastrarVeiculo(@RequestBody Veiculo veiculo){

        veiculoService.cadastrarVeiculo(veiculo);
        return ResponseEntity.status(HttpStatus.CREATED).body(veiculo);
    }

    //Metodo Buscar
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarVeiculoPorId(@PathVariable Integer id) {

        Veiculo veiculo = veiculoService.buscarPorId(id);

        if (veiculo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Veiculo " + id + " não encontrado !");
        }

        return ResponseEntity.ok(veiculo);
    }

    //Metodo Deletar
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarVeiculo(@PathVariable Integer id) {

        Veiculo veiculo = veiculoService.buscarPorId(id);

        if (veiculo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Veiculo " + id + " não encontrado !");
        }

        return ResponseEntity.ok(veiculo);
    }

    //Metodo Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarVeiculo(@PathVariable Integer id, @RequestBody Veiculo veiculoNovo){

        Veiculo veiculoAntigo = veiculoService.buscarPorId(id);

        if (veiculoAntigo == null) {
            return ResponseEntity.status(404).body("Veiculo não encontrado !");
        }

        return ResponseEntity.ok(veiculoAntigo);
    }
}
