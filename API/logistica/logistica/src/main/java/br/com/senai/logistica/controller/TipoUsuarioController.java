package br.com.senai.logistica.controller;

import br.com.senai.logistica.model.TipoUsuario;
import br.com.senai.logistica.service.TipoUsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipousuario")
public class TipoUsuarioController {

    //Controller -> Service
    private final TipoUsuarioService tipoUsuarioService;

    public TipoUsuarioController(TipoUsuarioService service) {
        tipoUsuarioService = service;
    }

    //Metodo Listar
    @GetMapping
    public ResponseEntity<List<TipoUsuario>> listarTipoUsuario() {

        //1. Pegar a lista de tipos de usuarios
        List<TipoUsuario> tipoUsuarios = tipoUsuarioService.listarTodos();
        return ResponseEntity.ok(tipoUsuarios);
    }

    //Metodo Cadastrar
    @PostMapping
    public ResponseEntity<TipoUsuario> cadastrarTipoUsuario(@RequestBody TipoUsuario tipoUsuario) {

        //1. Tentar cadastrar o tipo de usuario
        tipoUsuarioService.cadastrarTipoUsuario(tipoUsuario);

        //Codigo 201 - CREATED
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoUsuario);
    }

    //Metodo Buscar
    @GetMapping("/{id}")
    //Path Variable recebe um valor no LINK
    public ResponseEntity<?> buscarTipoUsuarioPorId(@PathVariable Integer id) {

        //1. Procurar e guardar o tipo de usuario
        TipoUsuario tipoUsuario = tipoUsuarioService.buscarPorId(id);

        //2. Se não encontrar, retornar erro
        if (tipoUsuario == null) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tipo de Usuario " + id + " não encontrado !");
        }

        //3. Se encontrar, retornar o tipo de usuario
        return ResponseEntity.ok(tipoUsuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarTipoUsuarioPorId(@PathVariable Integer id) {

        //1. Verificar se o tipo de usuario existe
        TipoUsuario tipoUsuario = tipoUsuarioService.buscarPorId(id);

        //2. Se não encontrar, retornar erro
        if (tipoUsuario == null) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tipo de Usuario " + id + " não encontrado !");
        }

        //3. Se existir, excluo
        return ResponseEntity.ok(tipoUsuario);
    }

    //Metodo Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarTipoUsuario(@PathVariable Integer id, @RequestBody TipoUsuario tipoNovo) {

        //1. Tento atualizar o tipo usuario
        TipoUsuario tipoAntigo = tipoUsuarioService.atualizarTipoUsuario(id, tipoNovo);

        //2. Se não achar o tipo de usuario, mostro erro
        if (tipoAntigo == null) {
            return ResponseEntity.status(404).body("Tipo de Usuario não encontrado !");
        }

        //3. Se achar, retorno ok
        return ResponseEntity.ok(tipoAntigo);
    }
}
