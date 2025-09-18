package br.com.senai.logistica.controller;

import br.com.senai.logistica.model.Usuario;
import br.com.senai.logistica.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService service) {
        usuarioService = service;
    }

    //Metodo Listar
    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuario() {

        List<Usuario> usuario = usuarioService.listarUsuarios();
        return ResponseEntity.ok(usuario);
    }

    //Metodo Cadastrar
    @PostMapping
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario) {

        //1. Tentar cadastrar o tipo de usuario
        usuarioService.cadastrarUsuario(usuario);

        //Codigo 201 - CREATED
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    //Metodo Buscar
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarUsuarioPorId(@PathVariable Integer id) {

        Usuario usuario = usuarioService.buscarPorId(id);

        if (usuario == null) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario " + id + " não encontrado !");
        }

        return ResponseEntity.ok(usuario);
    }

    //Metodo Deletar
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarUsuario(@PathVariable Integer id) {

        //1. Verificar se o usuario existe
        Usuario usuario = usuarioService.buscarPorId(id);

        //2. Se não encontrar, retornar erro
        if (usuario == null) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario " + id + " não encontrado !");
        }

        //3. Se existir, excluo
        return ResponseEntity.ok(usuario);
    }

    //Metodo Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarUsuario(@PathVariable Integer id, @RequestBody Usuario usuarioNovo) {

        //1. Tento atualizar o usuario
        Usuario usuarioAntigo = usuarioService.atualizarUsuario(id, usuarioNovo);

        //2. Se não achar o usuario, mostro erro
        if (usuarioAntigo == null) {
            return ResponseEntity.status(404).body("Usuario não encontrado !");
        }

        //3. Se achar, retorno ok
        return ResponseEntity.ok(usuarioAntigo);
    }
}
