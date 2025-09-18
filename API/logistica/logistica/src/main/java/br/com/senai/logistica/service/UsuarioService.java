package br.com.senai.logistica.service;

import br.com.senai.logistica.model.Usuario;
import br.com.senai.logistica.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository repo) {
        usuarioRepository = repo;
    }

    //Metodo Listar Todos
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    //Metodo Cadastrar
    public Usuario cadastrarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    //Metodo Buscar
    public Usuario buscarPorId (Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    //Metodo Deletar
    public Usuario deletarUsuario(Integer id) {

        //1. Verificar se o usuario existe
        Usuario usuario = buscarPorId(id);

        //2. Se não existir, retornar nulo
        if (usuario == null) {
            return null;
        }

        //3. Se existir, excluo
        usuarioRepository.delete(usuario);
        return usuario;
    }

    //Metodo Atualizar
    public Usuario atualizarUsuario (Integer id, Usuario usuarioNovo) {

        //1. Procurar quem eu quero atualizar
        Usuario usuarioAntigo = buscarPorId(id);

        //2. Se eu não encontrar, retorno nulo
        if (usuarioAntigo == null) {
            return null;
        }

        //3. Se eu achar, eu atualizo
        usuarioAntigo.setTipoUsuario(usuarioNovo.getTipoUsuario());
        usuarioAntigo.setEmail(usuarioNovo.getEmail());
        usuarioAntigo.setSenha(usuarioNovo.getSenha());
        usuarioAntigo.setNomeCompleto(usuarioNovo.getNomeCompleto());
        return usuarioRepository.save(usuarioAntigo);
    }
}
